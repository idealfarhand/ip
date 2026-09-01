package hamtaro;

import java.util.Scanner;
import java.util.List;

import hamtaro.task.Task;
import hamtaro.task.TaskList;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        String banner = "    __  __                __                 \n"
                + "   / / / /___ _____ ___  / /_____ __________ \n"
                + "  / /_/ / __ `/ __ `__ \\/ __/ __ `/ ___/ __ \\\n"
                + " / __  / /_/ / / / / / / /_/ /_/ / /  / /_/ /\n"
                + "/_/ /_/\\__,_/_/ /_/ /_/\\__/\\__,_/_/   \\____/ \n";
        System.out.println(banner);
        System.out.println("Hello! I'm Hamtaro!.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /** Reads one complete command from standard input. */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message){
        showLine();
        System.out.println(message);
        showLine();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list");
        showLine();
    }

    public void showTaskMarked(Task task) {
        showLine();
        System.out.println("Ok! I've marked this task as done: ");
        System.out.println(task.toString());
        showLine();
    }

    public void showTaskUnmarked(Task task) {
        showLine();
        System.out.println("Ok! I've marked this task as undone: ");
        System.out.println(task.toString());
        showLine();
    }

    public void showTaskDeleted(Task task) {
        showLine();
        System.out.println("Okay! I've deleted this task: ");
        System.out.println(task.toString());
        showLine();
    }

    public void showTaskList(TaskList tasks) {
        for(int i = 0; i < tasks.getSize();i++){
            System.out.println( (i+1) + ". " +  tasks.getTask(i).toString());
        }
    }

    /** Displays the tasks whose descriptions matched a find keyword. */
    public void showMatchingTasks(List<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        showLine();
    }

    public void showLine(){
        System.out.println("____________________________________________________________");
    }
}
