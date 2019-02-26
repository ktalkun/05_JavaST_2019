package by.tolkun.ellipse.entity.recorder;

import by.tolkun.ellipse.action.EllipseAction;
import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.geometry.ObservableEllipse;
import by.tolkun.ellipse.observer.ObserverFigure;
import by.tolkun.ellipse.observer.event.Geometric2DEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for storing information about of appropriate {@code Ellipse}.
 *
 * @author Kirill Tolkun
 */
public class EllipseRecorder implements ObserverFigure {

    /**
     * ID of appropriate {@code Ellipse}.
     */
    private int id;

    /**
     * Perimeter of appropriate {@code Ellipse}.
     */
    private double perimeter;

    /**
     * Square of appropriate {@code Ellipse}.
     */
    private double square;

    /**
     * Logger of class {@code EllipseRecorder}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(EllipseRecorder.class);

    /**
     * Default constructor.
     */
    public EllipseRecorder() {
        LOGGER.debug("EllipseRecorder created.");
    }

    /**
     * Get ID of {@code Ellipse} corresponding to this object.
     *
     * @return id of {@code Ellipse}
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID of {@code Ellipse} corresponding to this object.
     *
     * @param inputId of {@code Ellipse}
     */
    private void setId(final int inputId) {
        this.id = inputId;
    }

    /**
     * Get perimeter of {@code Ellipse} corresponding to this object.
     *
     * @return perimeter of {@code Ellipse}
     */
    public double getPerimeter() {
        return perimeter;
    }

    /**
     * Set perimeter of {@code Ellipse} corresponding to this object.
     *
     * @param inputPerimeter of {@code Ellipse}
     */
    private void setPerimeter(final double inputPerimeter) {
        this.perimeter = inputPerimeter;
    }

    /**
     * Get square of {@code Ellipse} corresponding to this object.
     *
     * @return square of {@code Ellipse}
     */
    public double getSquare() {
        return square;
    }

    /**
     * Set square of {@code Ellipse} corresponding to this object.
     *
     * @param inputSquare of {@code Ellipse}
     */
    private void setSquare(final double inputSquare) {
        this.square = inputSquare;
    }

    /**
     * Copy values of {@code Ellipse} all fields to this
     * {@code EllipseRecorder}.
     *
     * @param ellipse corresponding to this object
     */
    public void setEllipse(final Ellipse ellipse) {
        setId(ellipse.getId());
        EllipseAction ellipseAction = new EllipseAction();
        setPerimeter(ellipseAction.calcPerimeter(ellipse));
        setSquare(ellipseAction.calcSquare(ellipse));
    }

    /**
     * Update {@code perimeter} and {@code square} in {@code EllipseRecorder}.
     *
     * @param event contained object of {@code ObserverFigure}
     */
    @Override
    public void update(final Geometric2DEvent event) {
        LOGGER.debug("Notification got.");
        setEllipse((ObservableEllipse) event.getSource());
        LOGGER.debug("Data updated.");
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code EllipseRecorder} object that
     * contains the same fields values as this object.
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
        EllipseRecorder that = (EllipseRecorder) o;
        return id == that.id
                && Double.compare(that.perimeter, perimeter) == 0
                && Double.compare(that.square, square) == 0;
    }

    /**
     * Returns a hash code for a {@code EllipseRecorder}.
     *
     * @return a hash code value for a {@code EllipseRecorder}.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        final int halfShift = 32;
        int result = 1;
        long longBits;
        longBits = Double.doubleToLongBits(perimeter);
        result = prime * result + id;
        result = prime * result + (int) (longBits - (longBits >>> halfShift));
        longBits = Double.doubleToLongBits(square);
        result = prime * result + (int) (longBits - (longBits >>> halfShift));
        return result;
    }

    /**
     * Returns a {@code String} object representing this
     * {@code EllipseRecorder}'s coordinates.
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "EllipseRecorder{"
                + "id=" + id
                + ", perimeter=" + perimeter
                + ", square=" + square
                + '}';
    }
}
