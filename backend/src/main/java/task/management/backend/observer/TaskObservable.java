package task.management.backend.observer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.management.backend.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskObservable {

    private static Logger logger = LoggerFactory.getLogger(TaskObservable.class);
    private final List<TaskObserver> observers = new ArrayList<>();

    public void addObserver(TaskObserver observer) {
        logger.info("Inicializando addObserver en TaskObservable");
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        logger.info("Inicializando removeObserver en TaskObservable");
        observers.remove(observer);
    }

    public void notifyTaskCompleted(Task task) {
        logger.info("Inicializando notifyTaskCompleted en TaskObservable");
        for (TaskObserver observer : observers) {
            observer.onTaskCompleted(task);
        }
    }
}