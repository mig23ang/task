package task.management.backend.observer;

import task.management.backend.model.Task;


public interface TaskObserver {

    void onTaskCompleted(Task task);
}
