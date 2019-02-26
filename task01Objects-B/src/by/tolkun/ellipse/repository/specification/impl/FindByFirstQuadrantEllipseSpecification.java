package by.tolkun.ellipse.repository.specification.impl;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.FindEllipseSpecification;

import java.util.Map;

/**
 * Specification to find ellipse from fisrt quadrant.
 *
 * @author Kirill Tolkun
 */
public class FindByFirstQuadrantEllipseSpecification
        implements FindEllipseSpecification {

    /**
     * Specify {@code Ellipse} finder from first quadrant.
     *
     * @param entry to check appropriation to criteria
     * @return {@code true} if {@code Ellipse} belongs first quadrant otherwise
     * {@code false}.
     */
    @Override
    public boolean specified(final Map.Entry<Ellipse, EllipseRecorder> entry) {
        Ellipse ellipse = entry.getKey();
        return ellipse.getBeginPoint().getX() > 0
                && ellipse.getEndPoint().getY() > 0;
    }
}
