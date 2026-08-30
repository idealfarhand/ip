import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Storage {
    private static final Path FILE_PATH = Path.of("data", "hamtaro.txt");

    public static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }

        for (String line : Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8)) {
            if (!line.isBlank()) {
                tasks.add(decodeTask(line));
            }
        }
        return tasks;
    }

    public static void saveTasks(List<Task> tasks) throws IOException {
        Path parent = FILE_PATH.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(encodeTask(task));
        }
        Files.write(FILE_PATH, lines, StandardCharsets.UTF_8);
    }

    private static String encodeTask(Task task) {
        String done = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + done + " | " + encode(task.getDescription());
        } else if (task instanceof Deadline deadline) {
            return "D | " + done + " | " + encode(task.getDescription()) + " | " + encode(deadline.getBy());
        } else if (task instanceof Event event) {
            return "E | " + done + " | " + encode(task.getDescription()) + " | "
                    + encode(event.getFrom()) + " | " + encode(event.getTo());
        }
        throw new IllegalArgumentException("Unsupported task type: " + task.getClass().getSimpleName());
    }

    private static Task decodeTask(String line) throws IOException {
        String[] fields = line.split(" \\| ");
        boolean isDone = switch (fields.length > 1 ? fields[1] : "") {
        case "1" -> true;
        case "0" -> false;
        default -> throw new IOException("Invalid completion status in saved task: " + line);
        };

        try {
            if (fields[0].equals("T") && fields.length == 3) {
                return new Todo(decode(fields[2]), isDone);
            } else if (fields[0].equals("D") && fields.length == 4) {
                return new Deadline(decode(fields[2]), decode(fields[3]), isDone);
            } else if (fields[0].equals("E") && fields.length == 5) {
                return new Event(decode(fields[2]), decode(fields[3]), decode(fields[4]), isDone);
            }
            throw new IOException("Invalid saved task: " + line);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid saved task: " + line, e);
        }
    }

    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private static String decode(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }
}
