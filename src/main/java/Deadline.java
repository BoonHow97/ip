/**
 * Represents a task that must be completed by a specified time.
 */
public class Deadline extends Task {
    /** The deadline date or time. */
    protected String by;

    /**
     * Creates an incomplete deadline task.
     *
     * @param description the text describing the task
     * @param by the deadline date or time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline type, status, description, and deadline.
     *
     * @return the formatted deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
