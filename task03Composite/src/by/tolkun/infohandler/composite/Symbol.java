package by.tolkun.infohandler.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Class of leaf text component.
 *
 * @author Kirill Tolkun
 */
public class Symbol extends TextComponent {

    /**
     * Symbol of text component.
     */
    private char symbol;

    /**
     * Logger of class {@code Symbol}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Symbol.class);

    /**
     * Constructor with parameters.
     *
     * @param inputSymbol of text component.
     */
    public Symbol(final int inputSymbol) {
        symbol = (char) inputSymbol;
        componentType = TextComponentType.SYMBOL;
        LOGGER.debug("Symbol created.");
    }


    /**
     * Unsupported method.
     *
     * @param textComponent to add as child
     */
    public void add(final TextComponent textComponent) {
        throw new UnsupportedOperationException("\"remove\" unsupported.");
    }

    /**
     * Unsupported method.
     */
    @Override
    public void remove(final TextComponent component) {
        throw new UnsupportedOperationException("\"remove\" unsupported.");
    }

    /**
     * Unsupported method.
     */
    @Override
    public TextComponent getChild(final int i) {
        throw new UnsupportedOperationException("\"getChild\" unsupported.");
    }

    /**
     * Unsupported method.
     *
     * @return number of child components
     */
    @Override
    public long size() {
        throw new UnsupportedOperationException("\"size\" unsupported.");
    }

    /**
     * Clone text {@code Symbol}.
     *
     * @return clone of {@code Symbol}
     */
    @Override
    public TextComponent clone() {
        return new Symbol(symbol);
    }

    /**
     * Returns a {@code String} object representing this {@code Symbol}.
     *
     * @return string representation of object.
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    /**
     * Collect text composite and calculate bit expressions.
     *
     * @return string representation of collected text composite
     */
    @Override
    public String collect() {
        return toString();
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code TextComposite} object that
     * contains the same character.
     *
     * @param o the object to compare with.
     * @return {@code true} if the objects are the same;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol symbol1 = (Symbol) o;
        return symbol == symbol1.symbol;
    }

    /**
     * Returns a hash code for a {@code Symbol}.
     *
     * @return a hash code value for a {@code Symbol}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
