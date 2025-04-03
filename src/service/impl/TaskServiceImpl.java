package service.impl;

import constants.Status;
import constants.TaskType;
import exceptions.InvalidStatusTransition;
import models.Task;
import repository.TaskRepository;
import service.validator.StateTransitionValidator;
import service.validator.Validator;
import service.TaskService;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final Validator validator;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.validator = new StateTransitionValidator();
    }

    @Override
    public void createTask(String taskName, TaskType taskType, long dueDate) {
        taskRepository.createTask(taskName, taskType, dueDate);
    }

    @Override
    public Task getTask(String taskName) {
        return taskRepository.get(taskName);
    }

    @Override
    public void assignTask(String task1, String user) {
        taskRepository.assignTask(task1, user);
    }


    @Override
    public String getUser(String task1) {
        return taskRepository.getUser(task1);
    }

    @Override
    public void updateStatus(String taskName, Status status) throws InvalidStatusTransition {
        Task task = getTask(taskName);
        synchronized (this) {
            validator.validateState(task, status);
            task.setStatus(status);
            System.out.println("Task: status updated successfully");
            taskRepository.updateTask(taskName, task);
        }
    }
}
