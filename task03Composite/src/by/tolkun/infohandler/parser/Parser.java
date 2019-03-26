package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.exception.ParserException;

/**
 * Abstract parser to realize common behavior.
 *
 * @author Kirill Tolkun
 */
public abstract class Parser {

    /**
     * Next parser to realize chain of responsibility.
     */
    protected Parser nextParser;

    /**
     * Parse string and add components to textComponent.
     *
     * @param parseString   to parse
     * @param textComponent to add new component
     * @throws ParserException if string can not be parsed by the parsers chain
     */
    public abstract void parse(String parseString, TextComponent textComponent)
            throws ParserException;
}
