package by.tolkun.cashier.factory;

import by.tolkun.cashier.entity.RestaurantOrder;
import by.tolkun.cashier.exception.WrongArgumetException;
import by.tolkun.cashier.parser.RestaurantOrderParser;
import by.tolkun.cashier.validator.RestaurantOrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap;

/**
 * Class of factory for producing {@code RestaurantOrder}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantOrderFactory {

    /**
     * Logger of class {@code RestaurantOrderFactory}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantOrderFactory.class);

    /**
     * Default constructor.
     */
    public RestaurantOrderFactory() {
        LOGGER.debug("RestaurantOrderFactory created.");
    }

    /**
     * Factory method for creating {@code RestaurantOrder}.
     *
     * @param complexity the complexity of order. Define time of serving by
     *                   {@code RestaurantCashier}
     * @param isPreOrder the state of order, {@code true} if it's pre order,
     *                   {@code false} otherwise
     * @return {@code RestaurantOrder}
     * @throws WrongArgumetException if complexity <= 0
     */
    public RestaurantOrder createOrder(final int complexity,
                                       final boolean isPreOrder)
            throws WrongArgumetException {
        RestaurantOrderValidator restaurantOrderValidator
                = new RestaurantOrderValidator();
        if (!restaurantOrderValidator.isValid(complexity, isPreOrder)) {
            throw new WrongArgumetException("Invalid complexity.");
        }
        return new RestaurantOrder(complexity, isPreOrder);
    }

    /**
     * Factory method for creating {@code RestaurantOrder}.
     *
     * @param data for creating {@code RestaurantOrder}
     * @return {@code RestaurantOrder}
     * @throws WrongArgumetException if {@code data} is invalid
     */
    public RestaurantOrder createOrder(final String data)
            throws WrongArgumetException {
        RestaurantOrder order;
        RestaurantOrderParser restaurantOrderParser
                = new RestaurantOrderParser();
        AbstractMap.SimpleEntry<Integer, Boolean> pair
                = restaurantOrderParser.parse(data);
        if (pair != null) {
            RestaurantOrderFactory restaurantOrderFactory
                    = new RestaurantOrderFactory();
            int complexity = pair.getKey();
            boolean isPreOrder = pair.getValue();
            order = restaurantOrderFactory.createOrder(complexity, isPreOrder);
        } else {
            throw new WrongArgumetException("Invalid input data \""
                    + data + "\"");
        }
        return order;
    }
}
