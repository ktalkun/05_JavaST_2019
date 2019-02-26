package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.FindEllipseSpecification;

import java.util.Map;

/**
 * Specification to find ellipse with the same square.
 *
 * @author Kirill Tolkun
 */
public class FindBySquareSpecification implements FindEllipseSpecification {

    /**
     * Square of {Ellipse} to find.
     */
    private double square;

    /**
     * Constructor with parameters.
     *
     * @param inputSquare the square of {@code Ellipse} for finding
     */
    public FindBySquareSpecification(final double inputSquare) {
        square = inputSquare;
    }

    /**
     * Specify finder by square of {@code Ellipse}.
     *
     * @param entry to check appropriation to criteria
     * @return {@code true} if {@code Ellipse} square equal specification
     * square otherwise {@code false}
     */
    @Override
    public boolean specified(final Map.Entry<Ellipse, EllipseRecorder> entry) {
        return entry.getValue().getSquare() == square;
    }
}
