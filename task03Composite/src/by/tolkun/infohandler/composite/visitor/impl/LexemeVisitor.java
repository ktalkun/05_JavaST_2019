package by.tolkun.infohandler.composite.visitor.impl;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.visitor.Visitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Visitor to traversal text components of composite and form lexeme list.
 *
 * @author Kirill Tolkun
 */
public class LexemeVisitor implements Visitor {

    /**
     * List of lexemes.
     */
    private List<TextComponent> lexemes;

    /**
     * Logger of class {@code LexemeVisitor}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(LexemeVisitor.class);

    /**
     * Default constructor.
     */
    public LexemeVisitor() {
        lexemes = new ArrayList<>();
        LOGGER.debug("LexemeVisitor created.");
    }

    /**
     * Get list of lexemes.
     *
     * @return list of lexemes
     */
    public List<TextComponent> getLexemes() {
        return lexemes;
    }

    /**
     * Clear list of lexemes.
     */
    public void clear() {
        lexemes.clear();
    }

    /**
     * Visit text component and form list of lexemes.
     *
     * @param textComponent the initial text component to visit
     */
    @Override
    public void visit(final TextComponent textComponent) {
        Deque<TextComponent> traversalComponents = new ArrayDeque<>();
        traversalComponents.push(textComponent);
        while (!traversalComponents.isEmpty()) {
            TextComponent traversalComponent = traversalComponents.pop();
            for (int i = 0; i < traversalComponent.size(); i++) {
                TextComponent anyComponent = traversalComponent.getChild(i);
                if (anyComponent.getType() == TextComponentType.LEXEME) {
                    lexemes.add(anyComponent.clone());
                } else if (anyComponent.getType() != TextComponentType.SYMBOL) {
                    traversalComponents.push(anyComponent);
                }
            }
        }
    }
}
