package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation SHIFT_RIGHT_FILL_ZERO.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalShiftRightZero extends BinaryNonTerminal {

    /**
     * Logger of class {@code NonTerminalShiftRightZero}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalShiftRightZero.class);

    /**
     * Default constructor.
     */
    public NonTerminalShiftRightZero() {
        operator = (integer1, integer2) -> integer1 >>> integer2;
        LOGGER.debug("NonTerminalShiftRightZero created.");
    }
}
