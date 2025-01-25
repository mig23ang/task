package task.management.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.management.backend.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(User.Role role);
}
