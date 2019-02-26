package by.tolkun.ellipse.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Class for validation on the possibility of parsing into double array.
 *
 * @author Kirill Tolkun
 */
public class StringValidator {

    /**
     * Pattern for validation. Input string will be match to pattern if input
     * string consists only of numbers (double or integer).
     */
    public static final Pattern SEQUENCE_NUM_PATTERN =
            Pattern.compile("([+-]?((\\d+\\.?\\d*)"
                    + "|(\\d*\\.?\\d+))([\\s,;]+|))*");

    /**
     * Logger for class {@code StringValidator}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(StringValidator.class);

    /**
     * Default constructor of utility class.
     */
    public StringValidator() {
        LOGGER.debug("StringValidator created.");
    }

    /**
     * @param textLine is input string fo validation.
     * @return {@code true} if input string is valid for parsing,
     * {@code false} otherwise
     */
    public boolean isValid(final String textLine) {
        final boolean result = SEQUENCE_NUM_PATTERN.matcher(textLine).matches();
        String isValid = "valid";
        if (!result) {
            isValid = "invalid";
        }
        LOGGER.debug("String \"{}\" is {}.", textLine, isValid);
        return result;
    }
}
