package task.management.backend.services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.management.backend.factory.TaskFactory;
import task.management.backend.model.Task;
import task.management.backend.model.User;
import task.management.backend.observer.TaskObservable;
import task.management.backend.observer.TechLeadNotifier;
import task.management.backend.repository.TaskRepository;
import task.management.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskObservable taskObservable;

    @Autowired
    private TechLeadNotifier techLeadNotifier;

    @PostConstruct
    public void init() {
        logger.info("Inicializando TaskObservable en TaskService");
        taskObservable.addObserver(techLeadNotifier);
    }

    @Transactional
    public Task createTask(Task task) {

        logger.info("Creando tarea en TaskService");

        Task newTask = TaskFactory.createTask(
                task.getDescription(),
                task.getStatus().toString(),
                task.getPriority().toString(),
                task.getAssignedTo().getId(),
                userRepository
        );


        Task savedTask = taskRepository.save(newTask);
        logger.info("Tarea creada con éxito: " + savedTask);
        taskObservable.notifyTaskCompleted(savedTask);

        return savedTask;
    }


    public Task updateTaskStatus(Long taskId, Task updateTask) {
        logger.info("Inicio de actualizar la tarea en TaskService");

        Task taskConsulada = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        logger.info("Estado antes de actualizar: " + taskConsulada.getStatus());

        taskConsulada.setStatus(updateTask.getStatus());

        logger.info("Estado después de actualizar: " + taskConsulada.getStatus());

        if (updateTask.getStatus() == Task.Status.COMPLETED) {
            logger.info("Tarea completada y notificada");
            taskObservable.notifyTaskCompleted(taskConsulada);
        }

        Task updatedTask = taskRepository.save(taskConsulada);
        logger.info("Tarea actualizada: " + updatedTask);

        return updatedTask;
    }


    public List<Task> getTasksByStatus(Task.Status status) {
        logger.info("Consultando tareas con estado: " + status);
        return taskRepository.findByStatus(status);
    }


    public List<Task> getTasksByUserId(Long userId) {
        logger.info("Inicio de obtener tareas por usuario en TaskService");
        return taskRepository.findByAssignedToId(userId);
    }

    public Optional<Task> getTask(Long id) {
        logger.info("Inicio de obtener tarea por id en TaskService");
        return taskRepository.findById(id);
    }
}
