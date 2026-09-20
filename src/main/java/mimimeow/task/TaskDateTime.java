package mimimeow.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

/** Represents a validated date and time belonging to a task. */
public final class TaskDateTime implements Comparable<TaskDateTime> {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter
            .ofPattern("d/M/uuuu HHmm", Locale.ENGLISH)
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter
            .ofPattern("d MMM uuuu, h:mm a", Locale.ENGLISH);

    private final LocalDateTime value;

    /** Creates a task date and time by parsing the supplied text. */
    public TaskDateTime(String dateTimeText) {
        if (dateTimeText == null || dateTimeText.isBlank()) {
            throw new InvalidTaskException("A date and time is required.");
        }
        try {
            this.value = LocalDateTime.parse(dateTimeText.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException exception) {
            throw new InvalidTaskException(
                    "Use the date format d/M/yyyy HHmm, for example 2/12/2019 1800.");
        }
    }

    /** Returns whether this date and time occurs before another. */
    public boolean isBefore(TaskDateTime other) {
        return value.isBefore(other.value);
    }

    /** Returns whether this date and time occurs before the specified value. */
    public boolean isBefore(LocalDateTime other) {
        return value.isBefore(other);
    }

    /** Returns whether this date and time falls on the specified date. */
    public boolean isOn(LocalDate date) {
        return value.toLocalDate().equals(date);
    }

    /** Returns the calendar date represented by this value. */
    public LocalDate toLocalDate() {
        return value.toLocalDate();
    }

    /** Returns this date and time in the storage format. */
    public String toFileString() {
        return value.format(INPUT_FORMAT);
    }

    @Override
    public int compareTo(TaskDateTime other) {
        return value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return value.format(DISPLAY_FORMAT);
    }
}
