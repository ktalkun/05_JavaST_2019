package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation OR.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalOr implements AbstractBitExpression {

    /**
     * Logger of class {@code NonTerminalOr}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalOr.class);

    /**
     * Default constructor.
     */
    public NonTerminalOr() {
        LOGGER.debug("NonTerminalOr created.");
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
        context.pushValue(leftOperand | rightOperand);
    }
}
