package test.ellipse.repository;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.geometry.ObservableEllipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.factory.geometry.ObservableEllipseFactory;
import by.tolkun.ellipse.factory.recorder.EllipseRecorderBuilder;
import by.tolkun.ellipse.repository.EllipseRepository;
import by.tolkun.ellipse.repository.specification.impl.FindByFirstQuadrantEllipseSpecification;
import by.tolkun.ellipse.repository.specification.impl.FindByIdEllipseSpecification;
import by.tolkun.ellipse.repository.specification.impl.FindByPerimeterEllipseSpecification;
import by.tolkun.ellipse.repository.specification.impl.FindBySquareSpecification;
import by.tolkun.ellipse.repository.specification.impl.SortByIdEllipseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class {@code EllipseRepository}.
 *
 * @author Kirill Tolkun
 */
public class EllipseRepositoryTest {

    /**
     * {@code List<Ellipse>} for storing created ellipses.
     */
    private List<Ellipse> ellps = new ArrayList<>();

    /**
     * Constantly matrix for initialization {@code EllipseRepository}.
     */
    private final double[][] coordinatesArr = {
            {-11, 3, -10, 2},
            {-10, 1, -7, -1},
            {-6, 2, -5, -2},
            {-3, 9, 1, 8},
            {0, 7, 1, 6},
            {-1, 5, 1, 3},
            {-3, 1, 1, -2},
            {-2, 2, 12, -3},
            {4, 4, 6, -2},
            {0, -4, 2, -6},
            {-4, -5, 0, -8},
            {2, -8, 4, -10},
            {6, -5, 7, -6},
            {7, -6, 9, -8},
            {-3, -10, 1, -12}
    };

    /**
     * Initialize {@code EllipseRepository}.
     *
     * @throws WrongArgumentException the wrong argument exception
     */
    @BeforeClass
    public void initRepository()
            throws WrongArgumentException {
        for (double[] coordinates : coordinatesArr) {
            ObservableEllipseFactory observableEllipseFactory
                    = new ObservableEllipseFactory();
            ObservableEllipse observableEllipse
                    = (ObservableEllipse) observableEllipseFactory
                    .createFigure(coordinates);
            EllipseRecorderBuilder ellipseRecorderBuilder
                    = new EllipseRecorderBuilder();
            ellipseRecorderBuilder.setEllipse(observableEllipse);
            EllipseRecorder ellipseRecorder = ellipseRecorderBuilder.build();
            EllipseRepository
                    .getInstance()
                    .add(observableEllipse, ellipseRecorder);
            ellps.add(observableEllipse);
        }
    }

    /**
     * Test specification {@code FindByIdEllipseSpecification}.
     */
    @Test(description = "findById")
    public void testFindById() {
        List<Ellipse> actual = EllipseRepository
                .getInstance()
                .query(new FindByIdEllipseSpecification(ellps.get(5).getId()));
        List<Ellipse> expected = Arrays.asList(ellps.get(5));
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test specification {@code FindByPerimeterEllipseSpecification}.
     */
    @Test(description = "findByPerimeter")
    public void testFindByPerimeter() {
        List<Ellipse> actual = EllipseRepository
                .getInstance()
                .query(new FindByPerimeterEllipseSpecification(2
                        * Math.PI));
        List<Ellipse> expected = Arrays.asList(
                ellps.get(5),
                ellps.get(9),
                ellps.get(11),
                ellps.get(13)
        );
        Assert.assertEqualsNoOrder(actual.toArray(), expected.toArray());
    }

    /**
     * Test specification {@code FindBySquareSpecification}.
     */
    @Test(description = "findBySquare")
    public void testFindBySquare() {
        List<Ellipse> actual = EllipseRepository
                .getInstance()
                .query(new FindBySquareSpecification(17.5 * Math.PI));
        List<Ellipse> expected = Arrays.asList(ellps.get(7));
        Assert.assertEqualsNoOrder(actual.toArray(), expected.toArray());
    }

    /**
     * Test specification {@code FindByFirstQuadrantEllipseSpecification}.
     */
    @Test(description = "findByFirstQuadrant")
    public void testFindByFirstQuadrant() {
        List<Ellipse> actual = EllipseRepository
                .getInstance()
                .query(new FindByFirstQuadrantEllipseSpecification());
        List<Ellipse> expected = Arrays.asList();
        Assert.assertEqualsNoOrder(actual.toArray(), expected.toArray());
    }

    /**
     * Test specification {@code SortByIdEllipseSpecification}.
     */
    @Test(description = "sortById")
    public void testSortById() {
        List<Ellipse> actual = EllipseRepository
                .getInstance()
                .query(new SortByIdEllipseSpecification());
        List<Ellipse> expected = ellps;
        Assert.assertEquals(actual.toArray(), expected.toArray());
    }
}
