package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Class to parse string into lexemes with whitespace.
 *
 * @author Kirill Tolkun
 */
public class SentenceParser extends Parser {

    /**
     * Pattern to split into lexemes with whitespace.
     */
    private static final Pattern SENTENCE_PATTERN
            = Pattern.compile(" ");

    /**
     * Logger of class {@code SentenceParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SentenceParser.class);

    /**
     * Default constructor.
     *
     * @param inputNextParser the next parser of the parsers chain
     */
    public SentenceParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("SentenceParser created.");
    }

    /**
     * Parse string and add components to textComponent.
     *
     * @param parseString   to parse
     * @param textComponent to add new component
     * @throws ParserException if string can not be parsed by the parsers chain
     */
    @Override
    public void parse(final String parseString,
                      final TextComponent textComponent)
            throws ParserException {
        String[] lexemeTokens = SENTENCE_PATTERN.split(parseString);
        if (lexemeTokens.length != 0) {
            for (String lexemeToken : lexemeTokens) {
                TextComposite lexeme
                        = new TextComposite(TextComponentType.LEXEME);
                textComponent.add(lexeme);
                nextParser.parse(lexemeToken.trim(), lexeme);
            }
        } else {
            TextComposite lexeme
                    = new TextComposite(TextComponentType.LEXEME);
            textComponent.add(lexeme);
            nextParser.parse(parseString, lexeme);
        }
    }
}
