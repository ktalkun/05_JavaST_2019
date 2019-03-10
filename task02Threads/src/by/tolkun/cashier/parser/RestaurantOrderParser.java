package by.tolkun.cashier.parser;

import by.tolkun.cashier.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.AbstractMap;
import java.util.StringTokenizer;

/**
 * Class for parsing input string to data for creating {@code RestaurantOrder}.
 *
 * @author Kirill Tolkun
 */
public class RestaurantOrderParser {

    /**
     * Logger of class {@code RestaurantOrderParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RestaurantOrderParser.class);

    /**
     * Default constructor.
     */
    public RestaurantOrderParser() {
        LOGGER.debug("RestaurantOrderParser created.");
    }

    /**
     * Parse input string to data for creating {@code RestaurantOrder}.
     *
     * @param data for parsing
     * @return pair of complexity and flag isPreOrder of {@code RestaurantOrder}
     */
    public AbstractMap.SimpleEntry<Integer, Boolean> parse(final String data) {
        AbstractMap.SimpleEntry<Integer, Boolean> pair = null;
        StringValidator stringValidator = new StringValidator();
        if (stringValidator.isValid(data)) {
            StringTokenizer stringTokenizer
                    = new StringTokenizer(data, " ");
            Integer complexity = Integer.valueOf(stringTokenizer.nextToken());
            Boolean isPreOrder = Boolean.valueOf(stringTokenizer.nextToken());
            pair = new AbstractMap.SimpleEntry<>(complexity, isPreOrder);
        } else {
            LOGGER.warn("String \"{}\" for parsing is inValid", data);
        }
        return pair;
    }
}
