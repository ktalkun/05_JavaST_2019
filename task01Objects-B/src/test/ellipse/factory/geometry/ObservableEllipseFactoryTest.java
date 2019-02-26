package test.ellipse.factory.geometry;

import by.tolkun.ellipse.entity.geometry.ObservableEllipse;
import by.tolkun.ellipse.entity.geometry.Point2D;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.factory.geometry.ObservableEllipseFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code ObservableEllipseFactory}.
 *
 * @author Kirill Tolkun
 */
public class ObservableEllipseFactoryTest {

    /**
     * Object of {@code ObservableEllipseFactory} for testing methods.
     */
    private ObservableEllipseFactory observableEllipseFactory
            = new ObservableEllipseFactory();

    /**
     * Provide positive data for testing
     * {@code ObservableEllipseFactory::createFigure}
     * for array from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataArray")
    public Object[][] createPositiveDataArray() {
        return new Object[][]{
                {new double[]{-1, 1, 1, -1}, new ObservableEllipse(
                        new Point2D(-1, 1),
                        new Point2D(1, -1)
                )},
                {new double[]{-1.0, 1.0, 1.0, -1.0}, new ObservableEllipse(
                        new Point2D(-1.0, 1.0),
                        new Point2D(1.0, -1.0)
                )},
                {new double[]{.0, 0., .1, -1.}, new ObservableEllipse(
                        new Point2D(.0, 0.),
                        new Point2D(.1, -1.)
                )}
        };
    }

    /**
     * Testing positive script in method
     * {@code ObservableEllipseFactory::createFigure}
     * for array from coordinates.
     *
     * @param coordinates of points
     * @param expected    result
     * @throws WrongArgumentException if number of coordinates less or more
     *                                than required
     */
    @Test(description = "positive script",
            dataProvider = "positiveDataArray")
    public void testPositiveArrayCreateFigure(final double[] coordinates,
                                              final ObservableEllipse expected)
            throws WrongArgumentException {
        ObservableEllipse actual = (ObservableEllipse) observableEllipseFactory
                .createFigure(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing
     * {@code ObservableEllipseFactory::createFigure}
     * for array from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeDataArray")
    public Object[][] createNegativeDataArray() {
        return new Object[][]{
                {new double[]{}},
                {new double[]{0.1}},
                {new double[]{10., 12}},
                {new double[]{10., 0, .18}},
                {new double[]{2, 2, 2, 2}},
                {new double[]{2, 4, 2, 3}},
                {new double[]{3, 2, 4, 2}},
                {new double[]{3, 2, 4, 2, 0}}
        };
    }

    /**
     * Testing negative script in method
     * {@code ObservableEllipseFactory::createFigure}
     * for array from coordinates.
     *
     * @param coordinates of points
     * @throws WrongArgumentException if number of coordinates less or more
     *                                than required
     */
    @Test(description = "negative script",
            dataProvider = "negativeDataArray",
            expectedExceptions = WrongArgumentException.class)
    public void testNegativeArrayCreateFigure(final double[] coordinates)
            throws WrongArgumentException {
        observableEllipseFactory.createFigure(coordinates);
    }

    /**
     * Provide positive data for testing
     * {@code ObservableEllipseFactory::createFigure}
     * for String from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataString")
    public Object[][] createPositiveDataString() {
        return new Object[][]{
                {"-1, 1, 1, -1", new ObservableEllipse(
                        new Point2D(-1, 1),
                        new Point2D(1, -1)
                )},
                {"-1.0, 1.0, 1.0, -1.0", new ObservableEllipse(
                        new Point2D(-1.0, 1.0),
                        new Point2D(1.0, -1.0)
                )},
                {".0, 0., .1, -1.", new ObservableEllipse(
                        new Point2D(.0, 0.),
                        new Point2D(.1, -1.)
                )}
        };
    }

    /**
     * Testing positive script in method
     * {@code ObservableEllipseFactory::createFigure}
     * for String from coordinates.
     *
     * @param coordinates of points
     * @param expected    result
     * @throws WrongArgumentException if number of coordinates less or more
     *                                than required
     */
    @Test(description = "positive script",
            dataProvider = "positiveDataString")
    public void testPositiveStringCreateFigure(final String coordinates,
                                               final ObservableEllipse expected)
            throws WrongArgumentException {
        ObservableEllipse actual = (ObservableEllipse) observableEllipseFactory
                .createFigure(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing
     * {@code ObservableEllipseFactory::createFigure}
     * for String from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeDataString")
    public Object[][] createNegativeDataString() {
        return new Object[][]{
                {""},
                {"0.1"},
                {"10., 12"},
                {"10., 0, .18"},
                {"2, 2, 2, 2"},
                {"2, 4, 2, 3"},
                {"3, 2, 4, 2"},
                {"3, 2, 4, 2, 0"}
        };
    }

    /**
     * Testing negative script in method
     * {@code ObservableEllipseFactory::createFigure}
     * for String from coordinates.
     *
     * @param coordinates of points
     * @throws WrongArgumentException if number of coordinates less or more
     *                                than required
     */
    @Test(description = "negative script",
            dataProvider = "negativeDataString",
            expectedExceptions = WrongArgumentException.class)
    public void testNegativeStringCreateFigure(final String coordinates)
            throws WrongArgumentException {
        observableEllipseFactory.createFigure(coordinates);
    }
}
