package by.tolkun.ellipse.observer;

import by.tolkun.ellipse.observer.event.Geometric2DEvent;

/**
 * Common interface for observable figures.
 *
 * @author Kirill Tolkun
 */
public interface ObservableFigure {

    /**
     * Add observer to this observable object.
     *
     * @param observerFigure follow by updates of observable
     */
    void attachObserver(ObserverFigure observerFigure);

    /**
     * Remove observer from this observable object.
     *
     * @param observerFigure follow by updates of observable
     */
    void detachObserver(ObserverFigure observerFigure);

    /**
     * Notify observer about updates of observable object.
     *
     * @param event the event for getting information in observer
     */
    void notifyObservers(Geometric2DEvent event);
}
