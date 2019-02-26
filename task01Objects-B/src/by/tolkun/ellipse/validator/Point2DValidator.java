package by.tolkun.ellipse.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Class for validation on the possibility of creating {@code Point}.
 *
 * @author Kirill Tolkun
 */
public class Point2DValidator {

    /**
     * Number of coordinates needed for creating point.
     */
    private static final int COUNT_COORD_POINT2D = 2;

    /**
     * Index of coordinate <b>x</b>. Coordinate <b>x</b> is first coordinate of.
     */
    public static final int INDEX_COORD_X = 0;

    /**
     * Index of coordinate <b>y</b>. Coordinate <b>y</b> is second coordinate of
     * point.
     */
    public static final int INDEX_COORD_Y = 1;

    /**
     * Logger for class {@code Point2DValidator}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Point2DValidator.class);

    /**
     * Default constructor for utility class.
     */
    public Point2DValidator() {
        LOGGER.debug("Point2DValidator created.");
    }

    /**
     * Checks coordinates for validity. Coordinates should be two for creating
     * one point.
     *
     * @param coordinates of {@code Point}
     * @return {@code true} if input coordinates is valid,
     * {@code false} otherwise
     */
    public boolean isValid(final double[] coordinates) {
        boolean result = coordinates.length == COUNT_COORD_POINT2D;
        String isValid = "valid";
        if (!result) {
            isValid = "invalid";
        }
        LOGGER.debug("Dataset {} is {}.", Arrays.toString(coordinates),
                isValid);
        return result;
    }
}
