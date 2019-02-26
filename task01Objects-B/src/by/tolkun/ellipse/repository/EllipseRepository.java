package by.tolkun.ellipse.repository;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.repository.specification.EllipseSpecification;
import by.tolkun.ellipse.repository.specification.FindEllipseSpecification;
import by.tolkun.ellipse.repository.specification.SortEllipseSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class for storing collection of {@code Ellipse} and {@code Data}.
 *
 * @author Kirill Tolkun
 */
public final class EllipseRepository {

    /**
     * Collection for storing objects
     * {@code ObservableEllipse}, {@code EllipseRecorder}.
     */
    private Map<Ellipse, EllipseRecorder> repo = new HashMap<>();

    /**
     * Default Constructor.
     */
    private EllipseRepository() {
    }

    /**
     * Inner private static class for initialization {@code INSTANCE} and
     * collection of objects.
     */
    private static class SingletonHolder {

        /**
         * Instance of {@code EllipseRepository}.
         */
        private static final EllipseRepository INSTANCE
                = new EllipseRepository();
    }

    /**
     * Get instance of {@code EllipseRepository}.
     *
     * @return instance of {@code EllipseRepository}
     */
    public static EllipseRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Add pair ({@code ObservableEllipse}, {@code EllipseRecorder}).
     *
     * @param ellipse         the {@code ObservableEllipse} to add
     * @param ellipseRecorder {@code EllipseRecorder} corresponding
     *                        {@code ellipse}
     * @return added {@code ObservableEllipse}
     */
    public EllipseRecorder add(final Ellipse ellipse,
                               final EllipseRecorder ellipseRecorder) {
        return repo.put(ellipse, ellipseRecorder);
    }

    /**
     * Remove pair ({@code ObservableEllipse}, {@code EllipseRecorder}).
     *
     * @param ellipse the {@code ObservableEllipse} to remove
     * @return remove {@code ObservableEllipse}
     */
    public EllipseRecorder remove(final Ellipse ellipse) {
        return repo.remove(ellipse);
    }

    /**
     * Make query to repository.
     *
     * @param specification the criteria for query
     * @return {@code Lisr<Ellipse>} found or after sort by specification
     */
    public List<Ellipse> query(final EllipseSpecification specification) {
        List<Ellipse> ellipses = new ArrayList<>();

        if (specification instanceof FindEllipseSpecification) {
            ellipses = repo
                    .entrySet()
                    .stream()
                    .filter(((FindEllipseSpecification) specification)
                            ::specified)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

        } else if (specification instanceof SortEllipseSpecification) {
            ellipses = repo
                    .entrySet()
                    .stream()
                    .sorted(((SortEllipseSpecification) specification)
                            .specified())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
        return ellipses;
    }
}
