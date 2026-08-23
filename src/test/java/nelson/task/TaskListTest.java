package nelson.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

/** Tests task collection operations in {@link TaskList}. */
public class TaskListTest {
    @Test
    public void newTaskList_hasZeroTasks() {
        TaskList taskList = new TaskList();

        assertEquals(0, taskList.size());
    }

    @Test
    public void addTask_storesTaskAndUpdatesSize() {
        TaskList taskList = new TaskList();
        Task task = new Todo("read book");

        taskList.add(task);

        assertEquals(1, taskList.size());
        assertSame(task, taskList.get(0));
    }

    @Test
    public void addMultipleTasks_preservesInsertionOrder() {
        TaskList taskList = new TaskList();
        Task first = new Todo("first");
        Task second = new Todo("second");
        taskList.add(first);
        taskList.add(second);

        assertEquals(2, taskList.size());
        assertSame(first, taskList.get(0));
        assertSame(second, taskList.get(1));
    }

    @Test
    public void removeTask_returnsRemovedTaskAndUpdatesSize() {
        TaskList taskList = new TaskList();
        Task first = new Todo("first");
        Task second = new Todo("second");
        taskList.add(first);
        taskList.add(second);

        Task removed = taskList.remove(0);

        assertSame(first, removed);
        assertEquals(1, taskList.size());
        assertSame(second, taskList.get(0));
    }

    @Test
    public void clearTaskList_removesAllTasks() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("first"));
        taskList.add(new Todo("second"));

        taskList.clear();

        assertEquals(0, taskList.size());
    }

    @Test
    public void constructFromIterable_copiesTasksInOrder() {
        Task first = new Todo("first");
        Task second = new Todo("second");

        TaskList taskList = new TaskList(List.of(first, second));

        assertIterableEquals(List.of(first, second), taskList);
    }

    @Test
    public void getInvalidIndex_throwsIndexOutOfBoundsException() {
        TaskList taskList = new TaskList();

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.get(0));
    }

    @Test
    public void removeInvalidIndex_throwsIndexOutOfBoundsException() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("read book"));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.remove(1));
    }
}
