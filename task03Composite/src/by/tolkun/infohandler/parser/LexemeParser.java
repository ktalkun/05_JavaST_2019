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
 * Class to parse string into lexemes.
 *
 * @author Kirill Tolkun
 */
public class LexemeParser extends Parser {

    /**
     * Pattern to split into lexemes.
     */
    private static final Pattern LEXEME_PATTERN =
            Pattern.compile("(.*?)(\\.{3}|[\\.,!?])?$");

    /**
     * Pattern to split into expressions.
     */
    private static final Pattern EXPRESSION_PATTERN
            = Pattern.compile("(\\(*~?[+-]?\\d+\\)*(<{2}|>{2,3}|[&\\|\\^])?)*");

    /**
     * Logger of class {@code LexemeParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LexemeParser.class);

    /**
     * Default constructor.
     *
     * @param inputNextParser the next parser of the parsers chain
     */
    public LexemeParser(final Parser inputNextParser) {
        nextParser = inputNextParser;
        LOGGER.debug("LexemeParser created.");
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
        Matcher lexemeMatcher = LEXEME_PATTERN.matcher(parseString);
        if (lexemeMatcher.groupCount() != 0) {
            while (lexemeMatcher.find()) {
                if (lexemeMatcher.group().length() != 0) {
                    if (lexemeMatcher.group(1) != null) {
                        String token = lexemeMatcher.group(1);
                        TextComposite abstractLexeme;
                        if (EXPRESSION_PATTERN.matcher(token).matches()) {
                            abstractLexeme = new TextComposite(TextComponentType
                                    .EXPRESSION);
                        } else {
                            abstractLexeme
                                    = new TextComposite(TextComponentType.WORD);
                        }
                        textComponent.add(abstractLexeme);
                        nextParser
                                .parse(lexemeMatcher.group(1), abstractLexeme);
                    }
                    if (lexemeMatcher.group(2) != null) {
                        TextComposite punctuation = new TextComposite(
                                TextComponentType.PUNCTUATION);
                        textComponent.add(punctuation);
                        nextParser.parse(lexemeMatcher.group(2), punctuation);
                    }
                }
            }
        } else {
            throw new ParserException("Invalid input data: "
                    + parseString + ".");
        }
    }
}

