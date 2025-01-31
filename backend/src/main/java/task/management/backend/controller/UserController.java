package task.management.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.management.backend.model.User;
import task.management.backend.services.UserService;
import task.management.backend.utils.ApplicationException;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.management.backend.utils.Validator;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    Validator validator;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        validator.validateUser(user);
        logger.info("Inicio de creación de usuario en UserController");
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (ApplicationException ex) {
            logger.error("Error de negocio al crear usuario: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getFormattedMessage());
        } catch (Exception ex) {
            logger.error("Error interno del servidor al crear usuario", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsersByRole(@Valid @RequestParam(required = false) User.Role role) {
        logger.info("Inicio de obtener usuarios por rol en UserController");
        try {
            List<User> users = role != null ? userService.getUsersByRole(role) : List.of();
            return ResponseEntity.ok(users);
        } catch (ApplicationException ex) {
            logger.error("Error de negocio al obtener usuarios por rol: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getFormattedMessage());
        } catch (Exception ex) {
            logger.error("Error interno del servidor al obtener usuarios por rol", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        logger.info("Inicio de obtener todos los usuarios en UserController");
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (ApplicationException ex) {
            logger.error("Error de negocio al obtener todos los usuarios: " + ex.getMessage(), ex);
            return ResponseEntity.badRequest().body(ex.getFormattedMessage());
        } catch (Exception ex) {
            logger.error("Error interno del servidor al obtener todos los usuarios", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
