package by.tolkun.ellipse.parser;

import by.tolkun.ellipse.validator.StringValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for parsing input data (string) to double array.
 *
 * @author Kirill Tolkun
 */
public class DoubleArrayParser {

    /**
     * Pattern for parsing. String will be parsed into double numbers.
     */
    public static final Pattern NUM_PATTERN =
            Pattern.compile("[+-]*((\\d+\\.?\\d*)|(\\d*\\.?\\d+))");

    /**
     * Logger of class {@code DoubleArrayParser}.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(DoubleArrayParser.class);

    /**
     * Default constructor of utility class.
     */
    public DoubleArrayParser() {
        LOGGER.debug("DoubleArrayParser created.");
    }

    /**
     * Parse input string to double array.
     *
     * @param data is input data for parsing into double array
     * @return double array
     */
    public double[] parse(final String data) {
        List<String> coordinates = new ArrayList<>();
        StringValidator stringValidator = new StringValidator();
        if (stringValidator.isValid(data)) {
            Matcher matcher = NUM_PATTERN.matcher(data);
            while (matcher.find()) {
                coordinates.add(matcher.group());
            }
        } else {
            LOGGER.warn("Invalid input data: {}.", data);
        }
        double[] arrayCoordinates = coordinates
                .stream()
                .mapToDouble(Double::parseDouble)
                .toArray();
        LOGGER.debug("String \"{}\" parsed into {}.", data,
                Arrays.toString(arrayCoordinates));
        return arrayCoordinates;
    }
}
