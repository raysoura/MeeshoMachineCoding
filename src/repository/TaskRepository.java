package repository;

import constants.Status;
import constants.TaskType;
import models.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskRepository {
    private final Map<String, Task> taskMap;

    private final Map<String, String> taskUserMap;

    public TaskRepository() {
        this.taskMap = new ConcurrentHashMap<>();
        this.taskUserMap = new ConcurrentHashMap<>();
    }

    public void createTask(String taskName, TaskType taskType, long dueDate){
        Task task = new Task(taskName, taskType, Status.TODO, dueDate);
        synchronized (this) {
            taskMap.put(taskName, task);
        }
        System.out.println("Task created successfully: "+ taskName);
    }

    public Task get(String taskName) {
        return taskMap.get(taskName);
    }

    public synchronized void assignTask(String task1, String user) {
        taskUserMap.put(task1, user);
        System.out.println("Task: "+ task1 +" is assigned to: "+ user);
    }

    public String getUser(String task1) {
        return taskUserMap.getOrDefault(task1, null);
    }

    public synchronized void updateTask(String taskName, Task task) {
        taskMap.put(taskName, task);
    }


}
