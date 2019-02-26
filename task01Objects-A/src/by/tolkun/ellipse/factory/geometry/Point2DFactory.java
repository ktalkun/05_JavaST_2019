package by.tolkun.ellipse.factory.geometry;

import by.tolkun.ellipse.entity.geometry.Geometry2D;
import by.tolkun.ellipse.entity.geometry.Point2D;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.parser.DoubleArrayParser;
import by.tolkun.ellipse.validator.Point2DValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Class of factory for producing {@code Point2D}.
 *
 * @author Kirill Tolkun
 */
public class Point2DFactory extends Geometric2DFactory {

    /**
     * Logger of class {@code Point2DFactory}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Point2DFactory.class);

    /**
     * Default constructor.
     */
    public Point2DFactory() {
        LOGGER.debug("Point2DFactory created.");
    }

    /**
     * Factory method for creating {@code Point2D}.
     *
     * @param inputData is string of information for creating object. It's not
     *                  parsed and validated, therefore these operations
     *                  executes in method {@code createFigure}
     * @return geometric figure in a square ({@code Point})
     * @throws WrongArgumentException the wrong argument exception
     */
    @Override
    public Geometry2D createFigure(final String inputData)
            throws WrongArgumentException {
        double[] coordinates;
        DoubleArrayParser doubleArrayParser = new DoubleArrayParser();
        coordinates = doubleArrayParser.parse(inputData);
        Point2DFactory point2DFactory = new Point2DFactory();
        return point2DFactory.createFigure(coordinates);
    }

    /**
     * Factory method for creating {@code Point2D}.
     *
     * @param coordinates the coordinates of {@code Point2D}
     * @return geometric figure in a square ({@code Point})
     * @throws WrongArgumentException the wrong argument exception
     */
    @Override
    public Geometry2D createFigure(final double... coordinates)
            throws WrongArgumentException {
        Point2DValidator point2DValidator = new Point2DValidator();
        if (coordinates == null || !point2DValidator.isValid(coordinates)) {
            throw new WrongArgumentException("Invalid input data: "
                    + Arrays.toString(coordinates));
        }
        return new Point2D(
                coordinates[Point2DValidator.INDEX_COORD_X],
                coordinates[Point2DValidator.INDEX_COORD_Y]
        );
    }
}
