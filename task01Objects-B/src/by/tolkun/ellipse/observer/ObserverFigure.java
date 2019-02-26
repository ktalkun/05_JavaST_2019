package by.tolkun.ellipse.observer;

import by.tolkun.ellipse.observer.event.Geometric2DEvent;

/**
 * Common interface for figure observers.
 *
 * @author Kirill Tolkun
 */
public interface ObserverFigure {

    /**
     * Update information in object {@code ObserverFigure}.
     *
     * @param event contained object of {@code ObserverFigure}

     */
    void update(Geometric2DEvent event);
}
