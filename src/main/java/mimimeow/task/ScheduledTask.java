package mimimeow.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** Defines date-based operations supported by scheduled tasks. */
public interface ScheduledTask {
    /** Returns whether this task occurs on the specified date. */
    boolean occursOn(LocalDate date);

    /** Returns whether this task is upcoming at the specified date and time. */
    boolean isUpcomingAt(LocalDateTime dateTime);

    /** Returns whether this task is overdue at the specified date and time. */
    boolean isOverdueAt(LocalDateTime dateTime);

    /** Returns the date and time used to order this task chronologically. */
    TaskDateTime getScheduleTime();
}
