package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.entity.TextComponent;
import by.tolkun.infohandler.entity.TextComponentType;
import by.tolkun.infohandler.entity.TextComposite;
import by.tolkun.infohandler.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends Parser {

    private static final Pattern PARAGRAPH_PATTERN
            = Pattern.compile(".*?(\\.{3}|[\\.!?])");

    private static final Logger LOGGER
            = LogManager.getLogger(ParagraphParser.class);

    public ParagraphParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("ParagraphParser created.");
    }

    @Override
    public void parse(final String parseString,
                      final TextComponent textComponent)
            throws ParserException {
        Matcher sentenceMatcher = PARAGRAPH_PATTERN.matcher(parseString);
        if (sentenceMatcher.groupCount() != 0) {
            while (sentenceMatcher.find()) {
                TextComposite sentence
                        = new TextComposite(TextComponentType.SENTENCE);
                textComponent.add(sentence);
                nextParser.parse(sentenceMatcher.group(), sentence);
            }
        } else {
            TextComposite sentence
                    = new TextComposite(TextComponentType.SENTENCE);
            textComponent.add(sentence);
            nextParser.parse(parseString, sentence);
        }
    }
}
