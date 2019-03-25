package by.tolkun.infohandler.exception;

/**
 * Class of {@code Exception} reflection wrong function all.
 *
 * @author Kirill Tolkun
 */
public class UnsupportedMethodException extends Exception {
    /**
     * Default constructor.
     */
    public UnsupportedMethodException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param messsage of exception
     */
    public UnsupportedMethodException(final String messsage) {
        super(messsage);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public UnsupportedMethodException(final String message,
                                      final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public UnsupportedMethodException(final Throwable cause) {
        super(cause);
    }
}
