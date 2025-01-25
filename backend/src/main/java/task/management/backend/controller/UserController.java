package task.management.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.management.backend.model.User;
import task.management.backend.services.UserService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        logger.info("Inicio de creación de usuario en UserController");
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsersByRole(@RequestParam(required = false) User.Role role) {
        logger.info("Inicio de obtener usuarios por rol en UserController");
        return role != null ? userService.getUsersByRole(role) : List.of();
    }

    //consulta de todos los usuarios
    @GetMapping("/all")
    public List<User> getAllUsers() {
        logger.info("Inicio de obtener todos los usuarios en UserController");
        return userService.getAllUsers();
    }
}
