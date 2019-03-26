package test.infohandler.interpreter;

import by.tolkun.infohandler.exception.WrongArgumentException;
import by.tolkun.infohandler.interpreter.Interpreter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code InterpreterTest}.
 *
 * @author Kirill Tolkun
 */
public class InterpreterTest {

    /**
     * Provide positive data for testing {@code Interpreter::calculate}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {"123", 123},
                {"13<<2", 52},
                {"13<<2", 52},
                {"30>>>3", 3},
                {"-30>>>3", 536870908},
                {"~6&9|(3&4)", 9},
                {"5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)", 5},
        };
    }


    /**
     * Test positive script in method {@code Interpreter::calculate}.
     *
     * @param infixExpression for calculating
     * @param expected        list of text components
     * @throws WrongArgumentException if {@code infixExpression} is invalid
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveParse(final String infixExpression,
                                  final Integer expected)
            throws WrongArgumentException {

        Interpreter interpreter = new Interpreter(infixExpression);
        Integer actual = interpreter.calculate();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code Interpreter::calculate}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][]{
                {"1&&2"},
                {"13~2"},
                {"132|"},
                {"&"},
                {"2+5"},
        };
    }


    /**
     * Test negative script in method {@code Interpreter::calculate}.
     *
     * @param infixExpression for calculating
     * @throws WrongArgumentException if {@code infixExpression} is invalid
     */
    @Test(description = "negative script",
            dataProvider = "negativeData",
            expectedExceptions = WrongArgumentException.class)
    public void testNegativeParse(final String infixExpression)
            throws WrongArgumentException {

        Interpreter interpreter = new Interpreter(infixExpression);
        interpreter.calculate();
    }
}
