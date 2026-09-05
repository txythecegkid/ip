package mimimeow;

import java.util.Scanner;

/** Runs the MimiMeow command-line task manager. */
public class MimiMeow {
    private static final TaskList taskList = new TaskList();
    private static final CommandParser commandParser = new CommandParser();

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
        printSeparator();
        try {
            Command command = commandParser.parse(userInput);
            String commandWord = command.getWord();
            String commandArgs = command.getArguments();

            switch (commandWord) {
                case "bye":
                    printGoodbyeMessage();
                    printSeparator();
                    return true;
                case "list":
                    printTaskList();
                    break;
                case "mark":
                    markTaskFromInput(commandArgs);
                    break;
                case "unmark":
                    unmarkTaskFromInput(commandArgs);
                    break;
                case "todo":
                    addTodo(commandArgs);
                    break;
                case "deadline":
                    addDeadline(commandArgs);
                    break;
                case "event":
                    addEvent(commandArgs);
                    break;
                default:
                    throw new MimiMeowException("Mimi does not recognise that command. Try todo, list, mark, or bye.");
            }
        } catch (MimiMeowException exception) {
            printWithIndent("Miiiision impossible! " + exception.getMessage());
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
     * @param commandArguments task number entered by the user
     */
    private static void markTaskFromInput(String commandArguments) {
        if (commandArguments.isEmpty()) {
            throw new MimiMeowException("Mimi needs a task number to mark, meow.");
        } else {
            markTask(commandArguments);
        }
    }

    /**
     * Unmark the task specified in a parsed command as incomplete.
     *
     * @param commandArguments task number entered by the user
     */
    private static void unmarkTaskFromInput(String commandArguments) {
        if (commandArguments.isEmpty()) {
            throw new MimiMeowException("Mimi needs a task number to unmark, meow.");
        } else {
            unmarkTask(commandArguments);
        }
    }

    private static void addTodo(String commandArguments) {
        String description = commandArguments.trim();
        if (description.isEmpty()) {
            throw new MimiMeowException("This todo is as empty as Mimi's food bowl. Add a description, meow.");
        }
        addTaskAndReply(new Todo(description));
    }

    private static void addDeadline(String commandArguments) {
        String[] deadlineParts = commandArguments.split("\\s*/by\\s+", 2);

        if (deadlineParts.length < 2) {
            throw new MimiMeowException("Mimi needs a deadline description and date. Try: deadline description /by date.");
        }

        String description = deadlineParts[0].trim();
        String deadlineDate = deadlineParts[1].trim();
        if (description.isEmpty() || deadlineDate.isEmpty()) {
            throw new MimiMeowException("A deadline needs both a description and a date, meow.");
        }

        addTaskAndReply(new Deadline(description, deadlineDate));
    }

    private static void addEvent(String commandArguments) {
        String[] eventParts = commandArguments.split("\\s*/from\\s+", 2);

        if (eventParts.length < 2) {
            throw new MimiMeowException("Mimi needs an event description, start, and end. Try: event description /from start /to end.");
        }

        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("\\s*/to\\s+", 2);

        if (timeParts.length < 2) {
            throw new MimiMeowException("Mimi needs both /from and /to times for this event.");
        }

        String startTime = timeParts[0].trim();
        String endTime = timeParts[1].trim();
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new MimiMeowException("This event needs a description, start time, and end time");
        }

        addTaskAndReply(new Event(description, startTime, endTime));
    }

    /**
     * Adds the user's input as a task and prints MimiMeow's response.
     */
    private static void addTaskAndReply(Task task) {
        addTask(task);
        printMimiReply(task.toString());
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
    public static void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the specified task as completed.
     *
     * @param taskNumberText text representing the task number
     */
    private static void markTask(String taskNumberText) {
        updateTaskStatus(taskNumberText, true);
    }

    /**
     * Marks the specified task as incomplete.
     *
     * @param taskNumberText text representing the task number
     */
    private static void unmarkTask(String taskNumberText) {
        updateTaskStatus(taskNumberText, false);
    }

    /** Updates a task's completion status and prints the corresponding response. */
    private static void updateTaskStatus(String taskNumberText, boolean isDone) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberText);
        } catch (NumberFormatException exception) {
            throw new MimiMeowException("The task number must be a positive whole number.");
        }
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new MimiMeowException("Mimi cannot find task " + taskNumber + ". Check the task number");
        } else {
            Task task = taskList.get(taskNumber - 1);
            if (isDone) {
                task.setAsDone();
                printWithIndent("Nice! Meow've marked this task as done:");
            } else {
                task.setAsNotDone();
                printWithIndent("OK, Meow've marked this task as not done yet:");
            }
            printWithIndent(task.toString());
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
     * @param taskDescription description entered by the user
     */
    private static void printMimiReply(String taskDescription) {
        printWithIndent("(^._.^) meows: Got it! Meow'hv added this task:");

        printWithIndent(taskDescription);
        if (taskList.size() == 1) {
            printWithIndent("NOW you have 1 task in the list.");
        } else {
            printWithIndent("NOW you have " + taskList.size() + " tasks in the list.");
        }

    }

    /** Prints all tasks currently stored in the task list. */
    private static void printTaskList() {
        if (taskList.size() > 1) {
            printWithIndent("Here are the tasks in your list:");
        } else if (taskList.size() == 1) {
            printWithIndent("Here is the task in your list:");
        } else {
            printWithIndent("There are no tasks in your list yet!");
        }

        for (int i = 0; i < taskList.size(); i++) {
            printWithIndent((i + 1) + ". " + taskList.get(i));
        }
    }
}
