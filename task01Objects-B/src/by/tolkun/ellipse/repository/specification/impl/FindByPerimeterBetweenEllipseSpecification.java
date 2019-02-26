package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.FindEllipseSpecification;

import java.util.Map;

/**
 * Specification to find ellipse the perimeter in diapason.
 *
 * @author Kirill Tolkun
 */
public class FindByPerimeterBetweenEllipseSpecification
        implements FindEllipseSpecification {

    /**
     * Begin of diapason.
     */
    private double from;

    /**
     * End of diapason.
     */
    private double to;

    /**
     * Constructor with parameters.
     *
     * @param inputFrom the being of diapason
     * @param inputTo   the end of diapason
     */
    public FindByPerimeterBetweenEllipseSpecification(final double inputFrom,
                                                      final double inputTo) {
        from = inputFrom;
        to = inputTo;
    }

    /**
     * Specify finder by {@code Ellipse} perimeter diapason.
     *
     * @param entry to check appropriation to criteria
     * @return {@code true} if {@code Ellipse} perimeter in diapason otherwise
     * {@code false}
     */
    @Override
    public boolean specified(final Map.Entry<Ellipse, EllipseRecorder> entry) {
        EllipseRecorder ellipseRecorder = entry.getValue();
        return ellipseRecorder.getPerimeter() >= from
                && ellipseRecorder.getPerimeter() <= to;
    }
}
