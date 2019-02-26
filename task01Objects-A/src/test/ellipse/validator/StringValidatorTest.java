package test.ellipse.validator;

import by.tolkun.ellipse.validator.StringValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code StringValidator}.
 *
 * @author Kirill Tolkun
 */
public class StringValidatorTest {

    /**
     * Object of validator for testing methods.
     */
    private StringValidator stringValidator = new StringValidator();

    /**
     * Provide positive data for testing {@code StringValidator::isValid}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {"-10 0 10 01 10", true},
                {"-1.0 0.0 1.0", true},
                {"1. .1", true}
        };
    }

    /**
     * Testing positive script in method {@code StringValidator::isValid}.
     *
     * @param line     for validating
     * @param expected result
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveIsValid(final String line, final boolean expected) {
        boolean actual = stringValidator.isValid(line);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code StringValidator::isValid}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][]{
                {"10a a10 *15 15* -15-", false},
                {"!1 ,2 :3 ;4", false},
                {". 0.0 1.0 0.1", false}
        };
    }

    /**
     * Testing negative script in method {@code StringValidator::isValid}.
     *
     * @param line     for validating
     * @param expected result
     */
    @Test(description = "negative script",
            dataProvider = "negativeData")
    public void testNegativeIsValid(final String line, final boolean expected) {
        boolean actual = stringValidator.isValid(line);
        Assert.assertEquals(actual, expected);
    }
}
