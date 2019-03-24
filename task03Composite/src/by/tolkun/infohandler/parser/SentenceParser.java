package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.entity.TextComponent;
import by.tolkun.infohandler.entity.TextComponentType;
import by.tolkun.infohandler.entity.TextComposite;
import by.tolkun.infohandler.exception.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends Parser {

    private static final Pattern SENTENCE_PATTERN
            = Pattern.compile(" ");

    private static final Logger LOGGER
            = LogManager.getLogger(SentenceParser.class);

    public SentenceParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("SentenceParser created.");
    }

    @Override
    public void parse(final String parseString,
                      final TextComponent textComponent)
            throws ParserException {
        String[] lexemeTokens  = SENTENCE_PATTERN.split(parseString);
        if (lexemeTokens.length != 0) {
            for(String lexemeToken: lexemeTokens) {
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
