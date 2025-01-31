package task.management.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import task.management.backend.model.User;
import task.management.backend.repository.UserRepository;
import task.management.backend.utils.ApplicationException;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        logger.info("Inicio de creación de usuario en UserService");
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            logger.error("Error de violación de clave única: " + ex.getMessage(), ex);
            throw new ApplicationException("El correo electrónico ya está en uso.", "user.email.duplicate");
        } catch (Exception ex) {
            logger.error("Error inesperado al crear usuario: " + ex.getMessage(), ex);
            throw new ApplicationException("Ocurrió un error inesperado al crear el usuario", "user.creation.failed");
        }
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
