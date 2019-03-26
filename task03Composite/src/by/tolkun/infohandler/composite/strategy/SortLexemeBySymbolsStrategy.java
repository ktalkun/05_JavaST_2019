package by.tolkun.infohandler.composite.strategy;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.exception.WrongArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class for sorting words by the length.
 *
 * @author Kirill Tolkun
 */
public class SortLexemeBySymbolsStrategy extends SortTextStrategy {

    /**
     * Logger of class {@code SortWordsBySymbolsStrategy}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SortLexemeBySymbolsStrategy.class);

    /**
     * Default constructor.
     */
    public SortLexemeBySymbolsStrategy() {
        LOGGER.debug("SortWordsBySymbolsStrategy created.");
    }

    /**
     * Sort words by the length.
     *
     * @param currentComponent the component for sorting
     * @throws WrongArgumentException if {@code currentComponent} hasn't child
     *                                components for sorting
     */
    @Override
    public void sort(final TextComponent currentComponent)
            throws WrongArgumentException {
        sort(
                currentComponent,
                TextComponentType.LEXEME,
                TextComponentType.SYMBOL,
                List::size
        );
    }
}
