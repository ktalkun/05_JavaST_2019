package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.entity.TextComponent;
import by.tolkun.infohandler.exception.ParserException;

public abstract class Parser {

    protected Parser nextParser;

    public abstract void parse(String parseString, TextComponent textComponent)
            throws ParserException;
}
