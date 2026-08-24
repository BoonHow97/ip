package nelson;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nelson.exception.NelsonException;
import nelson.gui.DialogBox;
import nelson.ui.GuiUi;

/** JavaFX user interface for interacting with Nelson. */
public class NelsonGui extends Application {
    /** Scrolls through the conversation between the user and Nelson. */
    private final ScrollPane conversationPane = new ScrollPane();
    /** Holds one chat bubble for each message. */
    private final VBox messages = new VBox(10);
    /** Accepts the next command. */
    private final TextField commandField = new TextField();
    /** Sends a command when clicked. */
    private final Button sendButton = new Button("Send");
    /** The chatbot instance shared by all commands in this window. */
    private Nelson nelson;

    /**
     * Builds and displays the chatbot window.
     *
     * @param stage primary JavaFX stage
     */
    @Override
    public void start(Stage stage) {
        nelson = new Nelson(new GuiUi(this::appendBotMessage));

        messages.setPadding(new Insets(14));
        messages.setFillWidth(true);
        conversationPane.setContent(messages);
        conversationPane.setFitToWidth(true);
        conversationPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        conversationPane.setStyle("-fx-background: #f4f6f8; -fx-background-color: #f4f6f8;");
        messages.heightProperty().addListener((observable, oldHeight, newHeight) -> scrollToLatestMessage());

        Label title = new Label("Nelson");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
        Label subtitle = new Label("Your chess-themed task assistant");
        subtitle.setStyle("-fx-text-fill: #555555;");

        Label help = new Label("Commands\n"
                + "todo <description>\n"
                + "deadline <description> /by yyyy-mm-dd\n"
                + "event <description> /from yyyy-mm-dd /to yyyy-mm-dd\n"
                + "list   mark <number>   unmark <number>\n"
                + "delete <number>   find <keyword>   bye");
        help.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 12px;");

        HBox commandBar = new HBox(8, commandField, sendButton);
        HBox.setHgrow(commandField, Priority.ALWAYS);
        commandField.setPromptText("Type a command, for example: todo read book");
        commandField.setOnAction(event -> sendCommand());
        sendButton.setOnAction(event -> sendCommand());

        VBox header = new VBox(3, title, subtitle);
        VBox rightPanel = new VBox(12, new Label("Quick reference"), help);
        rightPanel.setPadding(new Insets(0, 0, 0, 12));
        rightPanel.setPrefWidth(290);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(18));
        root.setTop(header);
        root.setCenter(conversationPane);
        root.setRight(rightPanel);
        root.setBottom(commandBar);
        BorderPane.setMargin(conversationPane, new Insets(18, 18, 12, 0));
        BorderPane.setMargin(commandBar, new Insets(0, 18, 0, 0));

        appendWelcomeMessage();
        Scene scene = new Scene(root, 920, 620);
        stage.setTitle("Nelson - Task Assistant");
        stage.setScene(scene);
        stage.show();
        commandField.requestFocus();
    }

    /** Sends the current command to Nelson and displays its response. */
    private void sendCommand() {
        String command = commandField.getText().trim();
        if (command.isEmpty()) {
            return;
        }
        appendUserMessage(command);
        commandField.clear();

        if (command.equals("bye") || command.equals("Bye")) {
            appendBotMessage("Molo! Resigning already? Pathetic. I win.");
            commandField.setDisable(true);
            sendButton.setDisable(true);
            return;
        }

        try {
            nelson.processCommand(command);
        } catch (NelsonException exception) {
            appendBotMessage(exception.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            appendBotMessage("Molo! Out of bounds! That task number doesn't exist on this board.");
        }
    }

    /** Adds a response line to the conversation transcript. */
    private void appendBotMessage(String message) {
        messages.getChildren().add(DialogBox.forNelson(message));
        scrollToLatestMessage();
    }

    /** Adds a command bubble sent by the user. */
    private void appendUserMessage(String message) {
        messages.getChildren().add(DialogBox.forUser(message));
        scrollToLatestMessage();
    }

    /** Displays Nelson's original startup banner and persona greeting. */
    private void appendWelcomeMessage() {
        String logo =
                "       _   __     __\n"
                        + "       / | / /___ / /________  ____\n"
                        + "      /  |/ / __ \\/ / ___/ __ \\/ __ " + "\\" + "\n"
                        + "     / /|  /  __/ / (__  ) /_/ / / / /\n"
                        + "    /_/ |_/\\___/_/_/____/\\____/_/ /_/\n";
        appendBotMessage("System booting...\n" + logo);
        appendBotMessage("Molo! I have a surprise for you. Your move!");
        appendBotMessage("Type your move, or are you just going to let your time run out?");
    }

    /** Keeps the newest message visible after a bubble is added. */
    private void scrollToLatestMessage() {
        Platform.runLater(() -> conversationPane.setVvalue(conversationPane.getVmax()));
    }
}
