import java.util.ArrayList;

/** Represents one validated action requested by the user. */
public abstract class Command {
    /** Performs this command using the current task list and user interface. */
    public abstract void execute(TaskList tasks, Ui ui) throws HamtaroException;

    /** Returns whether this command changes the task list. */
    public boolean changesTasks() {
        return false;
    }

    /** Returns whether this command ends the application. */
    public boolean isExit() {
        return false;
    }
}

/** Adds a parsed task to the task list. */
class AddTaskCommand extends Command {
    private final Task task;

    AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
    }

    @Override
    public boolean changesTasks() {
        return true;
    }
}

/** Changes one task's completion status. */
class MarkCommand extends Command {
    private final int taskNumber;
    private final boolean shouldMark;

    MarkCommand(int taskNumber, boolean shouldMark) {
        this.taskNumber = taskNumber;
        this.shouldMark = shouldMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws HamtaroException {
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new HamtaroException("Task number " + taskNumber + " does not exist!");
        }
        Task task = tasks.getTask(taskNumber - 1);
        if (shouldMark) {
            task.mark();
            ui.showTaskMarked(task);
        } else {
            task.unmark();
            ui.showTaskUnmarked(task);
        }
    }

    @Override
    public boolean changesTasks() {
        return true;
    }
}

/** Deletes one task from the task list. */
class DeleteCommand extends Command {
    private final int taskNumber;

    DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws HamtaroException {
        if (taskNumber < 1 || taskNumber > tasks.getSize()) {
            throw new HamtaroException("Task number " + taskNumber + " does not exist!");
        }
        ui.showTaskDeleted(tasks.deleteTask(taskNumber - 1));
    }

    @Override
    public boolean changesTasks() {
        return true;
    }
}

/** Displays all current tasks. */
class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws HamtaroException {
        if (tasks.isEmpty()) {
            throw new HamtaroException("Task list is empty! Add some tasks first!");
        }
        ui.showTaskList(tasks);
    }
}

/** Displays a farewell and ends the application. */
class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
