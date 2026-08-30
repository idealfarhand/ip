/** A task that takes place over a specified time range. */
public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /** Returns the event start text for persistent storage. */
    public String getFrom() {
        return from;
    }

    /** Returns the event end text for persistent storage. */
    public String getTo() {
        return to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
