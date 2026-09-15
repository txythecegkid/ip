package mimimeow.task;

import java.util.ArrayList;

/**
 * Stores and manages the tasks created in MimiMeow.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the dynamic array
     */
    public void add(Task task) {
        tasks.add(task);
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
}
