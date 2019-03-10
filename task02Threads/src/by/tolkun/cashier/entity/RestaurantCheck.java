package by.tolkun.cashier.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Class to store information about completed {@code RestaurantOrder}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantCheck {

    /**
     * Unique identifier.
     */
    private final int id;

    /**
     * Counter (static field) for creating unique identifier {@code id}.
     */
    private static int count;

    /**
     * Identifier of the corresponding cashier.
     */
    private int cashierId;

    /**
     * Identifier of the corresponding completed order.
     */
    private int orderId;

    /**
     * Complexity of the corresponding completed order.
     */
    private int orderComplexity;

    /**
     * Logger of class {@code RestaurantCheck}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantCheck.class);

    /**
     * Constructor with parameters.
     *
     * @param inputCashierId       the id of the corresponding cashier
     * @param inputOrderId         the id of the corresponding completed
     *                             order
     * @param inputOrderComplexity the orderComplexity of corresponding
     *                             completed order
     */
    public RestaurantCheck(final int inputCashierId,
                           final int inputOrderId,
                           final int inputOrderComplexity) {
        id = ++count;
        cashierId = inputCashierId;
        orderId = inputOrderId;
        orderComplexity = inputOrderComplexity;
        LOGGER.debug("Check created.");
    }

    /**
     * Get identifier of the corresponding cashier.
     *
     * @return identifier of {@code Cashier}
     */
    public int getCashierId() {
        return cashierId;
    }

    /**
     * Get identifier of the corresponding completed order.
     *
     * @return identifier of {@code Order}
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Get complexity of corresponding complete order.
     *
     * @return orderComplexity of order
     */
    public int getOrderComplexity() {
        return orderComplexity;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Check} object that
     * contains the same points as this object.
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
        RestaurantCheck check = (RestaurantCheck) o;
        return cashierId == check.cashierId
                && orderId == check.orderId
                && orderComplexity == check.orderComplexity;
    }

    /**
     * Returns a hash code for a {@code Check}.
     *
     * @return a hash code value for a {@code Check}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cashierId, orderId, orderComplexity);
    }

    /**
     * Returns a {@code String} object representing this
     * {@code Check}.
     *
     * @return a string representation of the information of this
     * object
     */
    @Override
    public String toString() {
        return "Check{"
                + "id=" + id
                + ", cashierId='" + cashierId + '\''
                + ", orderId=" + orderId
                + ", orderComplexity=" + orderComplexity
                + '}';
    }
}
