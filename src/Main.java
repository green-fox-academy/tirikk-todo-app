import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  private static List<Task> taskList = new ArrayList<>();

  public static void main(String[] args) {

    createTasks();

    String[] arguments = {"-l", "-la", "-a", "-r", "-c"};
    if (args.length == 0) {
      printUsage();
    } else if (!Arrays.asList(arguments).contains(args[0])) {
      System.out.println("Unsupported argument");
    } else if (args[0].equals("-l")) {
      listUndoneTasks();
    } else if (args[0].equals("-la")) {
      listTasks();
    } else if (args[0].equals("-a")) {
      if (Arrays.asList(args).indexOf("-a") == args.length - 1) {
        System.out.println("Unable to add: no task provided");
      } else {
        for (int i = Arrays.asList(args).indexOf("-a") + 1; i < args.length; i++) {
          String parameter = args[i];
          addTask(parameter);
        }
      }
    } else if (args[0].equals("-r")) {
      if (Arrays.asList(args).indexOf("-r") == args.length - 1) {
        System.out.println("Unable to remove: no index provided");
      } else {
        ArrayList<String> indexes = new ArrayList<>();
        for (int i = Arrays.asList(args).indexOf("-r") + 1; i < args.length; i++) {
          indexes.add(args[i]);
        }
        removeTask(indexes);
      }
    } else if (args[0].equals("-c")) {
      if (Arrays.asList(args).indexOf("-r") == args.length - 1) {
        System.out.println("Unable to check: no index provided");
      } else {
        for (int i = Arrays.asList(args).indexOf("-c") + 1; i < args.length; i++) {
          String parameter = args[i];
          checkTask(parameter);
        }
      }
    }

    writeFile();
  }

  private static void printUsage() {
    System.out.println("Java Todo application\n" +
            "=====================\n" +
            "\n" +
            "Command line arguments:\n" +
            " -l   Lists unfinished tasks\n" +
            " -la  Lists all the tasks\n" +
            " -a   Adds a new task\n" +
            " -r   Removes a task\n" +
            " -c   Sets a task as finished/unfinished");
  }

  private static void createTasks() {
    List<String> initList;
    try {
      Path filePath = Paths.get("src/tasks.csv");
      initList = Files.readAllLines(filePath);
      for (String task : initList) {
        List<String> temp = Arrays.asList(task.split(";"));
        taskList.add(new Task(temp.get(0), temp.get(1)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void listUndoneTasks() {
    int toDo = 0;
    for (Task task : taskList) {
      if (!task.getState()) {
        toDo++;
      }
    }
    if (toDo == 0) {
      System.out.println("No tasks to do!");
    } else {
      for (Task task : taskList) {
        if (!task.getState()) {
          System.out.println((taskList.indexOf(task) + 1) + " - [ ] " + task.getDesc());
        }
      }
    }
  }

  private static void listTasks() {
    if (taskList.isEmpty()) {
      System.out.println("No tasks at all!");
    } else {
      for (Task task : taskList) {
        if (task.getState()) {
          System.out.println((taskList.indexOf(task) + 1) + " - [x] " + task.getDesc());
        } else {
          System.out.println((taskList.indexOf(task) + 1) + " - [ ] " + task.getDesc());
        }
      }
    }
  }

  private static void addTask(String task) {
    taskList.add(new Task(task));
  }

  private static void removeTask(ArrayList<String> list) {
    try {
      List<Task> buffer = new ArrayList<>();
      for (String i : list) {
        buffer.add(taskList.get(Integer.valueOf(i) - 1));
      }
      for (Task task : buffer) {
        int indexBuffer = taskList.indexOf(task);
        taskList.remove(indexBuffer);
      }
    } catch (IndexOutOfBoundsException | NumberFormatException d) {
      if (d.getClass().equals(NumberFormatException.class)) {
        System.out.println("Unable to remove: index is not a number!");
      } else if (d.getClass().equals(IndexOutOfBoundsException.class)) {
        System.out.println("Unable to remove: index is out of bound!");
      } else {
        d.printStackTrace();
      }
    }
  }

  private static void checkTask(String i) {
    try {
      Task temp = taskList.get(Integer.valueOf(i) - 1);
      temp.checkTask();
      taskList.set(Integer.valueOf(i) - 1, temp);
    } catch (IndexOutOfBoundsException | NumberFormatException d) {
      if (d.getClass().equals(NumberFormatException.class)) {
        System.out.println("Unable to check: index is not a number!");
      } else if (d.getClass().equals(IndexOutOfBoundsException.class)) {
        System.out.println("Unable to check: index is out of bound!");
      } else {
        d.printStackTrace();
      }
    }
  }

  private static void writeFile() {
    List<String> temp = new ArrayList<>();
    for (Task task : taskList) {
      temp.add(task.getState() + ";" + task.getDesc());
    }
    try {
      Files.write(Paths.get("src/tasks.csv"), temp);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
