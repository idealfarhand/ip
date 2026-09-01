package hamtaro.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** A task that takes place over a specified time range. */
public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    /** Creates an incomplete event with a start and end time. */
    public Event(String description, LocalDateTime from, LocalDateTime to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /** Creates an event with its completion status restored from storage. */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /** Returns the event start text for persistent storage. */
    public LocalDateTime getFrom() {
        return from;
    }

    /** Returns the event end text for persistent storage. */
    public LocalDateTime getTo() {
        return to;
    }

    /** Returns a displayable representation of this event. */
    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + " (from: " + this.from.format(dtf) + " to: " + this.to.format(dtf) + ")";
    }
}
