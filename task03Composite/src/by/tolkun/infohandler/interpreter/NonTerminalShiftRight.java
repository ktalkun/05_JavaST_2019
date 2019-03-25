package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation SHIFT_RIGHT.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalShiftRight implements AbstractBitExpression {

    /**
     * Logger of class {@code NonTerminalShiftRight}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalShiftRight.class);

    /**
     * Default constructor.
     */
    public NonTerminalShiftRight() {
        LOGGER.debug("NonTerminalShiftRight created.");
    }

    /**
     * Interpret not terminal as operator SHIFT_RIGHT.
     *
     * @param context to cache numbers during the calculating
     */
    @Override
    public void interpret(final Context context) {
        int rightOperand = context.popValue();
        int leftOperand = context.popValue();
        context.pushValue(leftOperand >> rightOperand);
    }
}
