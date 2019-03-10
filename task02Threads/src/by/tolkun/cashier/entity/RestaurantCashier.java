package by.tolkun.cashier.entity;

import by.tolkun.cashier.exception.WrongArgumetException;
import by.tolkun.cashier.factory.RestaurantCheckFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class provide serving {@code RestaurantOrder} in {@code Restaurant}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantCashier {

    /**
     * Unique identifier.
     */
    private final int id;

    /**
     * Counter (static field) for creating unique identifier {@code id}.
     */
    private static int count;

    /**
     * Number of customers serving by cashier.
     */
    private int queueLength;

    /**
     * Default timeout (milliseconds) spent to serve one point of customer's
     * order.
     */
    private static final int DEFAULT_TIMEOUT = 50;

    /**
     * Logger of class {@code RestaurantCashier}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantCashier.class);

    /**
     * Locker for locking code area by default {@code RestaurantOrder}.
     */
    private ReentrantLock defaultOrderLock;


    /**
     * Locker for locking code area by default or pre {@code RestaurantOrder}.
     */
    private ReentrantLock preOrderLock;

    /**
     * Default constructor.
     */
    public RestaurantCashier() {
        id = ++count;
        defaultOrderLock = new ReentrantLock();
        preOrderLock = new ReentrantLock();
        LOGGER.debug("Cashier created.");
    }

    /**
     * Get number customers serving by cashier.
     *
     * @return number of serving customers
     */
    public int getQueueLength() {
        return queueLength;
    }

    /**
     * Increase by one number of customers serving by cashier.
     */
    public void incQueueLength() {
        queueLength++;
    }


    /**
     * Serve order.
     *
     * @param order serving by current cashier
     * @return {@code RestaurantCheck} after serving {@code Order}
     * @throws WrongArgumetException the wrong argument exception
     */
    public RestaurantCheck serve(final RestaurantOrder order)
            throws WrongArgumetException {
        if (!order.isPreOrder()) {
            defaultOrderLock.lock();
        }
        preOrderLock.lock();
        RestaurantCheck check = null;
        try {
            if (!order.isPreOrder()) {
                TimeUnit.MILLISECONDS.sleep((long) DEFAULT_TIMEOUT
                        * order.getComplexity());
            }
            order.complete();
            RestaurantCheckFactory checkFactory
                    = new RestaurantCheckFactory();
            check = checkFactory.createCheck(id, order.getId(), order.getComplexity());
        } catch (InterruptedException e) {
            LOGGER.error(e);
            Thread.currentThread().interrupt();
        } finally {
            if (!order.isPreOrder()) {
                defaultOrderLock.unlock();
            }
            preOrderLock.unlock();
            queueLength--;
        }

        return check;

//        return new RestaurantCheck(id, order.getId(), order.getComplexity());
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code RestaurantCashier} object that
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
        RestaurantCashier that = (RestaurantCashier) o;
        return id == that.id
                && queueLength == that.queueLength
                && Objects.equals(defaultOrderLock, that.defaultOrderLock)
                && Objects.equals(preOrderLock, that.preOrderLock);
    }

    /**
     * Returns a hash code for a {@code RestaurantCashier}.
     *
     * @return a hash code value for a {@code RestaurantCashier}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, queueLength, defaultOrderLock, preOrderLock);
    }

    /**
     * Returns a {@code String} object representing this
     * {@code RestaurantCashier}.
     *
     * @return a string representation of the information of this
     * object
     */
    @Override
    public String toString() {
        return "RestaurantCashier{"
                + "id=" + id
                + ", queueLength=" + queueLength
                + '}';
    }
}
