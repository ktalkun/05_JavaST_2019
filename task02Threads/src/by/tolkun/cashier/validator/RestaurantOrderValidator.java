package by.tolkun.cashier.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for validation on the possibility of creation {@code RestaurantOrder}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantOrderValidator {

    /**
     * Logger of class {@code RestaurantOrderValidator}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantOrderValidator.class);

    /**
     * Default constructor.
     */
    public RestaurantOrderValidator() {
        LOGGER.debug("RestaurantOrderValidator created.");
    }

    /**
     * Validate data for creation {@code RestaurantOrder}.
     *
     * @param complexity of creating {@code RestaurantOrder}
     * @param isPreOrder of creating {@code RestaurantOrder}
     * @return {@code true} if input data is valid to create
     * {@code RestaurantOrder}, {@code false} otherwise
     */
    public boolean isValid(final int complexity, final boolean isPreOrder) {
        if (complexity > 0) {
            return true;
        }
        return false;
    }
}
