package mimimeow;

/** Represents a task in MimiMeow's task list. */
public class Task {
    private String description;
    private boolean isDone;

    /** Creates a task with the specified description. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns the completion status icon for this task. */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Returns this task in the format used when displaying the task list. */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /** Marks this task as done. */
    public void setAsDone() {
        isDone = true;
    }

    /** Marks this task as not done. */
    public void setAsNotDone() {
        isDone = false;
    }
}

