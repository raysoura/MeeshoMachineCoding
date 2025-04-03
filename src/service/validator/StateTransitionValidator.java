package service.validator;

import constants.Status;
import exceptions.InvalidStatusTransition;
import exceptions.MoreThan2TaskInSprintException;
import exceptions.TooManyTaskInSprintException;
import models.Task;
import utils.StatusTransitionEngine;

public class StateTransitionValidator implements Validator {
    @Override
    public void validate(String taskId, String sprintName) throws MoreThan2TaskInSprintException, TooManyTaskInSprintException {
    }

    @Override
    public void validateState(Task task, Status status) throws InvalidStatusTransition {
        if (!StatusTransitionEngine.isStatusTransitionAllowed(status, task.getStatus())) {
            throw new InvalidStatusTransition();
        }
    }
}
