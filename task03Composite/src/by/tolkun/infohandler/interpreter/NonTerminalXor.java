package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation XOR.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalXor implements AbstractBitExpression {

    /**
     * Logger of class {@code NonTerminalXor}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalXor.class);

    /**
     * Default constructor.
     */
    public NonTerminalXor() {
        LOGGER.debug("NonTerminalXor created.");
    }

    /**
     * Interpret not terminal as operator XOR.
     *
     * @param context to cache numbers during the calculating
     */
    @Override
    public void interpret(final Context context) {
        int rightOperand = context.popValue();
        int leftOperand = context.popValue();
        context.pushValue(leftOperand ^ rightOperand);
    }
}
