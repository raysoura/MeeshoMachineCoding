package models;

import constants.Status;
import constants.TaskType;

public class Task {
    String taskName;
    TaskType taskType;
    Status status;

    Long dueDate;

    public Task(String taskName, TaskType taskType, Status status, Long dueDate) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.status = status;
        this.dueDate = dueDate;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }



    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public Task(String taskName, TaskType taskType, Status status) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.status = status;
    }
}
