package mimimeow.command;

/** Represents an error caused by invalid input given to MimiMeow. */
public class MimiMeowException extends RuntimeException {
    /** Creates an exception with the specified user-facing message. */
    public MimiMeowException(String message) {
        super(message);
    }

    /** Creates an exception with a user-facing message and its underlying cause. */
    public MimiMeowException(String message, Throwable cause) {
        super(message, cause);
    }
}
