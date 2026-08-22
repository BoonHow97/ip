package nelson.ui;

import java.util.Scanner;

/** Handles Nelson's console input and output. */
public class Ui {
    /** Reads commands from standard input. */
    private final Scanner scanner = new Scanner(System.in);

    /** Displays Nelson's startup banner. */
    public void showWelcome() {
        String logo =
                "       _   __     __\n"
                        + "       / | / /___ / /________  ____\n"
                        + "      /  |/ / __ \\/ / ___/ __ \\/ __ \\\n"
                        + "     / /|  /  __/ / (__  ) /_/ / / / /\n"
                        + "    /_/ |_/\\___/_/_/____/\\____/_/ /_/\n";
        System.out.println("System booting...\n" + logo);
        showSeparator();
        System.out.println("    Molo! I have a surprise for you. Your move!");
        System.out.println("    Type your move, or are you just going to let your time run out?");
        showSeparator();
    }

    /** Returns whether another command is available. */
    public boolean hasNextCommand() {
        return scanner.hasNextLine();
    }

    /** Reads the next command. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Displays the standard command separator. */
    public void showSeparator() {
        System.out.println("    ____________________________________________________________");
    }

    /** Displays the resignation response. */
    public void showGoodbye() {
        System.out.println("    Molo! Resigning already? Pathetic. I win.");
        showSeparator();
    }

    /** Displays a normal response line. */
    public void show(String message) {
        System.out.println("    " + message);
    }

    /** Displays an error response. */
    public void showError(String message) {
        show(message);
    }
}
