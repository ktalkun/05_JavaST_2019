package by.tolkun.cashier.entity;

import by.tolkun.cashier.exception.CashierAmountException;
import by.tolkun.cashier.repository.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class of customer with orders for serving in {@code Restaurant} by
 * {@code RestaurantCashier}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantCustomer implements Callable<List<RestaurantCheck>> {

    /**
     * Unique identifier.
     */
    private final int id;

    /**
     * Counter {static field} for creating unique identifier {@code id}.
     */
    private static int count;

    /**
     * {@code Order} corresponding the {@code Customer}.
     */
    private List<RestaurantOrder> orders;

    /**
     * {@code Restaurant} whose cashiers serve the customer's orders.
     */
    private Restaurant restaurant;

    /**
     * Lock for locking code area (synchronizing), where customer find cashier
     * for default order.
     */
    private static ReentrantLock defaultOrderLock = new ReentrantLock();

    /**
     * Lock for locking code area (synchronizing), where customer find cashier
     * for pre order.
     */
    private static ReentrantLock preOrderLock = new ReentrantLock();

    /**
     * Logger of class {@code RestaurantOrder}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantCustomer.class);

    /**
     * Constructor with parameters.
     *
     * @param inputRestaurant where orders will be served by cashiers
     */
    public RestaurantCustomer(final Restaurant inputRestaurant) {
        id = ++count;
        orders = new ArrayList<>();
        restaurant = inputRestaurant;
        LOGGER.debug("RestaurantCustomer created.");
    }

    /**
     * Get unique identifier of customer.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get lsit of orders corresponding current customer.
     *
     * @return {@code Order}
     */
    public List<RestaurantOrder> getOrders() {
        return orders;
    }

    /**
     * Add order.
     *
     * @param order to add
     * @return {@code true} if {@code order} added, {@code false} otherwise
     */
    public boolean addOrder(final RestaurantOrder order) {
        return orders.add(order);
    }

    /**
     * Serve {@code Order} in {@code Reentrant} by {@code Cashier}.
     *
     * @return {@code Check} with information about serving {@code Customer} by
     * {@code Cashier}
     * @throws Exception if throw any exception int call method
     */
    @Override
    public List<RestaurantCheck> call() throws Exception {
        RestaurantCashier cashier;
        RestaurantCheck check;
        List<RestaurantCheck> checks = new ArrayList<>();
        if (restaurant.getCapasity() == 0) {
            throw new CashierAmountException("No cashiers");
        }
        for (RestaurantOrder order : orders) {
            if (order.isPreOrder()) {
                cashier = restaurant.getCashier(new Random()
                        .nextInt(restaurant.getCapasity()));
                preOrderLock.lock();
                cashier.incQueueLength();
                System.out.printf("%9s%2d %6s%2d [%-5s] %s%n",
                        "Thread-", id,
                        "Order-", order.getId(),
                        "PRE",
                        "found cashier. " + cashier);
                LOGGER.debug("Customer-" + id
                        + "Order-" + order.getId()
                        + " [PRE] found cashier: " + cashier);
                preOrderLock.unlock();
            } else {
                defaultOrderLock.lock();
                cashier = restaurant.findCashierByShortestQueue();
                cashier.incQueueLength();
                System.out.printf("%9s%2d %6s%2d [%-5s] %s%n",
                        "Thread-", id,
                        "Order-", order.getId(),
                        "NOPRE",
                        "found cashier. " + cashier);
                LOGGER.debug("Customer-" + id
                        + "Order-" + order.getId()
                        + " [NOPRE] found cashier: " + cashier);
                defaultOrderLock.unlock();
            }
            check = cashier.serve(order);
            checks.add(check);
        }

        return checks;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code RestaurantCustomer} object that
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
        RestaurantCustomer that = (RestaurantCustomer) o;
        return id == that.id
                && Objects.equals(orders, that.orders)
                && Objects.equals(restaurant, that.restaurant);
    }

    /**
     * Returns a hash code for a {@code RestaurantCustomer}.
     *
     * @return a hash code value for a {@code RestaurantCustomer}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, orders, restaurant);
    }

    /**
     * Returns a {@code String} object representing this
     * {@code RestaurantCustomer}.
     *
     * @return a string representation of the information of this
     * object
     */
    @Override
    public String toString() {
        return "RestaurantCustomer{"
                + "id=" + id
                + ", orders=" + orders
                + ", restaurant=" + restaurant
                + '}';
    }
}
