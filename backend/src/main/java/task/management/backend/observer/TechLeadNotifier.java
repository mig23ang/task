package task.management.backend.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.management.backend.model.Task;
import task.management.backend.model.User;
import task.management.backend.repository.UserRepository;

import java.util.List;

@Component
public class TechLeadNotifier implements TaskObserver {

    private static final Logger logger = LoggerFactory.getLogger(TechLeadNotifier.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onTaskCompleted(Task task) {
        logger.info("Inicio de notificación al tech lead en TechLeadNotifier");
        List<User> techLeads = userRepository.findByRole(User.Role.TECH_LEAD);
        techLeads.forEach(techLead -> {
            System.out.println("Notificando al tech lead: " + techLead.getName());
        });
    }
}