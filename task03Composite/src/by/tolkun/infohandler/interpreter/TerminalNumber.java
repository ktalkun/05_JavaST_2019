package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret terminal as number.
 *
 * @author Kirill Tolkun
 */
public class TerminalNumber implements AbstractBitExpression {

    /**
     * Number of terminal.
     */
    private int number;

    /**
     * Logger of class {@code TerminalNumber}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TerminalNumber.class);

    /**
     * Default constructor.
     * @param inputNumber of terminal
     */
    public TerminalNumber(final int inputNumber) {
        number = inputNumber;
        LOGGER.debug("TerminalExpressionNumber created.");
    }

    /**
     * Interpret terminal as number.
     *
     * @param context to cache numbers during the calculating
     */
    @Override
    public void interpret(final Context context) {
        context.pushValue(number);
    }
}
