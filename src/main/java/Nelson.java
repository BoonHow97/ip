import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Starts the Nelson chatbot application.
 */
public class Nelson {
    /** The relative path where Nelson stores the current task list. */
    private static final Path TASK_FILE = Paths.get("data", "nelson.txt");
    /** Stores the user's tasks for this run of the program using an ArrayList. */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /** Creates Nelson and restores any tasks saved by an earlier run. */
    public Nelson() {
        loadTasks();
    }

    public static void main(String[] args) {
        Nelson nelson = new Nelson();
        nelson.showWelcome();
        nelson.handleCommands();
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        String logo =
                "       _   __     __\n"
                        + "       / | / /___ / /________  ____\n"
                        + "      /  |/ / __ \\/ / ___/ __ \\/ __ \\\n"
                        + "     / /|  /  __/ / (__  ) /_/ / / / /\n"
                        + "    /_/ |_/\\___/_/_/____/\\____/_/ /_/\n";

        System.out.println("System booting...\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Molo! I have a surprise for you. Your move!");
        System.out.println("    Type your move, or are you just going to let your time run out?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Reads and responds to commands until the user resigns.
     */
    public void handleCommands() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            System.out.println("    ____________________________________________________________");

            try {
                if (command.equals("bye") || command.equals("Bye")) {
                    System.out.println("    Molo! Resigning already? Pathetic. I win.");
                    System.out.println("    ____________________________________________________________");
                    return;
                }
                processCommand(command);
            } catch (NelsonException exception) {
                System.out.println("    " + exception.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException exception) {
                System.out.println("    Molo! Out of bounds! That task number doesn't exist on this board.");
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Runs one command after validating its arguments.
     *
     * @param command the command entered by the user
     * @throws NelsonException if the command or its arguments are invalid
     */
    public void processCommand(String command) throws NelsonException {
        if (command.equals("list")) {
            showTasks();
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            String description = command.substring(4).trim();
            if (description.isEmpty()) {
                throw new NelsonException("Molo! An empty move? You must provide a description, you amateur.");
            }
            addTypedTask(new Todo(description));
        } else if (command.equals("deadline") || command.startsWith("deadline ")) {
            addDeadline(command);
        } else if (command.equals("event") || command.startsWith("event ")) {
            addEvent(command);
        } else if (command.equals("mark") || command.startsWith("mark ")) {
            markTask(command);
        } else if (command.equals("unmark") || command.startsWith("unmark ")) {
            unmarkTask(command);
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            deleteTask(command);
        } else {
            throw new NelsonException("Molo! I don't know what that means. Are you even playing the same game?");
        }
    }

    /**
     * Parses and adds a deadline task.
     *
     * @param command the deadline command string
     * @throws NelsonException if description or /by parameter is missing/malformed
     */
    public void addDeadline(String command) throws NelsonException {
        String details = command.substring(8).trim();
        if (details.isEmpty()) {
            throw new NelsonException("Molo! An empty move? You must provide a description, you amateur.");
        }
        int byIndex = details.indexOf("/by");
        if (byIndex == -1) {
            throw new NelsonException("Molo! Invalid notation! You are missing the required time parameters.");
        }
        String description = details.substring(0, byIndex).trim();
        String by = details.substring(byIndex + 3).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new NelsonException("Molo! Invalid notation! You are missing the required time parameters.");
        }
        addTypedTask(new Deadline(description, by));
    }

    /**
     * Parses and adds an event task.
     *
     * @param command the event command string
     * @throws NelsonException if description, /from, or /to parameters are missing/malformed
     */
    public void addEvent(String command) throws NelsonException {
        String details = command.substring(5).trim();
        if (details.isEmpty()) {
            throw new NelsonException("Molo! An empty move? You must provide a description, you amateur.");
        }
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new NelsonException("Molo! Invalid notation! You are missing the required time parameters.");
        }
        String description = details.substring(0, fromIndex).trim();
        String from = details.substring(fromIndex + 5, toIndex).trim();
        String to = details.substring(toIndex + 3).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new NelsonException("Molo! Invalid notation! You are missing the required time parameters.");
        }
        addTypedTask(new Event(description, from, to));
    }

    /**
     * Displays all tasks that have been added during this run.
     */
    public void showTasks() {
        System.out.println("    Molo! Evaluate your board state. Here are the tasks in your list:");
        for (int index = 0; index < tasks.size(); index++) {
            Task task = tasks.get(index);
            System.out.println("    " + (index + 1) + "." + task);
        }
    }

    /**
     * Marks the task specified in a mark command as complete.
     *
     * @param command the complete command entered by the user
     * @throws NelsonException if index is invalid or out of bounds
     */
    public void markTask(String command) throws NelsonException {
        String indexStr = command.substring(4).trim();
        if (indexStr.isEmpty()) {
            throw new NelsonException("Molo! Out of bounds! That task number doesn't exist on this board.");
        }
        int taskNumber = Integer.parseInt(indexStr);
        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NelsonException("Molo! Out of bounds! That task number doesn't exist on this board.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        saveTasks();
        System.out.println("    Molo! You completed a task? Do not celebrate. I am already calculating 15 moves ahead.");
        System.out.println("    [X] " + task.getDescription());
    }

    /**
     * Marks the task specified in an unmark command as incomplete.
     *
     * @param command the complete command entered by the user
     * @throws NelsonException if index is invalid or out of bounds
     */
    public void unmarkTask(String command) throws NelsonException {
        String indexStr = command.substring(6).trim();
        if (indexStr.isEmpty()) {
            throw new NelsonException("Molo! Out of bounds! That task number doesn't exist on this board.");
        }
        int taskNumber = Integer.parseInt(indexStr);
        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NelsonException("Molo! Out of bounds! That task number doesn't exist on this board.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        saveTasks();
        System.out.println("    Molo! Taking back your move? Absolute blunder. Marked as not done yet:");
        System.out.println("    [ ] " + task.getDescription());
    }

    /**
     * Deletes the task specified by the user using ArrayList collection methods.
     *
     * @param command the complete delete command
     * @throws NelsonException if index is invalid or out of bounds
     */
    public void deleteTask(String command) throws NelsonException {
        String indexStr = command.substring(6).trim();
        if (indexStr.isEmpty()) {
            throw new NelsonException("Molo! Out of bounds! That task number doesn't exist on this board.");
        }
        int taskNumber = Integer.parseInt(indexStr);
        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new NelsonException("Molo! Out of bounds! That task number doesn't exist on this board.");
        }
        Task removedTask = tasks.remove(taskIndex);
        saveTasks();
        System.out.println("    Molo! Sweeping your mistakes under the rug already? Fine, I've banished this blunder:");
        System.out.println("      " + removedTask);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a typed task and displays Nelson's task-addition response.
     *
     * @param task the task to add
     */
    public void addTypedTask(Task task) {
        tasks.add(task);
        saveTasks();
        System.out.println("    " + getAdditionMessage(task));
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }

    /** Loads valid task records from disk, leaving the list empty when no file exists. */
    private void loadTasks() {
        try {
            if (!Files.isRegularFile(TASK_FILE)) {
                return;
            }
            for (String line : Files.readAllLines(TASK_FILE, StandardCharsets.UTF_8)) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split("\\s*\\|\\s*", -1);
                if (parts.length < 3 || (!parts[1].equals("0") && !parts[1].equals("1"))) {
                    continue;
                }
                Task task;
                switch (parts[0]) {
                case "T":
                    if (parts.length != 3 || parts[2].isBlank()) {
                        continue;
                    }
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    if (parts.length != 4 || parts[2].isBlank() || parts[3].isBlank()) {
                        continue;
                    }
                    task = new Deadline(parts[2], parts[3]);
                    break;
                case "E":
                    if (parts.length != 5 || parts[2].isBlank()
                            || parts[3].isBlank() || parts[4].isBlank()) {
                        continue;
                    }
                    task = new Event(parts[2], parts[3], parts[4]);
                    break;
                default:
                    continue;
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException | SecurityException exception) {
            System.err.println("Warning: unable to load tasks from " + TASK_FILE + ". Starting with an empty list.");
            tasks.clear();
        }
    }

    /** Saves the current task list to disk after a successful mutation. */
    private void saveTasks() {
        try {
            Files.createDirectories(TASK_FILE.getParent());
            ArrayList<String> savedTasks = new ArrayList<>();
            for (Task task : tasks) {
                savedTasks.add(task.toStorageString());
            }
            Files.write(TASK_FILE, savedTasks, StandardCharsets.UTF_8);
        } catch (IOException | SecurityException exception) {
            System.err.println("Warning: unable to save tasks to " + TASK_FILE + ".");
        }
    }

    /**
     * Returns Nelson's type-specific response when adding a task.
     *
     * @param task the task being added
     * @return the appropriate task-addition message
     */
    public String getAdditionMessage(Task task) {
        if (task instanceof Todo) {
            return "Molo! Another thoughtless move? Fine. I have added this trivial task:";
        }
        if (task instanceof Deadline) {
            return "Molo! Running out of time on your clock? Pathetic. I have added this task:";
        }
        return "Molo! Booking out time just to blunder? Typical. I have added this task:";
    }
}
