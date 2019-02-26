package test.ellipse.action;

import by.tolkun.ellipse.action.EllipseAction;
import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.geometry.Point2D;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.factory.geometry.EllipseFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code EllipseActionTest}.
 *
 * @author Kirill Tolkun
 */
public class EllipseActionTest {

    /**
     * Object of action for testing methods.
     */
    private EllipseAction ellipseAction = new EllipseAction();

    /**
     * Constant value of error calculation.
     */
    private final double errorCalculation = 1E-14;

    /**
     * Provide positive data for testing {@code EllipseAction::isCircle}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataCircle")
    public Object[][] createPositiveDataCircle() {
        return new Object[][]{
                {new double[]{-1, 1, 1, -1}, true},
                {new double[]{0, .4, .4, 0}, true},
                {new double[]{-6, 0, 0, -6}, true}
        };
    }

    /**
     * Testing positive script in method {@code EllipseAction::isCircle}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     */
    @Test(description = "positive script to check on circle",
            dataProvider = "positiveDataCircle")
    public void testPositiveIsCircle(final double[] coordinates,
                                     final boolean expected) {
        Ellipse ellipse = new Ellipse(
                new Point2D(coordinates[0], coordinates[1]),
                new Point2D(coordinates[2], coordinates[3])
        );
        boolean actual = ellipseAction.isCircle(ellipse);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code EllipseAction::isCircle}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeDataCircle")
    public Object[][] createNegativeDataCircle() {
        return new Object[][]{
                {new double[]{-2, 1, 2, -1}, false},
                {new double[]{1.5, 3.5, 4.5, -3.5}, false},
                {new double[]{-7, 1, -1, 5}, false}
        };
    }

