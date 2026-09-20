package mimimeow.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** Represents a task that must be completed by a specified deadline. */
public class Deadline extends Task implements ScheduledTask {
    private final TaskDateTime by;

    /**
     * Creates a deadline task with the specified description and deadline.
     *
     * @param description description of the task
     * @param by deadline by which the task should be completed
     */
    public Deadline(String description, String by) {
        super(description);
        if (by == null || by.isBlank()) {
            throw new InvalidTaskException("A deadline needs a date.");
        }
        this.by = new TaskDateTime(by);
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + escapeFileField(by.toFileString());
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return by.isOn(date);
    }

    @Override
    public boolean isUpcomingAt(LocalDateTime dateTime) {
        return !by.isBefore(dateTime);
    }

    @Override
    public boolean isOverdueAt(LocalDateTime dateTime) {
        return by.isBefore(dateTime);
    }

    @Override
    public TaskDateTime getScheduleTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
