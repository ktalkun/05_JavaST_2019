package by.tolkun.ellipse.repository.specification;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;

import java.util.Map;

/**
 * Interface to realize find query.
 *
 * @author Kirill Tolkun
 */
public interface FindEllipseSpecification extends EllipseSpecification {

    /**
     * Specify finder.
     *
     * @param entry to check appropriation to criteria
     * @return {@code true} if entry appropriate the specified criteria
     * {@code false} otherwise
     */
    boolean specified(Map.Entry<Ellipse, EllipseRecorder> entry);
}
