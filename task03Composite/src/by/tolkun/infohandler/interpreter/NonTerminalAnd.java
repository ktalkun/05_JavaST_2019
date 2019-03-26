package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation AND.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalAnd extends BinaryNonTerminal {

    /**
     * Logger of class {@code NonTerminalAnd}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalAnd.class);

    /**
     * Default constructor.
     */
    public NonTerminalAnd() {
        operator = (integer1, integer2) -> integer1 & integer2;
        LOGGER.debug("NonTerminalAnd created.");
    }
}
