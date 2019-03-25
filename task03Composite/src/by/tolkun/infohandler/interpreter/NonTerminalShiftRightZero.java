package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation SHIFT_RIGHT_FILL_ZERO.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalShiftRightZero
        implements AbstractBitExpression {

    /**
     * Logger of class {@code NonTerminalShiftRightZero}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalShiftRightZero.class);

    /**
     * Default constructor.
     */
    public NonTerminalShiftRightZero() {
        LOGGER.debug("NonTerminalShiftRightZero created.");
    }

    /**
     * Interpret not terminal as operator SHIFT_RIGHT_FILL_ZERO.
     *
     * @param context to cache numbers during the calculating
     */
    @Override
    public void interpret(final Context context) {
        int rightOperand = context.popValue();
        int leftOperand = context.popValue();
        context.pushValue(leftOperand >>> rightOperand);
    }
}
