package mimimeow.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** Represents a task that takes place during a specified time period. */
public class Event extends Task implements ScheduledTask {
    private final TaskDateTime startTime;
    private final TaskDateTime endTime;

    /**
     * Creates an event with the specified description, start time, and end time.
     *
     * @param description description of the event
     * @param startTime time when the event starts
     * @param endTime time when the event ends
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        if (startTime == null || startTime.isBlank() || endTime == null || endTime.isBlank()) {
            throw new InvalidTaskException("An event needs both start and end times.");
        }
        this.startTime = new TaskDateTime(startTime);
        this.endTime = new TaskDateTime(endTime);
        if (this.endTime.isBefore(this.startTime)) {
            throw new InvalidTaskException("An event's end time cannot be before its start time.");
        }
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | "
                + escapeFileField(startTime.toFileString()) + " | "
                + escapeFileField(endTime.toFileString());
    }

    @Override
    public boolean occursOn(LocalDate date) {
        return !date.isBefore(startTime.toLocalDate()) && !date.isAfter(endTime.toLocalDate());
    }

    @Override
    public boolean isUpcomingAt(LocalDateTime dateTime) {
        return !startTime.isBefore(dateTime);
    }

    @Override
    public boolean isOverdueAt(LocalDateTime dateTime) {
        return false;
    }

    @Override
    public TaskDateTime getScheduleTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime + " to: " + endTime + ")";
    }
}
