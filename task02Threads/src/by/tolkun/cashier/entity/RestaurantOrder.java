package by.tolkun.cashier.entity;

import by.tolkun.cashier.exception.WrongArgumetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Class to store information about of {@code RestaurantCustomer}'s
 * {@code RestaurantOrder}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantOrder {

    /**
     * Unique identifier.
     */
    private final int id;

    /**
     * Counter (static field) for creating unique identifier {@code id}.
     */
    private static int count;

    /**
     * Complexity defining timeout during the serving order.
     */
    private int complexity;

    /**
     * Flag defines is pre order.
     */
    private boolean isPreOrder;

    /**
     * Flag defines order's state of service.
     */
    private boolean isCompleted;

    /**
     * Logger of class {@code RestaurantOrder}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantOrder.class);

    /**
     * Default constructor.
     */
    public RestaurantOrder() {
        this(1, false);
    }

    /**
     * Constructor with parameters.
     *
     * @param inputComplexity the complexity of order
     * @param inputIsPreOrder the state of order, {@code true} if it's
     *                        pre order, {@code false} otherwise
     */
    public RestaurantOrder(final int inputComplexity,
                           final boolean inputIsPreOrder) {
        id = ++count;
        complexity = inputComplexity;
        isPreOrder = inputIsPreOrder;
        LOGGER.debug("RestaurantOrder created."
                + (isPreOrder ? "[PRE]" : "[NOPRE]"));
    }

    /**
     * Get unique identifier of order.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get complexity of order.
     *
     * @return complexity.
     */
    public int getComplexity() {
        return complexity;
    }

    /**
     * Set new complexity of order.
     *
     * @param inputComplexity new complexity
     * @throws WrongArgumetException if new complexity <= 0
     */
    public void setComplexity(final int inputComplexity)
            throws WrongArgumetException {
        if (inputComplexity <= 0) {
            throw new WrongArgumetException("Invalid input complexity");
        }
        complexity = inputComplexity;
    }

    /**
     * Check is pre order.
     *
     * @return {@code true} is it's pre order, {@code false} otherwise
     */
    public boolean isPreOrder() {
        return isPreOrder;
    }

    /**
     * Get service state.
     *
     * @return {@code true} if order completed (served),
     * {@code false} otherwise
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Set service state as completed (served).
     */
    public void complete() {
        isCompleted = true;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code RestaurantOrder} object that
     * contains the same field values.
     *
     * @param o the object to compare with.
     * @return {@code true} if the objects are the same;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RestaurantOrder that = (RestaurantOrder) o;
        return id == that.id
                && complexity == that.complexity
                && isPreOrder == that.isPreOrder
                && isCompleted == that.isCompleted;
    }

    /**
     * Returns a hash code for a {@code RestaurantOrder}.
     *
     * @return a hash code value for a {@code RestaurantOrder}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, complexity, isPreOrder, isCompleted);
    }

    /**
     * Returns a {@code String} object representing this
     * {@code RestaurantOrder}.
     *
     * @return a string representation of the information of this
     * object
     */
    @Override
    public String toString() {
        return "RestaurantOrder{"
                + "id=" + id
                + ", complexity=" + complexity
                + ", isPreOrder=" + isPreOrder
                + ", isCompleted=" + isCompleted
                + '}';
    }
}
