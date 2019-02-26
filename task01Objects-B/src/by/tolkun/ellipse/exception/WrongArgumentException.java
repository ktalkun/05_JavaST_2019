package by.tolkun.ellipse.exception;

/**
 * Class of {@code Exception} reflection wrong input data.
 *
 * @author Kirill Tolkun
 */
public class WrongArgumentException extends Exception {

    /**
     * Default constructor.
     */
    public WrongArgumentException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param messsage of exception
     */
    public WrongArgumentException(final String messsage) {
        super(messsage);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public WrongArgumentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public WrongArgumentException(final Throwable cause) {
        super(cause);
    }
}
