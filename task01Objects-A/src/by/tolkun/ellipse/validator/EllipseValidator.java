package by.tolkun.ellipse.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Class for validation on the possibility of creating {@code Ellipse}.
 *
 * @author Kirill Tolkun
 */
public class EllipseValidator {

    /**
     * Number of coordinates needed for creating rectangle described near
     * ellipse.
     */
    private static final int COUNT_COORD_RECTANGLE = 4;

    /**
     * Index of coordinate <b>x</b>. Coordinate <b>x</b> is first coordinate of
     * upper left point of rectangle described near ellipse.
     */
    public static final int INDEX_UPPER_LEFT_COORD_X = 0;

    /**
     * Index of coordinate <b>y</b>. Coordinate <b>y</b> is second coordinate of
     * upper left point of rectangle described near ellipse.
     */
    public static final int INDEX_UPPER_LEFT_COORD_Y = 1;

    /**
     * Index of coordinate <b>x</b>. Coordinate <b>x</b> is first coordinate of
     * lower right point of rectangle described near ellipse.
     */
    public static final int INDEX_LOWER_RIGHT_COORD_X = 2;

    /**
     * Index of coordinate <b>y</b>. Coordinate <b>y</b> is first coordinate of
     * lower right point of rectangle described near ellipse.
     */
    public static final int INDEX_LOWER_RIGHT_COORD_Y = 3;

    /**
     * Logger for class {@code EllipseValidator}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(EllipseValidator.class);

    /**
     * Default constructor of utility class.
     */
    public EllipseValidator() {
        LOGGER.debug("EllipseValidator created.");
    }

    /**
     * Checks coordinates for validity. Coordinates should be four for creating
     * two points. Points should set rectangle in square.
     *
     * @param coordinates of rectangle's points described near ellipse
     * @return {@code true} if input coordinates is valid,
     * {@code false} otherwise
     */
    public boolean isValid(final double[] coordinates) {
        boolean result = coordinates.length == COUNT_COORD_RECTANGLE
                && !(coordinates[INDEX_UPPER_LEFT_COORD_X]
                == coordinates[INDEX_LOWER_RIGHT_COORD_X]
                || coordinates[INDEX_UPPER_LEFT_COORD_Y]
                == coordinates[INDEX_LOWER_RIGHT_COORD_Y]);
        String isValid = "valid";
        if (!result) {
            isValid = "invalid";
        }
        LOGGER.debug("Dataset {} is {}.", Arrays.toString(coordinates),
                isValid);
        return result;
    }
}
