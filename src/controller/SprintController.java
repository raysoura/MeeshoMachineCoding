package controller;

import exceptions.MoreThan2TaskInSprintException;
import exceptions.TooManyTaskInSprintException;
import models.Task;
import service.SprintService;

import java.util.List;

public class SprintController {
    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    public void createSprint(String sprintName, long startTime, long endTime, String description) {
        sprintService.createSprint(sprintName,startTime, endTime, description);
    }

    public void addTaskToSprint(String taskId, String sprintName)  {
        try {
            sprintService.addTaskToSprint(taskId, sprintName);
        } catch (TooManyTaskInSprintException e) {
            System.out.println("Facing too many tasks in sprint ");
        } catch (MoreThan2TaskInSprintException e) {
            System.out.println("Facing more than 2 tasks in sprint for a user");
            throw new RuntimeException(e);
        }
    }

    public void removeTaskFromSprint(String taskId, String sprintName) {
        sprintService.removeTaskFromSprint(taskId, sprintName);
    }

    public List<Task> showTasksAssigned(String user, String sprint) {
        return sprintService.showTasksAssigned(user, sprint);
    }

    public List<Task> showTasksDelayed(String sprint) {
        return sprintService.showTasksDelayed(sprint);
    }
}
