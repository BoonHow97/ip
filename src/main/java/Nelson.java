import java.util.Scanner;

/**
 * Starts the Nelson chatbot application.
 */
public class Nelson {
    /** Chess-themed prefixes for task-addition responses. */
    private static final String[] ADD_MESSAGES = {
            "A weak opening, but",
            "A developing move.",
            "That blunder is now on the scoresheet.",
            "A gambit so poor even your pawns are confused.",
            "Another dubious move recorded for posterity."
    };

    /** Stores the user's tasks for this run of the program. */
    private final String[] tasks = new String[100];
    /** Tracks whether each corresponding task in {@link #tasks} is complete. */
    private final boolean[] isDone = new boolean[100];
    /** Number of tasks currently stored in {@link #tasks}. */
    private int taskCount = 0;
    /** Selects the next task-addition response. */
    private int moveCount = 0;

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

            if (command.equals("bye") || command.equals("Bye")) {
                System.out.println("    Resigning already? Pathetic. I win.");
                System.out.println("    ____________________________________________________________");
                return;
            }

            if (command.equals("list")) {
                showTasks();
                System.out.println("    ____________________________________________________________");
                continue;
            }

            if (command.startsWith("mark ")) {
                markTask(command);
                System.out.println("    ____________________________________________________________");
                continue;
            }

            tasks[taskCount] = command;
            taskCount++;

            String addMessage = ADD_MESSAGES[moveCount % ADD_MESSAGES.length];
            System.out.println("    " + addMessage + " I have added: " + command + ". Defend yourself.");
            System.out.println("    ____________________________________________________________");

            // Increment the move count so the next task gets the next response.
            moveCount++;
        }
    }

    /**
     * Displays all tasks that have been added during this run.
     */
    public void showTasks() {
        System.out.println("    Evaluate your board state. Here are your tasks:");
        for (int index = 0; index < taskCount; index++) {
            String status = isDone[index] ? "[X]" : "[ ]";
            System.out.println("    " + (index + 1) + "." + status + " " + tasks[index]);
        }
    }

    /**
     * Marks the task specified in a mark command as complete.
     *
     * @param command the complete command entered by the user
     */
    public void markTask(String command) {
        int taskNumber = Integer.parseInt(command.substring(5));
        int taskIndex = taskNumber - 1;
        isDone[taskIndex] = true;
        System.out.println("    You completed a task? Do not celebrate. I am already calculating 15 moves ahead.");
        System.out.println("    [X] " + tasks[taskIndex]);
    }
}
