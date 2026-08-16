/**
 * Starts the Nelson chatbot application.
 */
public class Nelson {
    public static void main(String[] args) {
        new Nelson().showWelcome();
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        String logo =
                "    _   __     __\n"
                        + "   / | / /___ / /________  ____\n"
                        + "  /  |/ / __ \\/ / ___/ __ \\/ __ \\\n"
                        + " / /|  /  __/ / (__  ) /_/ / / / /\n"
                        + "/_/ |_/\\___/_/_/____/\\____/_/ /_/\n";

        System.out.println("System booting...\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Molo! I have a surprise for you. Your move!");
        System.out.println("Type your command, or are you just going to let your time run out?");
        System.out.println("____________________________________________________________");
    }
}
