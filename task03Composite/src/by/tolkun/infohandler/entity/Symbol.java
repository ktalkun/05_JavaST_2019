package by.tolkun.infohandler.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Symbol extends TextComponent {

    private char symbol;

    private static final Logger LOGGER
            = LogManager.getLogger(Symbol.class);

    public Symbol(final  int inputSymbol) {
        symbol = (char)inputSymbol;
        LOGGER.debug("Symbol created.");
    }

    public void add(final TextComponent textComponent) {
        throw new UnsupportedOperationException("\"remove\" unsupported.");
    }

    @Override
    public void remove(final TextComponent component) {
        throw new UnsupportedOperationException("\"remove\" unsupported.");
    }

    @Override
    public TextComponent getChild(final int i) {
        throw new UnsupportedOperationException("\"getChild\" unsupported.");
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
