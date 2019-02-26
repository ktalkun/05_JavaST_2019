package by.tolkun.ellipse.repository.specification;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;

import java.util.Comparator;
import java.util.Map;

/**
 * Interface to realize sort query.
 *
 * @author Kirill Tolkun
 */
public interface SortEllipseSpecification extends EllipseSpecification {

    /**
     * Specify comparator.
     *
     * @return {@code Comparator<Map.Entry<Ellipse, EllipseRecorder>>}
     */
    Comparator<Map.Entry<Ellipse, EllipseRecorder>> specified();
}
