import java.util.Scanner;

public class MimiMeow {
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
        printMimi("Hello! I'm MimiMeow.");
        printMimi("What can I do for you?");
        printMimi("─".repeat(60));
        printMimi("");
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine();
            printMimi("─".repeat(60));
            if (userInput.equals("bye")) {
                break;
            }
            printMimi("(^._.^) says:");
            printMimi(userInput);
            printMimi("─".repeat(60));
        }

        printMimi("Bye. Hope to see you again soon!");
        printMimi("─".repeat(60));
        printMimi("");
    }


    private static void printMimi(String message) {
        System.out.println("    " + message);
    }
}
