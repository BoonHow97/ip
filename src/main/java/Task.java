/**
 * Represents one task and whether it has been completed.
 */
public class Task {
    /** The text entered by the user for this task. */
    protected String description;
    /** Whether this task is complete. */
    protected boolean isDone;
    /** The type of task: T for ToDo, D for Deadline, or E for Event. */
    protected char type;
    /** The deadline date or time, when this is a deadline task. */
    protected String by;
    /** The event start date or time, when this is an event task. */
    protected String from;
    /** The event end date or time, when this is an event task. */
    protected String to;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description the text describing the task
     */
    public Task(String description) {
        this('T', description, "", "", "");
    }

    /**
     * Creates an incomplete task with its type-specific time information.
     *
     * @param type the task type: T, D, or E
     * @param description the text describing the task
     * @param by the deadline date or time
     * @param from the event start date or time
     * @param to the event end date or time
     */
    public Task(char type, String description, String by, String from, String to) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.by = by;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the icon used to display this task's completion status.
     *
     * @return {@code "X"} when complete, otherwise a space
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns this task's description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns this task in the format used by the task list.
     *
     * @return the type, status, description, and applicable time information
     */
    public String getListDisplay() {
        String taskDetails = description;
        if (type == 'D') {
            taskDetails += " (by: " + by + ")";
        } else if (type == 'E') {
            taskDetails += " (from: " + from + " to: " + to + ")";
        }
        return "[" + type + "][" + getStatusIcon() + "] " + taskDetails;
    }

    /** Marks this task as complete. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markAsNotDone() {
        isDone = false;
    }
}
