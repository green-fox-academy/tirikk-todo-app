import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      printUsage();
    } else if (args[0].equals("-l")) {
      listTasks();
    }
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

  private static void listTasks() {
    try {
      Path filePath = Paths.get("src/tasks.csv");
      List<String> tasks = Files.readAllLines(filePath);
      for (String task : tasks) {
        System.out.println((tasks.indexOf(task) + 1) + " - " + task);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
