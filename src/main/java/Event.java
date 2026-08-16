/**
 * Represents a task that occurs between a start and end time.
 */
public class Event extends Task {
    /** The event start date or time. */
    protected String from;
    /** The event end date or time. */
    protected String to;

    /**
     * Creates an incomplete event task.
     *
     * @param description the text describing the task
     * @param from the event start date or time
     * @param to the event end date or time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the event type, status, description, and time range.
     *
     * @return the formatted event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
