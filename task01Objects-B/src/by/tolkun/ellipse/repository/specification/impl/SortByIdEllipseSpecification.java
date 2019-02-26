package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.SortEllipseSpecification;

import java.util.Comparator;
import java.util.Map;

/**
 * Specification to sort ellipses by id.
 *
 * @author Kirill Tolkun
 */
public class SortByIdEllipseSpecification implements SortEllipseSpecification {

    /**
     * Specify comparator by id.
     *
     * @return {@code Comparator<Map.Entry<Ellipse, EllipseRecorder>>} to sort
     * by id
     */
    @Override
    public Comparator<Map.Entry<Ellipse, EllipseRecorder>> specified() {
        return Comparator.comparingInt(entry -> entry.getKey().getId());
    }
}
