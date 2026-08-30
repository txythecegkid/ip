package mimimeow;

import java.util.Scanner;

/** Runs the MimiMeow command-line task manager. */
public class MimiMeow {
    private static final Task[] storedTasks = new Task[100];
    private static int taskCount = 0;

    /** Starts the MimiMeow command-line application. */
    static void main(String[] args) {
        printWelcomeMessage();
        Scanner inputScanner = new Scanner(System.in);

        runCommandLoop(inputScanner);
    }

    /**
     * Continuously reads and executes commands entered by the user.
     *
     * @param inputScanner scanner used to read commands from standard input
     */
    private static void runCommandLoop(Scanner inputScanner) {
        while (true) {
            String userInput = readUserInput(inputScanner);
            if (executeCommand(userInput)) {
                return;
            }
        }
    }

    /**
     * Executes a user command and indicates whether the application should exit.
     *
     * @param userInput command entered by the user
     * @return true if the application should exit, otherwise false
     */
    private static boolean executeCommand(String userInput) {
        String[] commandParts = userInput.split("\\s+", 2);
        String commandWord = commandParts[0];

        printSeparator();

        switch (commandWord) {
            case "bye":
                printGoodbyeMessage();
                printSeparator();
                return true;

            case "list":
                printTaskList();
                break;

            case "mark":
                markTaskFromInput(commandParts);
                break;

            case "unmark":
                unmarkTaskFromInput(commandParts);
                break;

            default:
                addTaskAndReply(userInput);
                break;
        }

        printSeparator();
        return false;
    }

    /** Prints the goodbye message shown when the user exits the application. */
    private static void printGoodbyeMessage() {
        printWithIndent("Bye. Hope to see you again soon!");
    }

    /**
     * Marks the task specified in a parsed command as completed.
     *
     * @param commandParts command and task number entered by the user
     */
    private static void markTaskFromInput(String[] commandParts) {
        if (commandParts.length < 2) {
            printWithIndent("Please specify a task number.");
        } else {
            markTask(commandParts[1]);
        }
    }

    /**
     * Marks the task specified in a parsed command as incomplete.
     *
     * @param commandParts command and task number entered by the user
     */
    private static void unmarkTaskFromInput(String[] commandParts) {
        if (commandParts.length < 2) {
            printWithIndent("Please specify a task number.");
        } else {
            unmarkTask(commandParts[1]);
        }
    }

    /**
     * Adds the user's input as a task and prints MimiMeow's response.
     *
     * @param userInput task description entered by the user
     */
    private static void addTaskAndReply(String userInput) {
        addTask(userInput);

        printMimiReply(userInput);
    }

    /**
     * Reads and trims one line of user input.
     *
     * @param inputScanner scanner used to read standard input
     * @return trimmed user input
     */
    private static String readUserInput(Scanner inputScanner) {
        return inputScanner.nextLine().trim();
    }

    /** Prints the separator used to format the command-line interface. */
    private static void printSeparator() {
        printWithIndent("─".repeat(60));
    }

    /** Prints the welcome banner and introductory message. */
    private static void printWelcomeMessage() {
        String banner = createBanner();
        System.out.println(banner);
        printWithIndent("Hello! I'm MimiMeow.");
        printWithIndent("What can I do for you?");
        printSeparator();
        printWithIndent("");
    }

    /**
     * Creates the ASCII-art banner displayed when MimiMeow starts.
     *
     * @return MimiMeow's ASCII-art banner
     */
    private static String createBanner() {
        return
                  "      ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )      \n"
                + " ███╗   ███╗██╗███╗   ███╗██╗███╗   ███╗███████╗ ██████╗ ██╗    ██╗\n"
                + " ████╗ ████║██║████╗ ████║██║████╗ ████║██╔════╝██╔═══██╗██║    ██║\n"
                + " ██╔████╔██║██║██╔████╔██║██║██╔████╔██║█████╗  ██║   ██║██║ █╗ ██║\n"
                + " ██║╚██╔╝██║██║██║╚██╔╝██║██║██║╚██╔╝██║██╔══╝  ██║   ██║██║███╗██║\n"
                + " ██║ ╚═╝ ██║██║██║ ╚═╝ ██║██║██║ ╚═╝ ██║███████╗╚██████╔╝╚███╔███╔╝\n"
                + " ╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚══════╝ ╚═════╝  ╚══╝╚══╝ \n"
                + "      ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )      \n";

    }

    /** Adds a new task to the task list if storage is available. */
    public static void addTask(String message) {
        if (taskCount < storedTasks.length) {
            storedTasks[taskCount] = new Task(message);
            taskCount++;
        } else {
            System.out.println("Storage is full.");
        }
    }

    /**
     * Marks the specified task as completed.
     *
     * @param taskNumberText text representing the task number
     */
    private static void markTask(String taskNumberText) {

        int taskNumber = Integer.parseInt(taskNumberText);
        if (taskNumber > taskCount) {
            printWithIndent("Meow has no idea what task " + taskNumber + " is.");
        } else {
            storedTasks[taskNumber - 1].setAsDone();
            printWithIndent("Nice! Meow've marked this task as done:");
            printWithIndent(storedTasks[taskNumber - 1].toString());
        }
    }

    /**
     * Marks the specified task as incomplete.
     *
     * @param taskNumberText text representing the task number
     */
    private static void unmarkTask(String taskNumberText) {
        int taskNumber = Integer.parseInt(taskNumberText);
        if (taskNumber > taskCount) {
            printWithIndent("Meow has no idea what task " + taskNumber + " is.");
        } else {
            storedTasks[taskNumber - 1].setAsNotDone();
            printWithIndent("OK, Meow've marked this task as not done yet:");
            printWithIndent(storedTasks[taskNumber - 1].toString());
        }
    }

    /**
     * Prints a message with MimiMeow's standard indentation.
     *
     * @param message message to print
     */
    private static void printWithIndent(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints MimiMeow's response after a task has been added.
     *
     * @param message task description entered by the user
     */
    private static void printMimiReply(String message) {
        printWithIndent("(^._.^) meows:");
        printWithIndent("added: " + message);
    }

    /** Prints all tasks currently stored in the task list. */
    private static void printTaskList() {
        if (taskCount > 1) {
            printWithIndent("Here are the tasks in your list:");
        } else if (taskCount == 1) {
            printWithIndent("Here is the task in your list:");
        } else {
            printWithIndent("There are no tasks in your list yet!");
        }

        for (int i = 0; i < taskCount; i++) {
            printWithIndent((i + 1) + ". " + storedTasks[i]);
        }
    }
}
