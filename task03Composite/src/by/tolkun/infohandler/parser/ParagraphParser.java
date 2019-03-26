package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to parse string into sentences.
 *
 * @author Kirill Tolkun
 */
public class ParagraphParser extends Parser {

    /**
     * Pattern to split into sentences.
     */
    private static final Pattern PARAGRAPH_PATTERN
            = Pattern.compile(".*?(\\.{3}|[\\.!?])");

    /**
     * Logger of class {@code ParagraphParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ParagraphParser.class);

    /**
     * Default constructor.
     *
     * @param inputNextParser the next parser of the parsers chain
     */
    public ParagraphParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("ParagraphParser created.");
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
        Matcher sentenceMatcher = PARAGRAPH_PATTERN.matcher(parseString);
        boolean hasParagraphs = false;
        while (sentenceMatcher.find()) {
            TextComposite sentence
                    = new TextComposite(TextComponentType.SENTENCE);
            textComponent.add(sentence);
            nextParser.parse(sentenceMatcher.group().trim(), sentence);
            hasParagraphs = true;
        }
        if(!hasParagraphs){
            TextComposite sentence
                    = new TextComposite(TextComponentType.SENTENCE);
            textComponent.add(sentence);
            nextParser.parse(parseString.trim(), sentence);
        }
    }
}
