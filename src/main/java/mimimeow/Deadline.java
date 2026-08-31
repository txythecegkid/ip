package mimimeow;

/** Represents a task that must be completed by a specified deadline. */
public class Deadline extends Task {

    private final String by;

    /**
     * Creates a deadline task with the specified description and deadline.
     *
     * @param description description of the task
     * @param by deadline by which the task should be completed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
