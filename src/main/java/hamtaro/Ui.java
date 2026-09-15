package hamtaro;

import java.util.Scanner;
import java.util.List;

import hamtaro.task.Task;
import hamtaro.task.TaskList;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    private final String line = "____________________________________________________________";

    public String showWelcome() {

        String welcome = line
                + "    __  __                __                 \n"
                + "   / / / /___ _____ ___  / /_____ __________ \n"
                + "  / /_/ / __ `/ __ `__ \\/ __/ __ `/ ___/ __ \\\n"
                + " / __  / /_/ / / / / / / /_/ /_/ / /  / /_/ /\n"
                + "/_/ /_/\\__,_/_/ /_/ /_/\\__/\\__,_/_/   \\____/ \n"
                + "Hello! I'm Hamtaro!.\n"
                + "What can I do for you?\n"
                + line;

        return welcome;
    }

    /** Reads one complete command from standard input. */
    public String readCommand() {
        return scanner.nextLine();
    }

    public String showGoodbye() {

        String goodbye = line
                + "Bye. Hope to see you again soon!\n"
                + line;

        return goodbye;
    }

    public String showError(String message) {
        String error = line
                + message
                + line;

        return error;
    }

    public String showTaskAdded(Task task, int totalTasks) {

        String taskAdded = line
                + "Got it. I've added this task:\n"
                + task.toString()
                + "Now you have " + totalTasks + " tasks in the list\n"
                + line;

        return taskAdded;
    }

    public String showTaskMarked(Task task) {

        String taskMarked = line
                + "Ok! I've marked this task as done: \n"
                + task.toString()
                + line;

        return taskMarked;
    }

    public String showTaskUnmarked(Task task) {

        String taskUnmarked = line
                + "Ok! I've marked this task as undone: \n"
                + task.toString()
                + line;

        return taskUnmarked;
    }

    /** Displays a successful tag addition. */
    public String showTagAdded(Task task, String tag) {
        return line + "Added #" + tag + " to this task:\n" + task + line;
    }

    /** Displays a successful tag removal. */
    public String showTagRemoved(Task task, String tag) {
        return line + "Removed #" + tag + " from this task:\n" + task + line;
    }

    public String showTaskDeleted(Task task) {

        String deleted = line + "Okay! I've deleted this task: "
                + task.toString()
                + line;

        return deleted;
    }

    public String showTaskList(TaskList tasks) {

        String taskList = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            taskList += (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }

        return taskList;
    }

    /** Displays the tasks whose descriptions matched a find keyword. */
    public String showMatchingTasks(List<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            String noMatch = line + "No matching tasks found." + line;
            return noMatch;
        } else {
            String match = line + "Here are the matching tasks in your list: ";
            for (int i = 0; i < matchingTasks.size(); i++) {
                match += (i + 1) + ". " + matchingTasks.get(i);
            }

            match += line;
            return match;
        }
    }

    public String showLine() {
        return "____________________________________________________________";
    }
}
