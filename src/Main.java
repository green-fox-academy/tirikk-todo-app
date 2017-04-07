public class Main {
  public static void main(String[] args) {
    TaskHandler tasks = new TaskHandler();
    tasks.createTasks();

    ArgsHandler arguments = new ArgsHandler();
    arguments.checkArgs(args);

    tasks.writeFile();
  }
}
