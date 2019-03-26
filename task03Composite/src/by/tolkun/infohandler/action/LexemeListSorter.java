package by.tolkun.infohandler.action;

import by.tolkun.infohandler.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 * Class to sort lexeme list by descending number of occurrences of a given
 * character.
 */
public class LexemeListSorter {

    /**
     * Symbol for sorting by it.
     */
    private char symbol;

    /**
     * Logger of class {@code LexemeListSorter}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LexemeListSorter.class);

    /**
     * Default constructor.
     *
     * @param inputSymbol the symbol for sorting by it
     */
    public LexemeListSorter(final char inputSymbol) {
        symbol = inputSymbol;
        LOGGER.debug("LexemeListSorter created.");
    }

    /**
     * Sort lexeme list by descending number of occurrences of a given
     * character.
     *
     * @param lexemes for sorting
     */
    public void sort(final List<TextComponent> lexemes) {
        lexemes.get(0).size();
        lexemes.sort(Comparator.comparing(textComponent ->
                textComponent
                        .toString()
                        .chars()
                        .filter(value -> value == symbol)
                        .count(),
                Comparator.reverseOrder()
        ).thenComparing(Object::toString));
    }

}
