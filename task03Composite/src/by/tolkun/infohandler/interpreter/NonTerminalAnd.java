package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation AND.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalAnd implements AbstractBitExpression {

    /**
     * Logger of class {@code NonTerminalAnd}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalAnd.class);

    /**
     * Default constructor.
     */
    public NonTerminalAnd() {
        LOGGER.debug("NonTerminalAnd created.");
    }

    /**
     * Interpret not terminal as operator AND.
     *
     * @param context to cache numbers during the calculating
     */
    @Override
    public void interpret(final Context context) {
        int rightOperand = context.popValue();
        int leftOperand = context.popValue();
        context.pushValue(leftOperand & rightOperand);
    }
}
