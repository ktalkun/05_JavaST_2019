package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation OR.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalOr extends BinaryNonTerminal {

    /**
     * Logger of class {@code NonTerminalOr}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalOr.class);

    /**
     * Default constructor.
     */
    public NonTerminalOr() {
        operator = (integer1, integer2) -> integer1 | integer2;
        LOGGER.debug("NonTerminalOr created.");
    }
}
