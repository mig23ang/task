package task.management.backend.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task.management.backend.observer.TaskObservable;

@Configuration
public class TaskObservableConfig {

    @Bean
    public TaskObservable taskObservable() {
        return new TaskObservable();
    }
}
