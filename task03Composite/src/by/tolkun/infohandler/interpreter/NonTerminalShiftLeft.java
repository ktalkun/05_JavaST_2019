package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation SHIFT_LEFT.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalShiftLeft extends BinaryNonTerminal {

    /**
     * Logger of class {@code NonTerminalShiftLeft}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalShiftLeft.class);

    /**
     * Default constructor.
     */
    public NonTerminalShiftLeft() {
        operator = (integer1, integer2) -> integer1 << integer2;
        LOGGER.debug("NonTerminalShiftLeft created.");
    }
}
