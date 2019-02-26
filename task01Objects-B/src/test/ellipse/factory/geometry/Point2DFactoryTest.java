package test.ellipse.factory.geometry;

import by.tolkun.ellipse.entity.geometry.Point2D;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.factory.geometry.Point2DFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code Point2DFactory}.
 *
 * @author Kirill Tolkun
 */
public class Point2DFactoryTest {

    /**
     * Object of {@code Point2DFactory} for testing methods.
     */
    private Point2DFactory point2DFactory = new Point2DFactory();

    /**
     * Provide positive data for testing {@code Point2DFactory::createFigure}
     * for array from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataArray")
    public Object[][] createPositiveDataArray() {
        return new Object[][]{
                {new double[]{0, 0}, new Point2D(0, 0)},
                {new double[]{1, 1}, new Point2D(1, 1)},
                {new double[]{-1, -1}, new Point2D(-1, -1)},
                {new double[]{1, -1}, new Point2D(1, -1)},
                {new double[]{-1, 1}, new Point2D(-1, 1)}
        };
    }

    /**
     * Testing positive script in method {@code Point2DFactory::createFigure}
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
                                              final Point2D expected)
            throws WrongArgumentException {
        Point2D actual = (Point2D) point2DFactory.createFigure(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code Point2DFactory::createFigure}
     * for array from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeDataArray")
    public Object[][] createNegativeDataArray() {
        return new Object[][]{
                {new double[]{0, 0, 0}},
                {new double[]{1, 1, 1}},
                {new double[]{-1, -1, -1}},
                {new double[]{1, -1, 1}},
                {new double[]{-1, 1, -1}},
                {new double[]{1}},
                {new double[]{0}},
                {new double[]{-1}}
        };
    }

    /**
     * Testing negative script in method {@code Point2DFactory::createFigure}
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
        point2DFactory.createFigure(coordinates);
    }

    /**
     * Provide positive data for testing {@code Point2DFactory::createFigure}
     * for String from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataString")
    public Object[][] createPositiveDataString() {
        return new Object[][]{
                {"0 0", new Point2D(0, 0)},
                {"1 1", new Point2D(1, 1)},
                {"-1 -1", new Point2D(-1, -1)},
                {"1 -1", new Point2D(1, -1)},
                {"-1 1", new Point2D(-1, 1)}
        };
    }

    /**
     * Testing positive script in method {@code Point2DFactory::createFigure}
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
                                               final Point2D expected)
            throws WrongArgumentException {
        Point2D actual = (Point2D) point2DFactory.createFigure(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code Point2DFactory::createFigure}
     * for String from coordinates.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeDataString")
    public Object[][] createNegativeDataString() {
        return new Object[][]{
                {"0 0 0"},
                {"1 1 1"},
                {"-1 -1 -1"},
                {"1 -1 1"},
                {"-1 1 -1"},
                {"-1"},
                {"0"},
                {"1"}
        };
    }

    /**
     * Testing negative script in method {@code Point2DFactory::createFigure}
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
        point2DFactory.createFigure(coordinates);
    }
}
