package task.management.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.management.backend.model.User;
import task.management.backend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        logger.info("Inicio de creación de usuario en UserService");
        return userRepository.save(user);
    }

    public List<User> getUsersByRole(User.Role role) {
        logger.info("Inicio de obtener usuarios por rol en UserService");
        return userRepository.findByRole(role);
    }

    public List<User> getAllUsers() {
        logger.info("Inicio de obtener todos los usuarios en UserService");
        return userRepository.findAll();
    }
}
