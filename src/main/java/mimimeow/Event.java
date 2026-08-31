package mimimeow;

/** Represents a task that takes place during a specified time period. */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Creates an event with the specified description, start time, and end time.
     *
     * @param description description of the event
     * @param startTime time when the event starts
     * @param endTime time when the event ends
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime + " to: " + endTime + ")";
    }
}
