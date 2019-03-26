package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class to parse string into paragraphs.
 *
 * @author Kirill Tolkun
 */
public class TextParser extends Parser {

    /**
     * Pattern to split into paragraphs.
     */
    private static final Pattern PARAGRAPH_PATTERN
            = Pattern.compile("\t");

    /**
     * Logger of class {@code TextParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TextParser.class);

    /**
     * Default constructor.
     *
     * @param inputNextParser the next parser of the parsers chain
     */
    public TextParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("TextParser created.");
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
        List<String> paragraphTokens = Arrays
                .stream(PARAGRAPH_PATTERN.split(parseString))
                .filter(s -> !"".equals(s))
                .collect(Collectors.toList());
        if (!paragraphTokens.isEmpty()) {
            for (String paragraphToken : paragraphTokens) {
                TextComposite paragraph
                        = new TextComposite(TextComponentType.PARAGRAPH);
                textComponent.add(paragraph);
                nextParser.parse(paragraphToken, paragraph);
            }
        } else {
            TextComposite paragraph
                    = new TextComposite(TextComponentType.PARAGRAPH);
            textComponent.add(paragraph);
            nextParser.parse(parseString, paragraph);
        }
    }
}
