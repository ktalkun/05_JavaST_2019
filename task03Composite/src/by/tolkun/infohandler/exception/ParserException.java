package by.tolkun.infohandler.exception;

/**
 * Class of {@code Exception} reflection wrong input data to parse.
 *
 * @author Kirill Tolkun
 */
public class ParserException extends Exception {

    /**
     * Default constructor.
     */
    public ParserException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param messsage of exception
     */
    public ParserException(final String messsage) {
        super(messsage);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public ParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public ParserException(final Throwable cause) {
        super(cause);
    }
}
