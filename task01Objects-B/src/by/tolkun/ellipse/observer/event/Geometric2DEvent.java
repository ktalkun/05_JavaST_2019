package by.tolkun.ellipse.observer.event;

import by.tolkun.ellipse.entity.geometry.Geometry2D;
import by.tolkun.ellipse.exception.WrongArgumentException;

/**
 * The class from which all event state objects shall be derived.
 *
 * @author Kirill Tolkun
 */
public class Geometric2DEvent {

    /**
     * The object on which the Event initially occurred.
     */
    private Geometry2D source;

    /**
     * Constructor with parameters.
     *
     * @param inputSource The object on which the Event initially occurred.
     * @throws WrongArgumentException if {@code inputSource} is null
     */
    public Geometric2DEvent(final Geometry2D inputSource)
            throws WrongArgumentException {
        if (inputSource == null) {
            throw new WrongArgumentException("Passed null object");
        }
        source = inputSource;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    public Geometry2D getSource() {
        return source;
    }
}
