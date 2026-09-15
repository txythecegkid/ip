package mimimeow.ui;

import mimimeow.task.Task;
import mimimeow.task.TaskList;

import java.util.Scanner;

/** Handles input and output for the MimiMeow command-line interface. */
public class MimiMeowUi {
    /** Reads and trims one line of user input. */
    public String readUserInput(Scanner inputScanner) {
        return inputScanner.nextLine().trim();
    }

    /** Prints MimiMeow's welcome banner and introductory message. */
    public void showWelcomeMessage() {
        System.out.println(createBanner());
        printWithIndent("Hello! I'm MimiMeow.");
        printWithIndent("What can I do for you?");
        showSeparator();
        printWithIndent("");
    }

    /** Prints the separator used to format the command-line interface. */
    public void showSeparator() {
        printWithIndent("─".repeat(60));
    }

    /** Prints MimiMeow's goodbye message. */
    public void showGoodbyeMessage() {
        printWithIndent("Bye. Hope to see you again soon!");
    }

    /** Prints a user-facing error message in MimiMeow's voice. */
    public void showError(String message) {
        printWithIndent("Miiiision impossible! " + message);
    }

    /** Prints the response after adding a task. */
    public void showTaskAdded(Task task, int taskCount) {
        printWithIndent("(^._.^) meows: Got it! Meow'hv added this task:");
        printWithIndent(task.toString());
        printWithIndent("NOW you have " + taskCount
                + (taskCount == 1 ? " task in the list." : " tasks in the list."));
    }

    /** Prints all tasks currently stored in MimiMeow's task list. */
    public void showTaskList(TaskList taskList) {
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

    /** Prints the response after changing a task's completion status. */
    public void showTaskStatus(Task task, boolean isDone) {
        printWithIndent(isDone
                ? "Nice! Meow've marked this task as done:"
                : "OK, Meow've marked this task as not done yet:");
        printWithIndent(task.toString());
    }

    /** Prints the response after deleting a task. */
    public void showTaskDeleted(Task task, int taskCount) {
        printWithIndent("Noted! Meow've removed this task:");
        printWithIndent(task.toString());
        printWithIndent("Now you have " + taskCount
                + (taskCount == 1 ? " task in the list." : " tasks in the list."));
    }

    private void printWithIndent(String message) {
        System.out.println("    " + message);
    }

    private String createBanner() {
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
}
