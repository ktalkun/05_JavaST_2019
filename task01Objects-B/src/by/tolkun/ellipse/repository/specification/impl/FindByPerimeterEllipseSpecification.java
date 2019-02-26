package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.FindEllipseSpecification;

import java.util.Map;

/**
 * Specification to find ellipse with the same perimeter.
 *
 * @author Kirill Tolkun
 */
public class FindByPerimeterEllipseSpecification
        implements FindEllipseSpecification {

    /**
     * Perimeter of {Ellipse} to find.
     */
    private double perimeter;

    /**
     * Constructor with parameters.
     *
     * @param inputPerimeter the perimeter of {@code Ellipse} for finding
     */
    public FindByPerimeterEllipseSpecification(final double inputPerimeter) {
        perimeter = inputPerimeter;
    }

    /**
     * Specify finder by perimeter of {@code Ellipse}.
     *
     * @param entry to check appropriation to criteria
     * @return {@code true} if {@code Ellipse} perimeter equal specification
     * perimeter otherwise {@code false}
     */
    @Override
    public boolean specified(final Map.Entry<Ellipse, EllipseRecorder> entry) {
        return entry.getValue().getPerimeter() == perimeter;
    }
}
