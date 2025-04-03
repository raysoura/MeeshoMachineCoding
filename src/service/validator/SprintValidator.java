package service.validator;

import constants.Status;
import exceptions.InvalidStatusTransition;
import exceptions.MoreThan2TaskInSprintException;
import exceptions.TooManyTaskInSprintException;
import models.Task;
import repository.SprintRepository;
import service.TaskService;

import java.util.List;

public class SprintValidator implements Validator {
    private final TaskService taskService;
    private final SprintRepository sprintRepository;

    public SprintValidator(TaskService taskService, SprintRepository sprintRepository) {
        this.taskService = taskService;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public void validate(String taskId, String sprintName) throws MoreThan2TaskInSprintException, TooManyTaskInSprintException {
        validateNotMoreThan2OngoingTaskForUserInSprint(taskId, sprintName);
        validateMaxAllowedStoriesInSprint(sprintName);
    }

    @Override
    public void validateState(Task task, Status status) throws InvalidStatusTransition {
    }

    private void validateNotMoreThan2OngoingTaskForUserInSprint(String taskId, String sprintName) throws MoreThan2TaskInSprintException {
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
        }).filter(task1 -> task1.getStatus()!= Status.RESOLVED).toList();
        if (finalTaskList.size()>2) {
            throw new MoreThan2TaskInSprintException();
        }
    }

    private void validateMaxAllowedStoriesInSprint(String sprintName) throws TooManyTaskInSprintException {
        List<Task> taskList2 = sprintRepository.getTaskInSprint(sprintName);
//        validateIfMoreThan2Task(taskId, sprintName);
        if (taskList2.size() > 20) {
            throw new TooManyTaskInSprintException();
        }
    }

}
