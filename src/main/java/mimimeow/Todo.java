package mimimeow;

/** Represents a todo task without a specific deadline or time period. */
public class Todo extends Task {

    /**
     * Creates a todo task with the specified description.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
