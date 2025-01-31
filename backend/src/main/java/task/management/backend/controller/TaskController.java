package task.management.backend.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.management.backend.model.Task;
import task.management.backend.services.TaskService;
import task.management.backend.utils.ApplicationException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        logger.info("Inicio de creación de tarea en TaskController");
        try {
            Task createdTask = taskService.createTask(task);
            return ResponseEntity.ok(createdTask);
        } catch (ApplicationException ex) {
            logger.error("Error de negocio: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            logger.error("Error interno del servidor al crear tarea", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskStatus(@Valid @PathVariable Long id, @RequestBody Task task) {
        logger.info("Inicio de actualizar la tarea en TaskController");
        try {
            Task updatedTask = taskService.updateTaskStatus(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (ApplicationException ex) {
            logger.error("Error de negocio: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            logger.error("Error interno del servidor al actualizar tarea", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@Valid @PathVariable Long id) {
        logger.info("Inicio de obtener tarea por ID en TaskController");
        try {
            Optional<Task> task = taskService.getTask(id);
            return task.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (ApplicationException ex) {
            logger.error("Error de negocio: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            logger.error("Error interno del servidor al obtener tarea", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@Valid @PathVariable Task.Status status) {
        logger.info("Obteniendo tareas con estado: " + status);
        try {
            List<Task> tasks = taskService.getTasksByStatus(status);
            return ResponseEntity.ok(tasks);
        } catch (ApplicationException ex) {
            logger.error("Error de negocio: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            logger.error("Error interno del servidor al obtener tareas por estado", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUser(@Valid @PathVariable Long userId) {
        logger.info("Obteniendo tareas por usuario: " + userId);
        try {
            List<Task> tasks = taskService.getTasksByUserId(userId);
            return ResponseEntity.ok(tasks);
        } catch (ApplicationException ex) {
            logger.error("Error de negocio: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            logger.error("Error interno del servidor al obtener tareas por usuario", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
