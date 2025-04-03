package repository;

import models.Sprint;
import models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SprintRepository {
    private final Map<String, Sprint> sprintMap;
    private final Map<String, List<Task>> sprintTaskMap;

    public SprintRepository() {
        this.sprintMap = new ConcurrentHashMap<>();
        this.sprintTaskMap = new ConcurrentHashMap<>();
    }

    public synchronized void createSprint(String sprintName, long startTime, long endTime, String description) {
        Sprint sprint = new Sprint(sprintName, startTime, endTime, description);
        System.out.println("Created sprint with name: "+ sprintName);
        sprintMap.put(sprintName, sprint);
    }

    public void addTaskToSprint(Task task, String sprintName) {
        List<Task> taskList = sprintTaskMap.getOrDefault(sprintName, new ArrayList<>());
        taskList.add(task);
        System.out.println("Added task "+ task.getTaskName() +" to the sprint: "+ sprintName);
        synchronized (this) {
            sprintTaskMap.put(sprintName, taskList);
        }


    }

    public synchronized void removeTaskFromSprint(Task taskId, String sprintName) {
        //Handle if not present
        List<Task> taskList = sprintTaskMap.getOrDefault(sprintName, new ArrayList<>());
        taskList.remove(taskId);
        sprintTaskMap.put(sprintName, taskList);

    }

    public List<Task> getTaskInSprint(String sprint) {
        return sprintTaskMap.getOrDefault(sprint, new ArrayList<>());
    }
}
