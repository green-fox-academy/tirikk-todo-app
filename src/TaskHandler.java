import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TaskHandler {
  private static List<Task> taskList = new ArrayList<>();

  void createTasks() {
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

  static void listUndoneTasks() {
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

  static void listTasks() {
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

  static void addTask(String task) {
    taskList.add(new Task(task));
  }

  static void removeTask(ArrayList<String> list) {
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

  static void checkTask(String i) {
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

  void writeFile() {
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