    /**
     * Testing negative script in method {@code EllipseAction::isCircle}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     */
    @Test(description = "negative script to check on circle",
            dataProvider = "negativeDataCircle")
    public void testNegativeIsCircle(final double[] coordinates,
                                     final boolean expected) {
        Ellipse ellipse = new Ellipse(
                new Point2D(coordinates[0], coordinates[1]),
                new Point2D(coordinates[2], coordinates[3])
        );
        boolean actual = ellipseAction.isCircle(ellipse);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide positive data for testing {@code EllipseAction::isCrossOx}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataCrossOx")
    public Object[][] createPositiveDataCrossOx() {
        return new Object[][]{
                {new double[]{-6.5, 4, -2, 0}, true},
                {new double[]{-2, 2, 2, -2}, true},
                {new double[]{2, 0, 6.5, -4}, true}
        };
    }

    /**
     * Testing positive script in method {@code EllipseAction::isCrossOx}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     */
    @Test(description = "positive script to check on crossing axis Ox",
            dataProvider = "positiveDataCrossOx")
    public void testPositiveIsCrossOx(final double[] coordinates,
                                      final boolean expected) {
        Ellipse ellipse = new Ellipse(
                new Point2D(coordinates[0], coordinates[1]),
                new Point2D(coordinates[2], coordinates[3])
        );
        boolean actual = ellipseAction.isCrossOx(ellipse);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code EllipseAction::isCrossOx}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeDataCrossOx")
    public Object[][] createNegativeDataCrossOx() {
        return new Object[][]{
                {new double[]{-1.5, 4, 1.5, 2}, false},
                {new double[]{-4, 1, -2, 5}, false},
        };
    }

    /**
     * Testing negative script in method {@code EllipseAction::isCrossOx}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     */
    @Test(description = "negative script to check on crossing axis Ox",
            dataProvider = "negativeDataCrossOx")
    public void testNegativeIsCrossOx(final double[] coordinates,
                                      final boolean expected) {
        Ellipse ellipse = new Ellipse(
                new Point2D(coordinates[0], coordinates[1]),
                new Point2D(coordinates[2], coordinates[3])
        );
        boolean actual = ellipseAction.isCrossOx(ellipse);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide positive data for testing {@code EllipseAction::isCrossOy}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataCrossOy")
    public Object[][] createPositiveDataCrossOy() {
        return new Object[][]{
                {new double[]{-4, 6, 0, 2}, true},
                {new double[]{-2, 2, 2, -2}, true},
                {new double[]{0, -2, 4, -6}, true}
        };
    }

    /**
     * Testing positive script in method {@code EllipseAction::isCrossOy}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     */
    @Test(description = "positive script to check on crossing axis Oy",
            dataProvider = "positiveDataCrossOy")
    public void testPositiveIsCrossOy(final double[] coordinates,
                                      final boolean expected) {
        Ellipse ellipse = new Ellipse(
                new Point2D(coordinates[0], coordinates[1]),
                new Point2D(coordinates[2], coordinates[3])
        );
        boolean actual = ellipseAction.isCrossOy(ellipse);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code EllipseAction::isCrossOy}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeDataCrossOy")
    public Object[][] createNegativeDataCrossOy() {
        return new Object[][]{
                {new double[]{-6.5, 2, -2, -2}, false},
                {new double[]{2, -2, 6.5, -6}, false}
        };
    }

    /**
     * Testing negative script in method {@code EllipseAction::isCrossOy}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     */
    @Test(description = "negative script to check on crossing axis Oy",
            dataProvider = "negativeDataCrossOy")
    public void testNegativeIsCrossOy(final double[] coordinates,
                                      final boolean expected) {
        Ellipse ellipse = new Ellipse(
                new Point2D(coordinates[0], coordinates[1]),
                new Point2D(coordinates[2], coordinates[3])
        );
        boolean actual = ellipseAction.isCrossOy(ellipse);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide positive data for testing {@code EllipseAction::calcPerimeter}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataPerimeter")
    public Object[][] createPositiveDataPerimeter() {
        return new Object[][]{
                {new double[]{0.0, 0.0, 4.0, 4.0}, (2 + 2) * Math.PI},
                {new double[]{2.5, 3.2, -6.6, -3.1}, (3.15 + 4.55) * Math.PI},
                {new double[]{-6.6, -3.1, 2.5, 3.2}, (3.15 + 4.55) * Math.PI},
                {new double[]{-3.0, -1.0, -1.0, -5.0}, (1 + 2) * Math.PI},
                {new double[]{-2.0, 3.0, 0.0, -3.0}, (1 + 3) * Math.PI},
                {new double[]{0.0, -3.0, -2.0, 3.0}, (1 + 3) * Math.PI}
        };
    }

    /**
     * Testing positive script in method {@code EllipseAction::calcPerimeter}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     * @throws WrongArgumentException if the rectangle points not set
     *                                {@code Ellipse}
     */
    @Test(description = "positive scripts of perimeter calculation",
            dataProvider = "positiveDataPerimeter")
    public void testPositiveCalcPerimeter(final double[] coordinates,
                                          final double expected)
            throws WrongArgumentException {
        EllipseFactory ellipseFactory = new EllipseFactory();
        Ellipse ellipse = (Ellipse) ellipseFactory.createFigure(coordinates);
        double actual = ellipseAction.calcPerimeter(ellipse);
        Assert.assertEquals(actual, expected, errorCalculation);
    }

    /**
     * Provide positive data for testing {@code EllipseAction::calcSquare}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataSquare")
    public Object[][] createPositiveDataSquare() {
        return new Object[][]{
                {new double[]{0.0, 0.0, 4.0, 4.0}, 2 * 2 * Math.PI},
                {new double[]{2.5, 3.2, -6.6, -3.1}, 3.15 * 4.55 * Math.PI},
                {new double[]{-6.6, -3.1, 2.5, 3.2}, 3.15 * 4.55 * Math.PI},
                {new double[]{-3.0, -1.0, -1.0, -5.0}, 1 * 2 * Math.PI},
                {new double[]{-2.0, 3.0, 0.0, -3.0}, 1 * 3 * Math.PI},
                {new double[]{0.0, -3.0, -2.0, 3.0}, 1 * 3 * Math.PI}
        };
    }

    /**
     * Testing positive script in method {@code EllipseAction::calcSquare}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     * @throws WrongArgumentException if the rectangle points not set
     *                                {@code Ellipse}
     */
    @Test(description = "positive scripts of square calculation",
            dataProvider = "positiveDataSquare")
    public void testPositiveCalcSquare(final double[] coordinates,
                                       final double expected)
            throws WrongArgumentException {
        EllipseFactory ellipseFactory = new EllipseFactory();
        Ellipse ellipse = (Ellipse) ellipseFactory.createFigure(coordinates);

        double actual = ellipseAction.calcSquare(ellipse);
        Assert.assertEquals(actual, expected, errorCalculation);
    }
}


