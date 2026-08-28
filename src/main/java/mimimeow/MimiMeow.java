package mimimeow;

import java.util.Scanner;

/** Runs the MimiMeow command-line task manager. */
public class MimiMeow {
    private static final Task[] storedTasks = new Task[100];
    private static int storedTasksCount = 0;

    public static void main(String[] args) {
        String userInput;
        String banner =
                  "      ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )      \n"
                + " ███╗   ███╗██╗███╗   ███╗██╗███╗   ███╗███████╗ ██████╗ ██╗    ██╗\n"
                + " ████╗ ████║██║████╗ ████║██║████╗ ████║██╔════╝██╔═══██╗██║    ██║\n"
                + " ██╔████╔██║██║██╔████╔██║██║██╔████╔██║█████╗  ██║   ██║██║ █╗ ██║\n"
                + " ██║╚██╔╝██║██║██║╚██╔╝██║██║██║╚██╔╝██║██╔══╝  ██║   ██║██║███╗██║\n"
                + " ██║ ╚═╝ ██║██║██║ ╚═╝ ██║██║██║ ╚═╝ ██║███████╗╚██████╔╝╚███╔███╔╝\n"
                + " ╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚═╝╚═╝     ╚═╝╚══════╝ ╚═════╝  ╚══╝╚══╝ \n"
                + "      ( o.o ) | | (^._.^) | | (｡♥‿♥｡) | | (^._.^) | | ( o.o )      \n";
        System.out.println(banner);
        printWithIndent("Hello! I'm MimiMeow.");
        printWithIndent("What can I do for you?");
        printWithIndent("─".repeat(60));
        printWithIndent("");
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine().trim();

            String[] parts = userInput.split("\\s+", 2);
            String command = parts[0];
            printWithIndent("─".repeat(60));
            switch (command) {
                case "bye":
                    printWithIndent("Bye. Hope to see you again soon!");
                    printWithIndent("─".repeat(60));
                    return;

                case "list":
                    printSavedTasks();
                    break;

                case "mark":
                    if (parts.length < 2) {
                        printWithIndent("Please specify a task number.");
                    } else {
                        markTask(parts[1]);
                    }
                    break;

                case "unmark":
                    if (parts.length < 2) {
                        printWithIndent("Please specify a task number.");
                    } else {
                        unmarkTask(parts[1]);
                    }
                    break;

                default:
                    appendStoredTask(userInput);

                    printMimiReply(userInput);
                    break;
            }
            printWithIndent("─".repeat(60));
        }
    }

    /** Adds a new task to the task list if storage is available. */
    public static void appendStoredTask(String message) {
        if (storedTasksCount < storedTasks.length) {
            storedTasks[storedTasksCount] = new Task(message);
            storedTasksCount++;
        } else {
            System.out.println("Storage is full.");
        }
    }

    private static void markTask(String taskNumberText) {

        int taskNumber = Integer.parseInt(taskNumberText);
        if (taskNumber > storedTasksCount) {
            printWithIndent("Meow has no idea what task " + taskNumber + " is.");
        } else {
            storedTasks[taskNumber - 1].setAsDone();
            printWithIndent("Nice! Meow've marked this task as done:");
            printWithIndent(storedTasks[taskNumber - 1].toString());
        }
    }

    private static void unmarkTask(String taskNumberText) {
        int taskNumber = Integer.parseInt(taskNumberText);
        if (taskNumber > storedTasksCount) {
            printWithIndent("Meow has no idea what task " + taskNumber + " is.");
        } else {
            storedTasks[taskNumber - 1].setAsNotDone();
            printWithIndent("OK, Meow've marked this task as not done yet:");
            printWithIndent(storedTasks[taskNumber - 1].toString());
        }
    }
    private static void printWithIndent(String message) {
        System.out.println("    " + message);
    }

    private static void printMimiReply(String message) {
        printWithIndent("(^._.^) meows:");
        printWithIndent("added: " + message);
    }

    private static void printSavedTasks() {
        if (storedTasksCount > 1) {
            printWithIndent("Here are the tasks in your list:");
        } else if (storedTasksCount == 1) {
            printWithIndent("Here is the task in your list:");
        } else {
            printWithIndent("There are no tasks in your list yet!");
        }

        for (int i = 0; i < storedTasksCount; i++) {
            printWithIndent((i + 1) + ". " + storedTasks[i]);
        }
    }
}
