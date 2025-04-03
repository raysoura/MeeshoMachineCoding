import constants.Status;
import constants.TaskType;
import controller.SprintController;
import controller.TaskController;
import exceptions.InvalidStatusTransition;
import exceptions.MoreThan2TaskInSprintException;
import exceptions.TooManyTaskInSprintException;
import models.Task;
import repository.SprintRepository;
import repository.TaskRepository;
import service.SprintService;
import service.TaskService;
import service.impl.SprintServiceImpl;
import service.impl.TaskServiceImpl;
import service.validator.SprintValidator;

import java.util.List;

public class Main {
    public static void main(String[] args) throws TooManyTaskInSprintException, InvalidStatusTransition, MoreThan2TaskInSprintException {
        System.out.println("Hello world!");
        SprintRepository sprintRepository = new SprintRepository();


        TaskRepository taskRepository = new TaskRepository();
        TaskService taskService = new TaskServiceImpl(taskRepository);
        SprintValidator validator = new SprintValidator(taskService, sprintRepository);
        SprintService sprintService = new SprintServiceImpl(sprintRepository, taskService);
        SprintController sprintController = new SprintController(sprintService);
        TaskController taskController = new TaskController(taskService);


        sprintController.createSprint("abc",1l, 2l, "sample" );
        sprintController.createSprint("def",1l, 2l, "sample" );
        taskController.createTask("task1", TaskType.FEATURE, 3l);
        taskController.createTask("task2", TaskType.BUG, 3l);
        taskController.createTask("task3", TaskType.BUG, 3l);
        taskController.createTask("task4", TaskType.BUG, 3l);

        System.out.println();


        taskController.assignTask("task1", "Souradeep");
        taskController.assignTask("task2", "Souradeep");
        taskService.assignTask("task3", "Smriti");
        taskService.assignTask("task4", "Smriti");

        System.out.println();


        sprintController.addTaskToSprint("task1", "def");
        sprintController.addTaskToSprint("task2", "def");
        sprintController.addTaskToSprint("task3", "def");
        sprintController.addTaskToSprint("task4", "def");

        System.out.println();

//        taskController.updateStatus("task4", Status.IN_PROGRESS);
//        taskController.updateStatus("task4", Status.RESOLVED);
//
        taskService.updateStatus("task1", Status.IN_PROGRESS);
        taskController.updateStatus("task1", Status.RESOLVED);




        System.out.println("Tasks assigned to Smriti in def");
        List<Task> taskList = sprintController.showTasksAssigned("Smriti", "def");
        taskList.stream().forEach(task -> System.out.println("Task: "+task.getTaskName()));
//        sprintService.removeTaskFromSprint("task2", "abc");
        System.out.println();



        System.out.println("Tasks assigned to Souradeep in def");
        List<Task> taskList2 = sprintService.showTasksAssigned("Souradeep", "def");
        taskList2.stream().forEach(task -> System.out.println("Task: "+task.getTaskName()));

        System.out.println();
        System.out.println("Delayed tasks");



        List<Task> taskList3 = sprintController.showTasksDelayed("def");
        taskList3.stream().forEach(task -> System.out.println("Task: "+task.getTaskName()));

    }
}