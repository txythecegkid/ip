package mimimeow;

import java.util.Scanner;

/** Runs the MimiMeow command-line task manager. */
public class MimiMeow {
    private final MimiMeowUi ui;
    private final CommandHandler commandHandler;

    /** Creates MimiMeow with its task storage, parser, command handler, and UI. */
    public MimiMeow() {
        TaskList taskList = new TaskList();
        this.ui = new MimiMeowUi();
        this.commandHandler = new CommandHandler(taskList, new CommandParser(), ui);
    }

    /** Starts the MimiMeow command-line application. */
    public static void main(String[] args) {
        new MimiMeow().run();
    }

    /** Runs the application input loop until the user enters the bye command. */
    private void run() {
        ui.showWelcomeMessage();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String userInput = ui.readUserInput(inputScanner);
            if (commandHandler.execute(userInput)) {
                return;
            }
        }
    }
}
