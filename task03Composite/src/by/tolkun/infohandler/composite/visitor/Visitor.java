package by.tolkun.infohandler.composite.visitor;

import by.tolkun.infohandler.composite.TextComponent;

/**
 * Interface for visitor classes.
 *
 * @author Kirill Tolkun
 */
public interface Visitor {
    /**
     * Visit text component.
     *
     * @param textComponent the initial text component to visit
     */
    void visit(TextComponent textComponent);
}
