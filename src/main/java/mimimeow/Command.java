package mimimeow;

/** Represents a parsed MimiMeow command and its optional arguments. */
public class Command {
    private final String word;
    private final String arguments;

    /** Creates a command with the specified word and arguments. */
    public Command(String word, String arguments) {
        this.word = word;
        this.arguments = arguments;
    }

    /** Returns the command word. */
    public String getWord() {
        return word;
    }

    /** Returns the command arguments, or an empty string when none were supplied. */
    public String getArguments() {
        return arguments;
    }
}
