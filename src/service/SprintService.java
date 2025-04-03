package service;

import exceptions.MoreThan2TaskInSprintException;
import exceptions.TooManyTaskInSprintException;
import models.Task;

import java.util.List;

public interface SprintService {

    void createSprint(String sprintName, long startTime, long endTime, String description);

    void addTaskToSprint(String taskId, String sprintName) throws TooManyTaskInSprintException, MoreThan2TaskInSprintException;

    void removeTaskFromSprint(String taskId, String sprintName);

    List<Task> showTasksAssigned(String user, String sprint);

    List<Task> showTasksDelayed(String sprint);

}
