package by.tolkun.cashier.factory;

import by.tolkun.cashier.entity.RestaurantCashier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class of factory for producing {@code RestaurantCashier}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantCashierFactory {

    /**
     * Logger of class {@code RestaurantCashierFactory}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantCustomerFactory.class);

    /**
     * Default constructor.
     */
    public RestaurantCashierFactory() {
        LOGGER.debug("RestaurantCashierFactory created.");
    }

    /**
     * Factory method for creating {@code RestaurantCashier}.
     *
     * @return {@code RestaurantCashier}
     */
    public RestaurantCashier createCashier() {
        return new RestaurantCashier();
    }
}
