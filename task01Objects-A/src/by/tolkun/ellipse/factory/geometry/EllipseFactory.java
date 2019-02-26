package by.tolkun.ellipse.factory.geometry;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.geometry.Geometry2D;
import by.tolkun.ellipse.entity.geometry.Point2D;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.parser.DoubleArrayParser;
import by.tolkun.ellipse.validator.EllipseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Class of factory for producing {@code Ellipse}.
 *
 * @author Kirill Tolkun
 */
public class EllipseFactory extends Geometric2DFactory {

    /**
     * Logger for class {@code EllipseFactory}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(EllipseFactory.class);

    /**
     * Default constructor.
     */
    public EllipseFactory() {
        LOGGER.debug("EllipseFactory created.");
    }

    /**
     * Factory method for creating {@code Ellipse}.
     *
     * @param inputData is string of information for creating object. It's not
     *                  parsed and validated, therefore these operations
     *                  executes in method {@code createFigure}
     * @return geometric figure in a square ({@code Ellipse})
     * @throws WrongArgumentException the wrong argument exception
     */
    @Override
    public Geometry2D createFigure(final String inputData)
            throws WrongArgumentException {
        double[] coordinates;
        DoubleArrayParser doubleArrayParser = new DoubleArrayParser();
        coordinates = doubleArrayParser.parse(inputData);
        return createFigure(coordinates);
    }

    /**
     * Factory method for creating {@code Ellipse}.
     *
     * @param coordinates the points coordinates of the rectangle described
     *                    near {@code Ellipse}
     * @return geometric figure in a square ({@code Ellipse})
     * @throws WrongArgumentException the wrong argument exception
     */
    @Override
    public Geometry2D createFigure(final double... coordinates)
            throws WrongArgumentException {
        EllipseValidator ellipseValidator = new EllipseValidator();
        if (coordinates == null || !ellipseValidator.isValid(coordinates)) {
            throw new WrongArgumentException("Invalid input data: "
                    + Arrays.toString(coordinates));
        }

        Point2DFactory point2DFactory = new Point2DFactory();

        double upLeftX = Math.min(
                coordinates[EllipseValidator.INDEX_UPPER_LEFT_COORD_X],
                coordinates[EllipseValidator.INDEX_LOWER_RIGHT_COORD_X]
        );
        double upLeftY = Math.max(
                coordinates[EllipseValidator.INDEX_UPPER_LEFT_COORD_Y],
                coordinates[EllipseValidator.INDEX_LOWER_RIGHT_COORD_Y]
        );
        Point2D beginPoint = (Point2D) point2DFactory
                .createFigure(upLeftX, upLeftY);

        double lowRightX = Math.max(
                coordinates[EllipseValidator.INDEX_UPPER_LEFT_COORD_X],
                coordinates[EllipseValidator.INDEX_LOWER_RIGHT_COORD_X]
        );
        double lowRightY = Math.min(
                coordinates[EllipseValidator.INDEX_UPPER_LEFT_COORD_Y],
                coordinates[EllipseValidator.INDEX_LOWER_RIGHT_COORD_Y]
        );
        Point2D endPoint = (Point2D) point2DFactory
                .createFigure(lowRightX, lowRightY);

        return new Ellipse(beginPoint, endPoint);
    }
}
