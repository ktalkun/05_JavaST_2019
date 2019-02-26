package by.tolkun.ellipse.entity.geometry;

import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.observer.ObservableFigure;
import by.tolkun.ellipse.observer.ObserverFigure;
import by.tolkun.ellipse.observer.event.Geometric2DEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Observable class for notifying its observers about updates.
 *
 * @author Kirill Tolkun
 */
public class ObservableEllipse extends Ellipse implements ObservableFigure {

    /**
     * List of observers, which follow by updates of this object.
     */
    private List<ObserverFigure> observers;

    /**
     * Logger of class {@code ObservableEllipse}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ObservableEllipse.class);

    /**
     * Default constructor with parameters.
     *
     * @param inputBeginPoint upper left point of the rectangle described near
     *                        ellipse
     * @param inputEndPoint   lower right point of the rectangle described near
     */
    public ObservableEllipse(final Point2D inputBeginPoint,
                             final Point2D inputEndPoint) {
        super(inputBeginPoint, inputEndPoint);
        observers = new LinkedList<>();
        LOGGER.debug("ObservableEllipse created.");
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
    @Override
    public void setBeginPoint(final Point2D inputBeginPoint)
            throws WrongArgumentException {
        if (inputBeginPoint.getX() >= getEndPoint().getX()
                || inputBeginPoint.getY() <= getEndPoint().getY()) {
            throw new WrongArgumentException("New beginPoint is invalid");
        }
        super.setBeginPoint(inputBeginPoint);
        notifyObservers(new Geometric2DEvent(this));
    }

    /**
     * Set new end point of the rectangle described near ellipse.
     *
     * @param inputEndPoint is lower left point of the rectangle described
     *                      ellipse
     * @throws WrongArgumentException if begin point and end point of the
     *                                rectangle described ellipse lie on the
     *                                line parallel by axis Ox or Oy. If begin
     *                                point situated not upper left or end
     *                                point situated not lower right.
     */
    @Override
    public void setEndPoint(final Point2D inputEndPoint)
            throws WrongArgumentException {
        if (inputEndPoint.getX() <= getBeginPoint().getX()
                || inputEndPoint.getY() >= getBeginPoint().getY()) {
            throw new WrongArgumentException("New endPoint is invalid");
        }
        super.setEndPoint(inputEndPoint);
        notifyObservers(new Geometric2DEvent(this));
    }

    /**
     * Add observer to this observable object.
     *
     * @param observerFigure follow by updates of observable
     */
    @Override
    public void attachObserver(final ObserverFigure observerFigure) {
        observers.add(observerFigure);
    }

    /**
     * Remove observer from this observable object.
     *
     * @param observerFigure follow by updates of observable
     */
    @Override
    public void detachObserver(final ObserverFigure observerFigure) {
        observers.remove(observerFigure);
    }

    /**
     * Notify observer about updates of observable object.
     */
    @Override
    public void notifyObservers(final Geometric2DEvent event) {
        LOGGER.debug("Notification send to observers.");
        observers.forEach(observer -> observer.update(event));
    }
}
