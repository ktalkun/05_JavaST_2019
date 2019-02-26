package test.ellipse.factory.geometry;

import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.geometry.Point2D;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.factory.geometry.EllipseFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code EllipseFactory}.
 *
 * @author Kirill Tolkun
 */
public class EllipseFactoryTest {

    /**
     * Object of {@code EllipseFactory} for testing methods.
     */
    private EllipseFactory ellipseFactory = new EllipseFactory();

    /**
     * Provide positive data for testing {@code EllipseFactory::createFigure}
     * for array from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataArray")
    public Object[][] createPositiveDataArray() {
        return new Object[][]{
                {new double[]{-1, 1, 1, -1}, new Ellipse(
                        new Point2D(-1, 1),
                        new Point2D(1, -1)
                )},
                {new double[]{-1.0, 1.0, 1.0, -1.0}, new Ellipse(
                        new Point2D(-1.0, 1.0),
                        new Point2D(1.0, -1.0)
                )},
                {new double[]{.0, 0., .1, -1.}, new Ellipse(
                        new Point2D(.0, 0.),
                        new Point2D(.1, -1.)
                )}
        };
    }

    /**
     * Testing positive script in method {@code EllipseFactory::createFigure}
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
                                              final Ellipse expected)
            throws WrongArgumentException {
        Ellipse actual = (Ellipse) ellipseFactory.createFigure(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code EllipseFactory::createFigure}
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
     * Testing negative script in method {@code EllipseFactory::createFigure}
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
        ellipseFactory.createFigure(coordinates);
    }

    /**
     * Provide positive data for testing {@code EllipseFactory::createFigure}
     * for String from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataString")
    public Object[][] createPositiveDataString() {
        return new Object[][]{
                {"-1, 1, 1, -1", new Ellipse(
                        new Point2D(-1, 1),
                        new Point2D(1, -1)
                )},
                {"-1.0, 1.0, 1.0, -1.0", new Ellipse(
                        new Point2D(-1.0, 1.0),
                        new Point2D(1.0, -1.0)
                )},
                {".0, 0., .1, -1.", new Ellipse(
                        new Point2D(.0, 0.),
                        new Point2D(.1, -1.)
                )}
        };
    }

    /**
     * Testing positive script in method {@code EllipseFactory::createFigure}
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
                                               final Ellipse expected)
            throws WrongArgumentException {
        Ellipse actual = (Ellipse) ellipseFactory.createFigure(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code EllipseFactory::createFigure}
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
     * Testing negative script in method {@code EllipseFactory::createFigure}
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
        ellipseFactory.createFigure(coordinates);
    }
}
