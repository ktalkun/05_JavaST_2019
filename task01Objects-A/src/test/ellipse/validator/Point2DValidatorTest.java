package test.ellipse.validator;

import by.tolkun.ellipse.validator.Point2DValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code Point2DValidator}.
 *
 * @author Kirill Tolkun
 */
public class Point2DValidatorTest {

    /**
     * Object of validator for testing methods.
     */
    private Point2DValidator point2DValidator = new Point2DValidator();

    /**
     * Provide positive data for testing {@code Point2DValidator::isValid}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {new double[]{-10, 0}, true},
                {new double[]{-1.0, 0.0}, true},
                {new double[]{1., .1}, true}
        };
    }

    /**
     * Testing positive script in method {@code Point2DValidator::isValid}.
     *
     * @param coordinates of points
     * @param expected result
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveIsValid(final double[] coordinates,
                                    final boolean expected) {
        boolean actual = point2DValidator.isValid(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code Point2DValidator::isValid}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][]{
                {new double[]{0.1, 10, .2}, false},
                {new double[]{-10}, false},
                {new double[]{}, false}
        };
    }

    /**
     * Testing negative script in method {@code Point2DValidator::isValid}.
     *
     * @param coordinates of points
     * @param expected result
     */
    @Test(description = "negative script",
            dataProvider = "negativeData")
    public void testNegativeIsValid(final double[] coordinates,
                                    final boolean expected) {
        boolean actual = point2DValidator.isValid(coordinates);
        Assert.assertEquals(actual, expected);
    }
}
