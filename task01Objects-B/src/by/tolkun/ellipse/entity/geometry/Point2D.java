package by.tolkun.ellipse.entity.geometry;


import by.tolkun.ellipse.entity.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class of geometric object {@code Point2D} with fields
 * <b>x</b> and <b>y</b>.
 *
 * @author Kirill Tolkun
 */
public class Point2D extends AbstractEntity implements Geometry2D {

    /**
     * <b>x</b> coordinate.
     */
    private double x;

    /**
     * <b>y</b> coordinate.
     */
    private double y;

    /**
     * Logger of class {@code Point2D}.
     */
    private static final Logger LOGGER = LogManager.getLogger(Point2D.class);

    /**
     * Constructor with parameters.
     *
     * @param inputX is first coordinate
     * @param inputY is second coordinate
     */
    public Point2D(final double inputX, final double inputY) {
        this.x = inputX;
        this.y = inputY;
        LOGGER.debug("Point created: x = {}, y = {}.", inputX, inputY);
    }

    /**
     * Get <b>x</b> coordinate.
     *
     * @return <b>x</b> coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Set <b>x</b> coordinate.
     *
     * @param inputX coordinate of {@code Point2D} in square
     */
    public void setX(final double inputX) {
        this.x = inputX;
        LOGGER.debug("Coordinate x of point changed.");
    }

    /**
     * Get <b>y</b> coordinate.
     *
     * @return <b>y</b>  coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Set <b>y</b> coordinate.
     *
     * @param inputY coordinate of {@code Point2D} in square
     */
    public void setY(final double inputY) {
        this.y = inputY;
        LOGGER.debug("Coordinate y of point changed.");
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Point2D} object that
     * contains the same {@code double} coordinates as this object.
     *
     * @param o the object to compare with.
     * @return {@code true} if the objects are the same;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point2D point2D = (Point2D) o;
        return Double.compare(point2D.x, x) == 0
                && Double.compare(point2D.y, y) == 0;
    }

    /**
     * Returns a hash code for a {@code Point2D}.
     *
     * @return a hash code value for a {@code Point2D}.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        final int halfShift = 32;
        int result = 1;
        long longBits;
        longBits = Double.doubleToLongBits(x);
        result = prime * result + getId();
        result = prime * result + (int) (longBits - (longBits >>> halfShift));
        longBits = Double.doubleToLongBits(y);
        result = prime * result + (int) (longBits - (longBits >>> halfShift));
        return result;
    }

    /**
     * Returns a {@code String} object representing this
     * {@code Point2D}'s coordinates.
     *
     * @return a string representation of the point's coordinate of this
     * object
     */
    @Override
    public String toString() {
        return "Point2D{"
                + "id=" + getId()
                + ", x=" + x
                + ", y=" + y
                + '}';
    }
}
