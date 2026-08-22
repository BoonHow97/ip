/** Converts raw user input into structured Nelson commands. */
public class Parser {
    /** Supported command kinds. */
    public enum Type { LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE }

    /** A parsed command and its arguments. */
    public static class Command {
        private final Type type;
        private final String[] arguments;

        private Command(Type type, String... arguments) {
            this.type = type;
            this.arguments = arguments;
        }

        public Type getType() { return type; }
        public String getArgument(int index) { return arguments[index]; }
    }

    /** Parses one user command and validates its required notation. */
    public Command parse(String command) throws NelsonException {
        if (command.equals("list")) {
            return new Command(Type.LIST);
        } else if (command.equals("todo") || command.startsWith("todo ")) {
            String description = command.substring(4).trim();
            if (description.isEmpty()) {
                throw emptyMove();
            }
            return new Command(Type.TODO, description);
        } else if (command.equals("deadline") || command.startsWith("deadline ")) {
            return parseDeadline(command);
        } else if (command.equals("event") || command.startsWith("event ")) {
            return parseEvent(command);
        } else if (command.equals("mark") || command.startsWith("mark ")) {
            return new Command(Type.MARK, command.substring(4).trim());
        } else if (command.equals("unmark") || command.startsWith("unmark ")) {
            return new Command(Type.UNMARK, command.substring(6).trim());
        } else if (command.equals("delete") || command.startsWith("delete ")) {
            return new Command(Type.DELETE, command.substring(6).trim());
        }
        throw new NelsonException("Molo! I don't know what that means. Are you even playing the same game?");
    }

    private Command parseDeadline(String command) throws NelsonException {
        String details = command.substring(8).trim();
        int byIndex = details.indexOf("/by");
        if (details.isEmpty()) {
            throw emptyMove();
        }
        if (byIndex == -1) {
            throw invalidTimeParameters();
        }
        String description = details.substring(0, byIndex).trim();
        String by = details.substring(byIndex + 3).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw invalidTimeParameters();
        }
        return new Command(Type.DEADLINE, description, by);
    }

    private Command parseEvent(String command) throws NelsonException {
        String details = command.substring(5).trim();
        int fromIndex = details.indexOf("/from");
        int toIndex = details.indexOf("/to");
        if (details.isEmpty()) {
            throw emptyMove();
        }
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw invalidTimeParameters();
        }
        String description = details.substring(0, fromIndex).trim();
        String from = details.substring(fromIndex + 5, toIndex).trim();
        String to = details.substring(toIndex + 3).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw invalidTimeParameters();
        }
        return new Command(Type.EVENT, description, from, to);
    }

    private NelsonException emptyMove() {
        return new NelsonException("Molo! An empty move? You must provide a description, you amateur.");
    }

    private NelsonException invalidTimeParameters() {
        return new NelsonException("Molo! Invalid notation! You are missing the required time parameters.");
    }
}
