package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to interpret not terminal as bit operation NOT.
 *
 * @author Kirill Tolkun
 */
public class NonTerminalNot extends UnaryNonTerminal {

    /**
     * Logger of class {@code NonTerminalNot}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(NonTerminalNot.class);

    /**
     * Default constructor.
     */
    public NonTerminalNot() {
        operator = integer -> ~integer;
        LOGGER.debug("NonTerminalNot created.");
    }

}
