package task.management.backend.utils;

import org.springframework.stereotype.Component;
import task.management.backend.model.Task;
import task.management.backend.model.User;

@Component
public class Validator {


    public static void validateCreateTask(Task task) {
        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            throw new ApplicationException("Description is required", "task.description.required");
        }
        if (task.getStatus() == null) {
            throw new ApplicationException("Status is required", "task.status.required");
        }
        if (task.getPriority() == null) {
            throw new ApplicationException("Priority is required", "task.priority.required");
        }
        if (task.getAssignedTo() == null) {
            throw new ApplicationException("AssignedTo is required", "task.assignedTo.required");
        }
    }

    public static void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ApplicationException("Name is required", "user.name.required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ApplicationException("Email is required", "user.email.required");
        }
        //validar el role
        if (user.getRole() == null) {
            throw new ApplicationException("Role is required", "user.role.required");
        }
    }
}
