package task.management.backend.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.management.backend.model.Task;
import task.management.backend.model.User;
import task.management.backend.repository.UserRepository;

public class TaskFactory {

    private static final Logger logger = LoggerFactory.getLogger(TaskFactory.class);

    public static Task createTask(String description, String status, String priority, Long assignedToId, UserRepository userRepository) {
        logger.info("Inicio de creación de tarea en TaskFactory");

        Task.Status taskStatus = Task.Status.valueOf(status);
        Task.Priority taskPriority = Task.Priority.valueOf(priority);

        User assignedTo = userRepository.findById(assignedToId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return new Task(
                null,
                description,
                taskStatus,
                taskPriority,
                assignedTo
        );
    }
}
