package mimimeow.command;

import mimimeow.exception.MimiMeowException;

/** Represents an error caused by an invalid command or command argument. */
public class CommandException extends MimiMeowException {
    /** Creates an exception with the specified user-facing message. */
    public CommandException(String message) {
        super(message);
    }
}
