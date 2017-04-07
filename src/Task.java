class Task {
  private boolean completed;
  private String description;

  Task(String description) {
    this.completed = false;
    this.description = description;
  }

  Task(String i, String description) {
    this.description = description;
    if (i.equals("true")) {
      this.completed = true;
    } else {
      this.completed = false;
    }
  }

  void checkTask() {
    if (completed) {
      completed = false;
    } else {
      completed = true;
    }
  }

  boolean getState() {
    return completed;
  }

  String getDesc() {
    return description;
  }
}
