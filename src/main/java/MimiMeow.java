import java.util.Scanner;

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
        printwithIndent("Hello! I'm MimiMeow.");
        printwithIndent("What can I do for you?");
        printwithIndent("─".repeat(60));
        printwithIndent("");
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine().trim();

            String[] parts = userInput.split("\\s+", 2);
            String command = parts[0];
            printwithIndent("─".repeat(60));
            switch (command) {
                case "bye":
                    printwithIndent("Bye. Hope to see you again soon!");
                    printwithIndent("─".repeat(60));
                    return;

                case "list":
                    printSavedTasks();
                    break;

                case "mark":
                    if (parts.length < 2) {
                        printwithIndent("Please specify a task number.");
                    } else {
                        markTask(parts[1]);
                    }
                    break;

                case "unmark":
                    if (parts.length < 2) {
                        printwithIndent("Please specify a task number.");
                    } else {
                        unmarkTask(parts[1]);
                    }
                    break;

                default:
                    appendStoredTask(userInput);

                    printMimiReply(userInput);
                    break;
            }
            printwithIndent("─".repeat(60));
        }
    }

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
            printwithIndent("Meow has no idea what task " + taskNumber + " is.");
        }else {
            storedTasks[taskNumber - 1].setAsDone();
            printwithIndent("Nice! Meow've marked this task as done:");
            printwithIndent(storedTasks[taskNumber - 1].toString());
        }
    }

    private static void unmarkTask(String taskNumberText) {
        int taskNumber = Integer.parseInt(taskNumberText);
        if (taskNumber > storedTasksCount) {
            printwithIndent("Meow has no idea what task " + taskNumber + " is.");
        }else {
            storedTasks[taskNumber - 1].setAsNotDone();
            printwithIndent("OK, Meow've marked this task as not done yet:");
            printwithIndent(storedTasks[taskNumber - 1].toString());
        }
    }
    private static void printwithIndent(String message) {
        System.out.println("    " + message);
    }

    private static void printMimiReply(String message) {
        printwithIndent("(^._.^) meows:");
        printwithIndent("added: " + message);
    }

    private static void printSavedTasks() {
        if (storedTasksCount > 1){
            printwithIndent("Here are the tasks in your list:");
        }else if (storedTasksCount == 1){
            printwithIndent("Here is the task in your list:");
        }else{
            printwithIndent("There is no task in you list yet!");
        }

        for (int i = 0; i < storedTasksCount; i++) {
            printwithIndent((i + 1) + ". " + storedTasks[i]);
        }
    }
}
