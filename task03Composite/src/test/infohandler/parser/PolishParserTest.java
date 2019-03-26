package test.infohandler.parser;

import by.tolkun.infohandler.exception.WrongArgumentException;
import by.tolkun.infohandler.parser.PolishParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code PolishParser}.
 *
 * @author Kirill Tolkun
 */
public class PolishParserTest {
    /**
     * Object of {@code PolishParser} for testing methods.
     */
    private PolishParser polishParser = new PolishParser();

    /**
     * Provide positive data for testing {@code PolishParser::parse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {"()", ""},
                {"13<<2", "13 2 <<"},
                {"13<<(2)", "13 2 <<"},
                {"13 >> 2", "13 2 >>"},
                {"~6&9|(3&4)", "6 ~ 9 & 3 4 & |"},
                {"(1^1&(2|5>>2&71))|1", "1 1 2 5 2 >> 71 & | & ^ 1 |"},
                {"-3|(2&1>>2)|2", "-3 2 1 2 >> & 2 ||"},
        };
    }


    /**
     * Test positive script in method {@code PolishParser::parse}.
     *
     * @param inputExpression for validating
     * @param expected        expression after parsing
     * @throws WrongArgumentException if {@code inputExpression} is invalid
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveParse(final String inputExpression,
                                  final String expected)
            throws WrongArgumentException {
        String actual = polishParser.parse(inputExpression);
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code PolishParser::parse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][]{
                {")("},
                {"13 >> (2"},
                {"13 >> 2)"},
                {"(13 >> 2"},
        };
    }

    /**
     * Test negative script in method {@code PolishParser::parse}.
     *
     * @param inputExpression for validating
     * @throws WrongArgumentException if {@code inputExpression} is invalid
     */
    @Test(description = "negative script",
            dataProvider = "negativeData",
            expectedExceptions = WrongArgumentException.class)
    public void testNegativeParse(final String inputExpression)
            throws WrongArgumentException {
        polishParser.parse(inputExpression);
    }
}
