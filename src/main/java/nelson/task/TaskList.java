package nelson.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stores Nelson's tasks and provides operations on the task collection.
 */
public class TaskList implements Iterable<Task> {
    /** The tasks currently known to Nelson. */
    private final ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Creates a task list containing the tasks from the given source. */
    public TaskList(Iterable<Task> source) {
        this();
        for (Task task : source) {
            add(task);
        }
    }

    /** Adds a task to the end of this list. */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Removes and returns the task at the given zero-based index. */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /** Returns the task at the given zero-based index. */
    public Task get(int index) {
        return tasks.get(index);
    }

    /** Returns the number of tasks in this list. */
    public int size() {
        return tasks.size();
    }

    /** Removes all tasks from this list. */
    public void clear() {
        tasks.clear();
    }

    /** Allows storage code to iterate over tasks without owning the collection. */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
