package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.composite.Symbol;
import by.tolkun.infohandler.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to parse string into symbols.
 *
 * @author Kirill Tolkun
 */
public final class WordParser extends Parser {

    /**
     * Logger of class {@code WordParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(WordParser.class);

    /**
     * Default constructor.
     *
     * @param inputNextParser the next parser of the parsers chain
     */
    public WordParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("WordParser created.");
    }


    /**
     * Parse string and add components to textComponent.
     *
     * @param parseString   to parse
     * @param textComponent to add new component
     */
    @Override
    public void parse(final String parseString,
                      final TextComponent textComponent) {
        parseString
                .chars()
                .mapToObj(Symbol::new)
                .forEach(textComponent::add);
    }
}
