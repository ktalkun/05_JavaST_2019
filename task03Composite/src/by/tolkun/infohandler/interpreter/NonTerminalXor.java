package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation XOR.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalXor extends BinaryNonTerminal {

    /**
     * Logger of class {@code NonTerminalXor}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalXor.class);

    /**
     * Default constructor.
     */
    public NonTerminalXor() {
        operator = (integer1, integer2) -> integer1 ^ integer2;
        LOGGER.debug("NonTerminalXor created.");
    }
}
