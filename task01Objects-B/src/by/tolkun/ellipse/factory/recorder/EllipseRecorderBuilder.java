package by.tolkun.ellipse.factory.recorder;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.geometry.ObservableEllipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for creating object of {@code EllipseRecorder}.
 *
 * @author Kirill Tolkun
 */
public class EllipseRecorderBuilder {

    /**
     * {@code EllipseRecorder} for building.
     */
    private EllipseRecorder ellipseRecorder;

    /**
     * Logger of class {@code EllipseRecorderBuilder}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(EllipseRecorder.class);

    /**
     * Default constructor.
     */
    public EllipseRecorderBuilder() {
        LOGGER.debug("EllipseRecorderBuilder created.");
        ellipseRecorder = new EllipseRecorder();
    }

    /**
     * Set corresponding {@code Ellipse} to future {@code EllipseRecorder}.
     *
     * @param ellipse corresponding {@code EllipseRecorder}
     */
    public void setEllipse(final Ellipse ellipse) {
        ellipseRecorder.setEllipse(ellipse);
        if (ellipse instanceof ObservableEllipse) {
            ((ObservableEllipse) ellipse).attachObserver(ellipseRecorder);
        }
    }

    /**
     * Build new {@code EllipseRecorder}.
     *
     * @return built {@code EllipseRecorder}
     */
    public EllipseRecorder build() {
        LOGGER.debug("EllipseRecorder built.");
        return ellipseRecorder;
    }
}
