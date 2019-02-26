package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.FindEllipseSpecification;

import java.util.Map;

/**
 * Specification to find ellipse with the same id.
 *
 * @author Kirill Tolkun
 */
public class FindByIdEllipseSpecification implements FindEllipseSpecification {

    /**
     * Id of {Ellipse} to find.
     */
    private int id;

    /**
     * Constructor with parameters.
     *
     * @param inputId the id of {@code Ellipse} for finding
     */
    public FindByIdEllipseSpecification(final int inputId) {
        id = inputId;
    }

    /**
     * Specify finder by id of {@code Ellipse}.
     *
     * @param entry to check appropriation to criteria
     * @return {@code true} if {@code Ellipse} id equal specification id
     * otherwise {@code false}
     */
    @Override
    public boolean specified(final Map.Entry<Ellipse, EllipseRecorder> entry) {
        return entry.getKey().getId() == id;
    }
}
