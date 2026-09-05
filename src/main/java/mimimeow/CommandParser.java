package mimimeow;

/** Converts raw user input into structured MimiMeow commands. */
public class CommandParser {
    /** Parses a line into its command word and optional arguments. */
    public Command parse(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new MimiMeowException("Meow? Mimi needs a command to get started.");
        }
        String[] commandParts = userInput.trim().split("\\s+", 2);
        String commandWord = commandParts[0];
        String commandArguments = commandParts.length > 1 ? commandParts[1] : "";
        return new Command(commandWord, commandArguments);
    }
}
