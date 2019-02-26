package by.tolkun.ellipse.entity.geometry;

import by.tolkun.ellipse.entity.AbstractEntity;
import by.tolkun.ellipse.exception.WrongArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Class geometric figure Ellipse with fields <b>beginPoint</b>
 * and <b>endPoint</b>.
 *
 * @author Kirill Tolkun
 */
public class Ellipse extends AbstractEntity implements Geometry2D {

    /**
     * <b>beginPoint</b> is upper left point of the rectangle described near
     * the ellipse.
     */
    private Point2D beginPoint;

    /**
     * <b>endPoint</b> is lower right point of the rectangle described near
     * the ellipse.
     */
    private Point2D endPoint;

    /**
     * Logger of class {@code Ellipse}.
     */
    private static final Logger LOGGER = LogManager.getLogger(Ellipse.class);

    /**
     * Default constructor with parameters.
     *
     * @param inputBeginPoint upper left point of the rectangle described near
     *                        ellipse
     * @param inputEndPoint   lower right point of the rectangle described near
     *                        ellipse
     */
    public Ellipse(final Point2D inputBeginPoint, final Point2D inputEndPoint) {
        this.beginPoint = inputBeginPoint;
        this.endPoint = inputEndPoint;
        LOGGER.debug("Ellipse created.");
    }

    /**
     * Get begin coordinate of the rectangle described near the ellipse.
     *
     * @return upper left point of the rectangle described near the ellipse.
     */
    public Point2D getBeginPoint() {
        return beginPoint;
    }

    /**
     * Set new begin point of the rectangle described near ellipse.
     *
     * @param inputBeginPoint is upper right point of the rectangle described
     *                        ellipse
     * @throws WrongArgumentException if begin point and end point of the
     *                                rectangle described ellipse lie on the
     *                                line parallel by axis Ox or Oy. If begin
     *                                point situated not upper left or end
     *                                point situated not lower right.
     */
    public void setBeginPoint(final Point2D inputBeginPoint)
            throws WrongArgumentException {
        if (inputBeginPoint.getX() >= endPoint.getX()
                || inputBeginPoint.getY() <= endPoint.getY()) {
            throw new WrongArgumentException("Points not create rectangle.");
        }
        this.beginPoint = inputBeginPoint;
        LOGGER.debug("Begin point of ellipse changed.");
    }

    /**
     * Get end point of the rectangle  described near the ellipse.
     *
     * @return lower right point of the rectangle described near the ellipse.
     */
    public Point2D getEndPoint() {
        return endPoint;
    }

    /**
     * Set new end point of the rectangle described ellipse.
     *
     * @param inputEndPoint is lower left point of the rectangle described
     *                      ellipse
     * @throws WrongArgumentException if begin point and end point of the
     *                                rectangle described ellipse lie on the
     *                                line parallel by axis Ox or Oy. If begin
     *                                point situated not upper left or end
     *                                point situated not lower right.
     */
    public void setEndPoint(final Point2D inputEndPoint)
            throws WrongArgumentException {
        if (beginPoint.getX() >= inputEndPoint.getX()
                || beginPoint.getY() <= inputEndPoint.getY()) {
            throw new WrongArgumentException("Points not create rectangle.");
        }
        this.endPoint = inputEndPoint;
        LOGGER.debug("End point of ellipse changed.");
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Ellipse} object that
     * contains the same points as this object.
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
        Ellipse ellipse = (Ellipse) o;
        return Objects.equals(beginPoint, ellipse.beginPoint)
                && Objects.equals(endPoint, ellipse.endPoint);
    }

    /**
     * Returns a hash code for a {@code Ellipse}.
     *
     * @return a hash code value for a {@code Ellipse}.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getId();
        result = prime * result + beginPoint.hashCode();
        result = prime * result + endPoint.hashCode();
        return result;
    }

    /**
     * Returns a {@code String} object representing this
     * {@code Ellipse}'s coordinates.
     *
     * @return a string representation of the point's coordinate of this
     * object
     */
    @Override
    public String toString() {
        return "Ellipse{"
                + "id=" + getId()
                + ", beginPoint=" + beginPoint
                + ", endPoint=" + endPoint
                + '}';
    }
}
