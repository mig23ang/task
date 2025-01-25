package task.management.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.management.backend.model.Task;
import task.management.backend.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);


    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        logger.info("Inicio de creación de tarea en TaskController");
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        logger.info("Inicio de actualizar la tarea en TaskController");
        return taskService.updateTaskStatus(id, task);
    }


    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Task.Status status) {
        logger.info("Obteniendo tareas con estado: " + status);
        return taskService.getTasksByStatus(status);
    }
    //obtener tareas por usuario
    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUser(@PathVariable Long userId) {
        logger.info("Obteniendo tareas por usuario: " + userId);
        return taskService.getTasksByUserId(userId);
    }
}