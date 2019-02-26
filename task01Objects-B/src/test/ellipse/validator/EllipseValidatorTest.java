package test.ellipse.validator;

import by.tolkun.ellipse.validator.EllipseValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code EllipseValidator}.
 *
 * @author Kirill Tolkun
 */
public class EllipseValidatorTest {

    /**
     * Object of validator for testing methods.
     */
    private EllipseValidator ellipseValidator = new EllipseValidator();

    /**
     * Provide positive data for testing {@code EllipseValidator::isValid}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {new double[]{-1, 1, 1, -1}, true},
                {new double[]{-1.0, 1.0, 1.0, -1.0}, true},
                {new double[]{.0, 0., .1, -1.}, true}
        };
    }

    /**
     * Testing positive script in method {@code EllipseValidator::isValid}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected    result
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveIsValid(final double[] coordinates,
                                    final boolean expected) {
        boolean actual = ellipseValidator
                .isValid(coordinates);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code EllipseValidator::isValid}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][]{
                {new double[]{}, false},
                {new double[]{0.1}, false},
                {new double[]{10., 12}, false},
                {new double[]{10., 0, .18}, false},
                {new double[]{2, 2, 2, 2}, false},
                {new double[]{2, 4, 2, 3}, false},
                {new double[]{3, 2, 4, 2}, false},
                {new double[]{3, 2, 4, 2, 0}, false}
        };
    }

    /**
     * Testing negative script in method {@code EllipseValidator::isValid}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @param expected result
     */
    @Test(description = "negative script",
            dataProvider = "negativeData")
    public void testNegativeIsValid(final double[] coordinates,
                                    final boolean expected) {
        boolean actual = ellipseValidator
                .isValid(coordinates);
        Assert.assertEquals(actual, expected);
    }
}
