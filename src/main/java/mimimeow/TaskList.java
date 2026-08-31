package mimimeow;

/** Stores and manages the tasks created in MimiMeow. */
public class TaskList {
    private static final int MAXIMUM_TASKS = 100;
    private final Task[] tasks = new Task[MAXIMUM_TASKS];
    private int taskCount;

    /** Adds a task when storage is available. */
    public void add(Task task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
        } else {
            System.out.println("Storage is full.");
        }
    }

    /** Returns the number of tasks currently stored. */
    public int size() {
        return taskCount;
    }

    /** Returns the task at the specified zero-based index. */
    public Task get(int index) {
        return tasks[index];
    }
}
