package test.ellipse.parser;

import by.tolkun.ellipse.parser.DoubleArrayParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code DoubleArrayParser}.
 *
 * @author Kirill Tolkun
 */
public class DoubleArrayParserTest {
    /**
     * Object of validator for testing methods.
     */
    private DoubleArrayParser doubleArrayParser = new DoubleArrayParser();

    /**
     * Provide positive data for testing {@code DoubleArrayParser::parse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {"-1 0 1", new double[]{-1, 0, 1}},
                {"-1.0 0.0 1.0", new double[]{-1.0, 0.0, 1.0}},
                {"1. .1", new double[]{1.0, 0.1}},
                {"", new double[]{}}
        };
    }

    /**
     * Testing positive script in method {@code DoubleArrayParser::parse}.
     *
     * @param line     for validating
     * @param expected result
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveIsValid(final String line,
                                    final double[] expected) {
        double[] actual = doubleArrayParser.parse(line);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code DoubleArrayParser::parse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][]{
                {"0.1 10a .2", new double[]{}},
                {"*10", new double[]{}},
                {". ", new double[]{}}
        };
    }

    /**
     * Testing negative script in method {@code DoubleArrayParser::parse}.
     *
     * @param line     for validating
     * @param expected result
     */
    @Test(description = "negative script",
            dataProvider = "negativeData")
    public void testNegativeParse(final String line, final double[] expected) {
        double[] actual = doubleArrayParser.parse(line);
        Assert.assertEquals(actual, expected);
    }
}
