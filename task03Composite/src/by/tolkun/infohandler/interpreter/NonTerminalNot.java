package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation NOT.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalNot implements AbstractBitExpression {

    /**
     * Logger of class {@code NonTerminalNot}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalNot.class);

    /**
     * Default constructor.
     */
    public NonTerminalNot() {
        LOGGER.debug("NonTerminalNot created.");
    }

    /**
     * Interpret not terminal as operator NOT.
     *
     * @param context to cache numbers during the calculating
     */
    @Override
    public void interpret(final Context context) {
        int rightOperand = context.popValue();
        context.pushValue(~rightOperand);
    }
}
