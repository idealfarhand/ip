package hamtaro.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Stores and provides access to the tasks currently managed by the application. */
public class TaskList {
    private ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /** Creates a task list containing the supplied tasks. */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Adds a task to the end of this list. */
    public void addTask(Task task){
        tasks.add(task);
    }

    /** Removes and returns the task at the specified zero-based index. */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /** Returns the number of tasks in this list. */
    public int getSize() {
        return this.tasks.size();
    }

    /** Returns the task at the specified zero-based index. */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /** Returns whether this list contains no tasks. */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns tasks whose descriptions contain the supplied keyword, ignoring case.
     *
     * @param keyword text to search for in task descriptions
     * @return matching tasks in their existing list order
     */
    public List<Task> findTasks(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ROOT);
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase(Locale.ROOT).contains(normalizedKeyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
