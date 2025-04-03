package service.validator;

import constants.Status;
import exceptions.InvalidStatusTransition;
import exceptions.MoreThan2TaskInSprintException;
import exceptions.TooManyTaskInSprintException;
import models.Task;

public interface Validator {

    void validate(String taskId, String sprintName) throws MoreThan2TaskInSprintException, TooManyTaskInSprintException;

    void validateState(Task task, Status status) throws InvalidStatusTransition;
}
