package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.entity.TextComponent;
import by.tolkun.infohandler.entity.TextComponentType;
import by.tolkun.infohandler.entity.TextComposite;
import by.tolkun.infohandler.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextParser extends Parser {

    private static final Pattern PARAGRAPH_PATTERN
            = Pattern.compile("\t");

    private static final Logger LOGGER
            = LogManager.getLogger(TextParser.class);


    public TextParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("TextParser created.");
    }

    @Override
    public void parse(final String parseString,
                      final TextComponent textComponent) throws ParserException {
        List<String> paragraphTokens = Arrays
                .stream(PARAGRAPH_PATTERN.split(parseString))
                .filter(s -> !"".equals(s))
                .collect(Collectors.toList());
        if (paragraphTokens.size() != 0) {
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
