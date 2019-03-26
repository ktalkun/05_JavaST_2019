package by.tolkun.infohandler.composite.strategy;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.exception.WrongArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class for sorting paragraphs by the number of sentences.
 *
 * @author Kirill Tolkun
 */
public class SortParagraphsBySentencesStrategy extends SortTextStrategy {

    /**
     * Logger of class {@code SortWordsBySymbolsStrategy}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SortParagraphsBySentencesStrategy.class);

    /**
     * Default constructor.
     */
    public SortParagraphsBySentencesStrategy() {
        LOGGER.debug("SortParagraphsBySentencesStrategy created.");
    }

    /**
     * Sort paragraphs by the number of sentences.
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
                TextComponentType.PARAGRAPH,
                TextComponentType.SENTENCE,
                List::size
        );
    }
}
