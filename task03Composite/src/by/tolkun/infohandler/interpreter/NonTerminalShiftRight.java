package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation SHIFT_RIGHT.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalShiftRight extends BinaryNonTerminal {

    /**
     * Logger of class {@code NonTerminalShiftRight}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalShiftRight.class);

    /**
     * Default constructor.
     */
    public NonTerminalShiftRight() {
        operator = (integer1, integer2) -> integer1 >> integer2;
        LOGGER.debug("NonTerminalShiftRight created.");
    }
}
