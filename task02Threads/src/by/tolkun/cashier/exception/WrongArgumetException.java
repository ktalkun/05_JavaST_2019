package by.tolkun.cashier.exception;

/**
 * Class of {@code Exception} reflection wrong input data.
 *
 * @author Kirill Tolkun
 */
public class WrongArgumetException extends Exception {

    /**
     * Default constructor.
     */
    public WrongArgumetException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     */
    public WrongArgumetException(final String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public WrongArgumetException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public WrongArgumetException(final Throwable cause) {
        super(cause);
    }
}
