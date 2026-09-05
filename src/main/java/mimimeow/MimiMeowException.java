package mimimeow;

/** Represents an error caused by invalid input given to MimiMeow. */
public class MimiMeowException extends RuntimeException {
    /** Creates an exception with the specified user-facing message. */
    public MimiMeowException(String message) {
        super(message);
    }
}
