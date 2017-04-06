import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Task {
  String state;
  String description;

  public Task(String description) {
    this.state = "done";
    this.description = description;
    try {
      Files.write(Paths.get("src/tasks.csv"), (description + "\n").getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
