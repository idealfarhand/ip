package hamtaro.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import hamtaro.task.Deadline;
import hamtaro.task.Event;
import hamtaro.task.Task;
import hamtaro.task.TaskList;
import hamtaro.task.Todo;
import hamtaro.util.Utils;

/** Saves tasks to and loads tasks from the application's local data file. */
public class Storage {
    private static final Path FILE_PATH = Path.of("data", "hamtaro.txt");

    /** Loads all persisted tasks, returning an empty list when no data file exists. */
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

    /** Saves every task in the supplied list to the local data file. */
    public static void saveTasks(TaskList tasks) throws IOException {
        Path parent = FILE_PATH.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> lines = new ArrayList<>();
        for(int i = 0; i < tasks.getSize(); i++){
            lines.add(encodeTask(tasks.getTask(i)));
        }

        Files.write(FILE_PATH, lines, StandardCharsets.UTF_8);
    }

    /** Converts a task into the pipe-delimited format used in the data file. */
    private static String encodeTask(Task task) {
        String done = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + done + " | " + task.getDescription();
        } else if (task instanceof Deadline deadline) {
            return "D | " + done + " | " + task.getDescription() + " | " + deadline.getBy().toString();
        } else if (task instanceof Event event) {
            return "E | " + done + " | " + task.getDescription() + " | "
                    + event.getFrom() + " | " + event.getTo();
        }
        throw new IllegalArgumentException("Unsupported task type: " + task.getClass().getSimpleName());
    }

    /** Recreates one task from a pipe-delimited line in the data file. */
    private static Task decodeTask(String line) throws IOException {
        String[] fields = line.split(" \\| ");
        boolean isDone = switch (fields.length > 1 ? fields[1] : "") {
        case "1" -> true;
        case "0" -> false;
        default -> throw new IOException("Invalid completion status in saved task: " + line);
        };

        try {
            if (fields[0].equals("T") && fields.length == 3) {
                return new Todo(fields[2], isDone);
            } else if (fields[0].equals("D") && fields.length == 4) {
                return new Deadline(fields[2], Utils.parseDate(fields[3]), isDone);
            } else if (fields[0].equals("E") && fields.length == 5) {
                return new Event(fields[2], Utils.parseDateTime(fields[3]), Utils.parseDateTime(fields[4]), isDone);
            }
            throw new IOException("Invalid saved task: " + line);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid saved task: " + line, e);
        }
    }


}
