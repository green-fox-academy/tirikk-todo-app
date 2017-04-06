class Task {
  private int state;
  private String description;

  Task(String description) {
    this.state = 0;
    this.description = description;
  }

  Task(String i, String description) {
    this.description = description;
    if (Integer.valueOf(i) == 1) {
      this.state = 1;
    } else if (Integer.valueOf(i) == 0) {
      this.state = 0;
    }
  }

  void checkTask() {
    state = 1;
  }

  int getState() {
    return state;
  }

  String getDesc() {
    return description;
  }
}
