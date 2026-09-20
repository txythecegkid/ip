package mimimeow.task;

/** Represents a task that takes place during a specified time period. */
public class Event extends Task {
    private final String startTime;
    private final String endTime;

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
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | "
                + escapeFileField(startTime) + " | " + escapeFileField(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime + " to: " + endTime + ")";
    }
}
