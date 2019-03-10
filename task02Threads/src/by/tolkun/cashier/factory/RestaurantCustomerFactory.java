package by.tolkun.cashier.factory;

import by.tolkun.cashier.entity.RestaurantCustomer;
import by.tolkun.cashier.entity.RestaurantOrder;
import by.tolkun.cashier.exception.WrongArgumetException;
import by.tolkun.cashier.repository.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class of factory for producing {@code RestaurantCustomer}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantCustomerFactory {

    /**
     * Logger of class {@code RestaurantCustomerFactory}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantCustomerFactory.class);

    /**
     * Default constructor.
     */
    public RestaurantCustomerFactory() {
        LOGGER.debug("RestaurantCustomerFactory created.");
    }

    /**
     * Factory method for creating {@code RestaurantCustomer}.
     *
     * @param restaurant with cashiers for serving customer's orders
     * @return {@code RestaurantCustomer}
     * @throws WrongArgumetException if {@code restaurant} is null
     */
    public RestaurantCustomer createCustomer(final Restaurant restaurant)
            throws WrongArgumetException {
        if (restaurant != null) {
            return new RestaurantCustomer(restaurant);
        } else {
            throw new WrongArgumetException("Invalid input restaurant (null).");
        }

    }

    /**
     * Factory method for creating {@code RestaurantCustomer}.
     *
     * @param orders     for serving by restaurant's cashiers
     * @param restaurant with cashiers for serving customer's orders
     * @return {@code RestaurantCustomer}
     * @throws WrongArgumetException if there aren't orders
     */
    public RestaurantCustomer createCustomer(final Restaurant restaurant,
                                             final List<RestaurantOrder> orders)
            throws WrongArgumetException {
        RestaurantCustomerFactory restaurantCustomerFactory
                = new RestaurantCustomerFactory();
        RestaurantCustomer restaurantCustomer
                = restaurantCustomerFactory.createCustomer(restaurant);
        if (orders != null) {
            for (RestaurantOrder order : orders) {
                restaurantCustomer.addOrder(order);
            }
        } else {
            throw new WrongArgumetException("Invalid list of orders (null).");
        }
        return restaurantCustomer;
    }

    /**
     * Factory method for creating {@code RestaurantCustomer}.
     *
     * @param restaurant for serving customer's orders
     * @param orders     of customer
     * @return {@code RestaurantCustomer}
     * @throws WrongArgumetException if input data is invalid
     */
    public RestaurantCustomer createCustomer(final Restaurant restaurant,
                                             final RestaurantOrder... orders)
            throws WrongArgumetException {
        if (orders != null) {
            RestaurantCustomerFactory restaurantCustomerFactory
                    = new RestaurantCustomerFactory();
            return restaurantCustomerFactory
                    .createCustomer(restaurant, Arrays.asList(orders));
        } else {
            throw new WrongArgumetException("Invalid array of orders (null).");
        }
    }

    /**
     * Factory method for creating {@code RestaurantCustomer}.
     *
     * @param restaurant for serving customer's orders
     * @param orders     of customer
     * @return {@code RestaurantCustomer}
     * @throws WrongArgumetException if input data is invalid
     */
    public RestaurantCustomer createCustomer(final Restaurant restaurant,
                                             final String... orders)
            throws WrongArgumetException {
        if (orders != null) {
            RestaurantOrderFactory restaurantOrderFactory
                    = new RestaurantOrderFactory();
            List<RestaurantOrder> listOrders = new ArrayList<>();
            for (String order : orders) {
                listOrders.add(restaurantOrderFactory.createOrder(order));
            }
            RestaurantCustomerFactory restaurantCustomerFactory
                    = new RestaurantCustomerFactory();
            return restaurantCustomerFactory
                    .createCustomer(restaurant, listOrders);
        } else {
            throw new WrongArgumetException("Invalid array of orders (null).");
        }
    }
}
