package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.SortEllipseSpecification;

import java.util.Comparator;
import java.util.Map;

/**
 * Specification to sort ellipses by x coordinate of begin point.
 *
 * @author Kirill Tolkun
 */
public class SortByBeginPointXEllipseSpecification
        implements SortEllipseSpecification {

    /**
     * Specify comparator by x coordinate of begin point.
     *
     * @return {@code Comparator<Map.Entry<Ellipse, EllipseRecorder>>} to sort
     * by x coordinate of begin point
     */
    @Override
    public Comparator<Map.Entry<Ellipse, EllipseRecorder>> specified() {
        return Comparator.comparing(entry ->
                entry.getKey().getBeginPoint().getX()
        );
    }
}
