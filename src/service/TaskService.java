package service;

import constants.Status;
import constants.TaskType;
import exceptions.InvalidStatusTransition;
import models.Task;

public interface TaskService {

    void createTask(String taskName, TaskType taskType, long dueDate);

    void updateStatus(String taskName, Status status) throws InvalidStatusTransition;

   Task getTask(String taskName);

    void assignTask(String task1, String souradeep);

    String getUser(String task1);
}
