package by.tolkun.cashier.factory;

import by.tolkun.cashier.entity.RestaurantCheck;
import by.tolkun.cashier.exception.WrongArgumetException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class of factory for producing {@code RestaurantCheck}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantCheckFactory {

    /**
     * Logger of class {@code RestaurantCheckFactory}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantCheckFactory.class);

    /**
     * Default constructor.
     */
    public RestaurantCheckFactory() {
        LOGGER.debug("RestaurantCheckFactory created.");
    }

    /**
     * Factory method for creating {@code RestaurantCheck}.
     * *
     * @param cashierId the id of corresponding cashier
     * @param orderId the id of corresponding completed order
     * @param orderComplexity the complexity of corresponding completed order
     * @return {@code RestaurantCheck}
     * @throws WrongArgumetException if {@code cashierId} <= 0
     *                               or (@code orderId) <= 0
     *                               or {@code orderComplexity} <= 0
     */
    public RestaurantCheck createCheck(final int cashierId,
                                       final int orderId,
                                       final int orderComplexity)
            throws WrongArgumetException {
        if (cashierId <= 0 || orderId <= 0 || orderComplexity <= 0) {
            throw new WrongArgumetException("Invalid input data.");
        }
        return new RestaurantCheck(cashierId, orderId, orderComplexity);
    }
}
