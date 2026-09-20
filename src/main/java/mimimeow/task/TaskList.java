package mimimeow.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Stores and manages the tasks created in MimiMeow.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the dynamic list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task at the specified zero-based index.
     */
    public void add(int index, Task task) {
        tasks.add(index, task);
    }

    /**
     * Deletes the specified task from the list if it is present.
     */
    public void delete(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns the number of tasks currently stored.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified zero-based index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes and returns the task at the specified zero-based index.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /** Returns scheduled tasks that occur on the specified date. */
    public List<Task> findTasksOccurringOn(LocalDate date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof ScheduledTask scheduledTask && scheduledTask.occursOn(date)) {
                matchingTasks.add(task);
            }
        }
        matchingTasks.sort(createScheduleComparator());
        return matchingTasks;
    }

    /** Returns incomplete scheduled tasks that are upcoming at the specified time. */
    public List<Task> findUpcomingTasks(LocalDateTime dateTime) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isDone() && task instanceof ScheduledTask scheduledTask
                    && scheduledTask.isUpcomingAt(dateTime)) {
                matchingTasks.add(task);
            }
        }
        matchingTasks.sort(createScheduleComparator());
        return matchingTasks;
    }

    /** Returns incomplete scheduled tasks that are overdue at the specified time. */
    public List<Task> findOverdueTasks(LocalDateTime dateTime) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isDone() && task instanceof ScheduledTask scheduledTask
                    && scheduledTask.isOverdueAt(dateTime)) {
                matchingTasks.add(task);
            }
        }
        matchingTasks.sort(createScheduleComparator());
        return matchingTasks;
    }

    private Comparator<Task> createScheduleComparator() {
        return Comparator.comparing(task -> ((ScheduledTask) task).getScheduleTime());
    }
}
