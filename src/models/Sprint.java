package models;

public class Sprint {
    String sprintName;
    long startTime;
    long endTime;

    public Sprint(String sprintName, long startTime, long endTime, String description) {
        this.sprintName = sprintName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    String description;
}
