import java.util.Scanner;

public class MimiMeow {
    private static final String[] storedUI = new String[100];
    private static int storedUICount = 0;

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
            userInput = in.nextLine();
            printwithIndent("─".repeat(60));
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                printSavedUI();
            }else{
                appendStoredUI(userInput);
                printwithIndent("(^._.^) says:");
                printMimiReply(userInput);
            }

            printwithIndent("─".repeat(60));
        }

        printwithIndent("Bye. Hope to see you again soon!");
        printwithIndent("─".repeat(60));
        printwithIndent("");
    }


    private static void printwithIndent(String message) {
        System.out.println("    " + message);
    }

    private static void printMimiReply(String message) {
        System.out.println("    added: " + message);
    }

    private static void printSavedUI() {
        for (int i = 0; i < storedUICount; i++) {
            printwithIndent((i+1) + "." + storedUI[i]);
        }
    }

    public static String [] getStoredUI() {
        return storedUI;
    }

    public static void appendStoredUI(String message) {
        if (storedUICount < storedUI.length) {
            storedUI[storedUICount] = message;
            storedUICount++;
        } else {
            System.out.println("Storage is full.");
        }
    }
}
