package controller;

import constants.Status;
import constants.TaskType;
import exceptions.InvalidStatusTransition;
import models.Task;
import service.TaskService;

public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void createTask(String taskName, TaskType taskType, long dueDate) {
        taskService.createTask(taskName, taskType, dueDate);
    }

    public void updateStatus(String taskName, Status status)  {
        try {
            taskService.updateStatus(taskName, status);
        } catch (InvalidStatusTransition e) {
            System.out.println("The status transition for the task is invalid");
            throw new RuntimeException(e);
        }
    }

    public Task getTask(String taskName) {
        return taskService.getTask(taskName);
    }

    public void assignTask(String task1, String user) {
        taskService.assignTask(task1, user);
    }

    public String getUser(String task1) {
        return taskService.getUser(task1);
    }
}
