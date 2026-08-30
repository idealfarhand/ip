/** A task that must be completed by a specified time. */
public class Deadline extends Task{
    protected String by;


    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /** Returns the deadline text for persistent storage. */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
