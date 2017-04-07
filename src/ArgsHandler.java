import java.util.ArrayList;
import java.util.Arrays;

class ArgsHandler {

  void checkArgs(String[] args) {
    String[] arguments = {"-l", "-la", "-a", "-r", "-c"};
    if (args.length == 0) {
      printUsage();
    } else if (!Arrays.asList(arguments).contains(args[0])) {
      System.out.println("Unsupported argument");
    } else if (args[0].equals("-l")) {
      TaskHandler.listUndoneTasks();
    } else if (args[0].equals("-la")) {
      TaskHandler.listTasks();
    } else if (args[0].equals("-a")) {
      if (Arrays.asList(args).indexOf("-a") == args.length - 1) {
        System.out.println("Unable to add: no task provided");
      } else {
        for (int i = Arrays.asList(args).indexOf("-a") + 1; i < args.length; i++) {
          String parameter = args[i];
          TaskHandler.addTask(parameter);
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
        TaskHandler.removeTask(indexes);
      }
    } else if (args[0].equals("-c")) {
      if (Arrays.asList(args).indexOf("-r") == args.length - 1) {
        System.out.println("Unable to check: no index provided");
      } else {
        for (int i = Arrays.asList(args).indexOf("-c") + 1; i < args.length; i++) {
          String parameter = args[i];
          TaskHandler.checkTask(parameter);
        }
      }
    }
  }

  private void printUsage() {
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
}
