package by.tolkun.infohandler.composite.visitor;

/**
 * Interface for classes allow to visit and traversal them.
 *
 * @author Kirill Tolkun
 */
public interface Visitable {

    /**
     * Initialize {@code Visitor}.
     *
     * @param visitor to traversal this and child components
     */
    void accept(Visitor visitor);
}
