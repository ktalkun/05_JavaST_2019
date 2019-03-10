package by.tolkun.cashier.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Class for validation on the possibility of parsing into
 * {@code RestaurantOrder}.
 *
 * @author Kirill Tolkun
 */
public class StringValidator {

    /**
     * Pattern for validation.
     */
    public static final Pattern STRING_ORDER_PATTERN
            = Pattern.compile("\\d+\\s+(true|false)");

    /**
     * Logger of class {@code StringValidator}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(StringValidator.class);

    /**
     * Default constructor.
     */
    public StringValidator() {
        LOGGER.debug("StringValidator created.");
    }

    /**
     * Validate string for parsing in data for creating {@code RestaurantOrder}.
     *
     * @param textLine the string for validation
     * @return {@code true} if {@code textLine} is valid,
     * {@code false} otherwise
     */
    public boolean isValid(final String textLine) {
        return STRING_ORDER_PATTERN.matcher(textLine).matches();
    }
}
