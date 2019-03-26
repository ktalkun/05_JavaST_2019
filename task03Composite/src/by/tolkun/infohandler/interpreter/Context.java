package by.tolkun.infohandler.interpreter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class to store cache values during the calculating.
 *
 * @author Kirill Tolkun
 */
public class Context {

    /**
     * Stack of numbers to store cache values during the calculating.
     */
    private Deque<Integer> contextValues = new ArrayDeque<>();

    /**
     * Logger of class {@code Context}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Context.class);

    /**
     * Default constructor.
     */
    public Context() {
        LOGGER.debug("Context created.");
    }

    /**
     * Pop (retrive and delete from the heed) value.
     *
     * @return value from the head
     */
    public Integer popValue() {
        return contextValues.pop();
    }

    /**
     * Push (add to head) value.
     *
     * @param value to push
     */
    public void pushValue(final Integer value) {
        contextValues.push(value);
    }

    /**
     * Retunr size of context.
     *
     * @return number of numbers in context
     */
    public int size() {
        return contextValues.size();
    }
}
