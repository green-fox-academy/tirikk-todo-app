import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  static List<String> tasks;
  static List<Task> taskList = new ArrayList<>();

  public static void main(String[] args) {

    try {
      Path filePath = Paths.get("src/tasks.csv");
      tasks = Files.readAllLines(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    createTasks();

    String[] arguments = {"-l", "-a", "-r", "-c"};
    if (args.length == 0) {
      printUsage();
    } else if (!Arrays.asList(arguments).contains(args[0])) {
      System.out.println("Unsupported argument");
    } else if (args[0].equals("-l")) {
      listTasks();
    } else if (args[0].equals("-a")) {
      if (Arrays.asList(args).indexOf("-a") == args.length - 1) {
        System.out.println("Unable to add: no task provided");
      } else {
        String parameter = args[Arrays.asList(args).indexOf("-a") + 1];
        addTask(parameter);
      }
    } else if (args[0].equals("-r")) {
      if (Arrays.asList(args).indexOf("-r") == args.length - 1) {
        System.out.println("Unable to remove: no index provided");
      } else {
        String parameter = args[Arrays.asList(args).indexOf("-r") + 1];
        removeTask(parameter);
      }
    } else if (args[0].equals("-c")) {
      if (Arrays.asList(args).indexOf("-r") == args.length - 1) {
        System.out.println("Unable to check: no index provided");
      } else {
        String parameter = args[Arrays.asList(args).indexOf("-c") + 1];
        checkTask(parameter);
      }
    }

    writeFile();
  }

  private static void printUsage() {
    System.out.println("Java Todo application\n" +
            "=====================\n" +
            "\n" +
            "Command line arguments:\n" +
            " -l   Lists all the tasks\n" +
            " -a   Adds a new task\n" +
            " -r   Removes an task\n" +
            " -c   Completes an task");
  }

  private static void writeFile() {
    List<String> temp = new ArrayList<>();
    for (Task task :  taskList) {
      temp.add(task.getState() + ";" + task.getDesc());
    }
    try {
      Files.write(Paths.get("src/tasks.csv"), temp);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void createTasks() {
    for (String task : tasks) {
      if (task.contains(";")) {
        List<String> temp = Arrays.asList(task.split(";"));
        taskList.add(new Task(temp.get(0), temp.get(1)));
      } else {
        taskList.add(new Task(task));
      }
    }
  }

  private static void listTasks() {
    if (taskList.isEmpty()) {
      System.out.println("No todos for today! :)");
    } else {
      for (Task task : taskList) {
        if (task.getState() == 1) {
          System.out.println((taskList.indexOf(task) + 1) + " - [x] " + task.getDesc());
        } else {
          System.out.println((taskList.indexOf(task) + 1) + " - [ ] " + task.getDesc());
        }
      }
    }
  }

  private static void addTask(String task) {
//    tasks.add(task);
    taskList.add(new Task(task));
  }

  private static void removeTask(String i) {
    try {
      taskList.remove(Integer.valueOf(i) - 1);
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
}
