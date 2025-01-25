package task.management.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import task.management.backend.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task.Status status);
    List<Task> findByAssignedToId(Long userId);
}