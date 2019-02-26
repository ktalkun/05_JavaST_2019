package by.tolkun.ellipse.action;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for calculating geometric properties of {@code Ellipse}.
 *
 * @author Kirill Tolkun
 */
public class EllipseAction {

    /**
     * Logger for class {@code EllipseAction}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(EllipseAction.class);

    /**
     * Default constructor.
     */
    public EllipseAction() {
        LOGGER.debug("EllipseAction created.");
    }

    /**
     * Check is circle.
     *
     * @param ellipse to check
     * @return {@code true} if {@code Ellipse} is circle otherwise {@code false}
     */
    public boolean isCircle(final Ellipse ellipse) {
        return ellipse.getEndPoint().getX()
                - ellipse.getBeginPoint().getX()
                == ellipse.getBeginPoint().getY()
                - ellipse.getEndPoint().getY();
    }

    /**
     * Check {@code Ellipse} cross axis Ox.
     *
     * @param ellipse to check
     * @return {@code true} if {@code Ellipse} cross axis Ox
     */
    public boolean isCrossOx(final Ellipse ellipse) {
        return Double.compare(ellipse.getBeginPoint().getY(), 0.0) >= 0
                && Double.compare(ellipse.getEndPoint().getY(), 0.0) <= 0;
    }

    /**
     * Check {@code Ellipse} cross axis Oy.
     *
     * @param ellipse to check
     * @return {@code true} if {@code ELlipse} cross axis Oy
     */
    public boolean isCrossOy(final Ellipse ellipse) {
        return Double.compare(ellipse.getBeginPoint().getX(), 0.0) <= 0
                && Double.compare(ellipse.getEndPoint().getX(), 0.0) >= 0;
    }

    /**
     * Calculate perimeter of {@code Ellipse}.
     *
     * @param ellipse for calculating perimeter
     * @return perimeter of {@code Ellipse}
     */
    public double calcPerimeter(final Ellipse ellipse) {
        double perimeter = Math.PI
                * ((ellipse.getEndPoint().getX()
                - ellipse.getBeginPoint().getX()) / 2
                + (ellipse.getBeginPoint().getY()
                - ellipse.getEndPoint().getY()) / 2);
        LOGGER.debug("Perimeter calculated: {}", perimeter);
        return perimeter;
    }

    /**
     * Calculate square of {@code Ellipse}.
     *
     * @param ellipse for calculating square
     * @return square of {@code Ellipse}
     */
    public double calcSquare(final Ellipse ellipse) {
        double square = Math.PI
                * (ellipse.getEndPoint().getX()
                - ellipse.getBeginPoint().getX())
                / 2
                * (ellipse.getBeginPoint().getY()
                - ellipse.getEndPoint().getY())
                / 2;
        LOGGER.debug("Square calculated: {}", square);
        return square;
    }
}
