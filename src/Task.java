public class Task {
  private int state;
  private String description;

  public Task(String description) {
    this.state = 0;
    this.description = description;
  }

  public Task(String i, String description) {
    this.description = description;
    if (Integer.valueOf(i) == 1) {
      this.state = 1;
    } else if (Integer.valueOf(i) == 0) {
      this.state = 0;
    }
  }

  public void checkTask() {
    state = 1;
  }

  public int getState() {
    return state;
  }

  public String getDesc() {
    return description;
  }
}
