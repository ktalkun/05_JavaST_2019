package by.tolkun.cashier.exception;

/**
 * Class of {@code Exception} reflection the absence of cashiers in
 * {@code Restaurant}.
 *
 * @author Kirill Tolkun
 */
public class CashierAmountException extends Exception {

    /**
     * Default constructor.
     */
    public CashierAmountException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     */
    public CashierAmountException(final String message) {
        super(message);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public CashierAmountException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public CashierAmountException(final Throwable cause) {
        super(cause);
    }
}
