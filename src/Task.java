import java.util.ArrayList;

public class Task {
  private boolean state;
  private String description;
  ArrayList<Task> taskList = new ArrayList<>();

  public Task(String description) {
    this.state = false;
    this.description = description;
  }

  public Task(String i, String description) {
    this.description = description;
    if (Integer.valueOf(i) == 1) {
      this.state = true;
    } else if (Integer.valueOf(i) == 0) {
      this.state = false;
    }
  }

  public void checkTask() {
    state = true;
  }

  public boolean getState() {
    return state;
  }
}
