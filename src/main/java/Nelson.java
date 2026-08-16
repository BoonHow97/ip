import java.util.Scanner;

/**
 * Starts the Nelson chatbot application.
 */
public class Nelson {
    /** Chess-themed taunts appended to echoed commands. */
    private static final String[] TAUNTS = {
            "A pawn could have planned that better.",
            "Was that a move, or did your knight just trip over itself?",
            "Spectacular--your position is already collapsing.",
            "Your king called; it wants a better defender.",
            "Even your bishop looks embarrassed by that move.",
            "I've seen stalemates put up more of a fight.",
            "That command has all the strategy of hanging your queen on move one.",
            "Bold choice. Unfortunately, it is the kind of bold that loses a rook."
            
    };
    private static int moveCount = 0;


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
        System.out.println("    Type your command, or are you just going to let your time run out?");
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

            String taunt = TAUNTS[moveCount % TAUNTS.length];
            System.out.println("    You played: " + command + ". " + taunt);
            System.out.println("    ____________________________________________________________");
            
            // Increment the move count so the next command gets the next taunt
            moveCount++;
        }
    }
}
