package nelson.ui;

import java.util.List;
import java.util.function.Consumer;

import nelson.task.Task;

/** Sends Nelson's responses to a JavaFX-friendly output callback. */
public class GuiUi extends Ui {
    /** Receives one response line at a time. */
    private final Consumer<String> output;

    /**
     * Creates a GUI output adapter.
     *
     * @param output callback that receives response lines
     */
    public GuiUi(Consumer<String> output) {
        this.output = output;
    }

    /**
     * Sends a normal response line to the GUI.
     *
     * @param messages response text without console indentation
     */
    @Override
    public void show(String... messages) {
        for (String message : messages) {
            output.accept(message);
        }
    }

    /**
     * Sends an error response line to the GUI.
     *
     * @param message error text without console indentation
     */
    @Override
    public void showError(String message) {
        output.accept(message);
    }

    /**
     * Sends matching tasks to the GUI using the same numbering as the console.
     *
     * @param tasks tasks matching the search keyword
     */
    @Override
    public void showMatchingTasks(List<Task> tasks) {
        show("Here are the matching tasks in your list:");
        for (int index = 0; index < tasks.size(); index++) {
            show((index + 1) + "." + tasks.get(index));
        }
    }
}
