package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.SortEllipseSpecification;

import java.util.Comparator;
import java.util.Map;

/**
 * Specification to sort ellipses by perimeter.
 *
 * @author Kirill Tolkun
 */
public class SortByPerimeterEllipseSpecification
        implements SortEllipseSpecification {

    /**
     * Specify comparator by square.
     *
     * @return {@code Comparator<Map.Entry<Ellipse, EllipseRecorder>>} to sort
     * by perimeter
     */
    @Override
    public Comparator<Map.Entry<Ellipse, EllipseRecorder>> specified() {
        return Comparator.comparing(entry ->
                entry.getValue().getPerimeter()
        );
    }
}
