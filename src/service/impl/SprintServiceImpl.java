package service.impl;

import constants.Status;
import exceptions.MoreThan2TaskInSprintException;
import exceptions.TooManyTaskInSprintException;
import models.Task;
import repository.SprintRepository;
import service.SprintService;
import service.TaskService;
import service.validator.SprintValidator;
import service.validator.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;
    private final TaskService taskService;

    private final Validator validator;

    public SprintServiceImpl(SprintRepository sprintRepository, TaskService taskService) {
        this.sprintRepository = sprintRepository;
        this.taskService = taskService;
        this.validator = new SprintValidator(taskService, sprintRepository);
    }

    @Override
    public void createSprint(String sprintName, long startTime, long endTime, String description) {
        sprintRepository.createSprint(sprintName, startTime, endTime, description);
    }

    @Override
    public synchronized void addTaskToSprint(String taskId, String sprintName) throws TooManyTaskInSprintException, MoreThan2TaskInSprintException {
        validator.validate(taskId, sprintName);

        Task task = taskService.getTask(taskId);
        sprintRepository.addTaskToSprint(task, sprintName);
    }

    /**
     * Have been moved to the validator
     * @param taskId
     * @param sprintName
     * @throws MoreThan2TaskInSprintException
     */

    private void validateIfMoreThan2Task(String taskId, String sprintName) throws MoreThan2TaskInSprintException {

        String user = taskService.getUser(taskId);
        if (user == null) {
            return;
        }
        List<Task> taskList = sprintRepository.getTaskInSprint(sprintName);
        List<Task> finalTaskList = taskList.stream().filter(task1 -> {
            String userDoing = taskService.getUser(task1.getTaskName());
            if (user.equals(userDoing)) {
                return true;
            }
            return false;
        }).filter(task1 -> task1.getStatus()!=Status.RESOLVED).toList();
        if (finalTaskList.size()>2) {
            throw new MoreThan2TaskInSprintException();
        }
    }

    @Override
    public void removeTaskFromSprint(String taskId, String sprintName) {
        Task task = taskService.getTask(taskId);
        sprintRepository.removeTaskFromSprint(task, sprintName);

    }

    @Override
    public List<Task> showTasksAssigned(String userId, String sprint) {
        List<Task> taskList = sprintRepository.getTaskInSprint(sprint);
        return taskList.stream().filter(task -> {
            String user = taskService.getUser(task.getTaskName());
            if (user!=null) {
                return user.equals(userId);
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Task> showTasksDelayed(String sprint) {
        long currentTime  = System.currentTimeMillis();
        List<Task> taskList = sprintRepository.getTaskInSprint(sprint);
        return taskList.stream()
                .filter(task -> !task.getStatus().equals(Status.RESOLVED) && currentTime> task.getDueDate())
                .collect(Collectors.toList());
    }


}
