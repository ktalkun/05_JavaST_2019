package by.tolkun.infohandler.composite;

import by.tolkun.infohandler.composite.strategy.SortTextStrategy;
import by.tolkun.infohandler.composite.visitor.Visitable;
import by.tolkun.infohandler.composite.visitor.Visitor;
import by.tolkun.infohandler.exception.UnsupportedMethodException;
import by.tolkun.infohandler.exception.WrongArgumentException;

/**
 * Abstract class to provide work with composite.
 *
 * @author Kirill Tolkun
 */
public abstract class TextComponent implements Visitable {

    /**
     * Type of text component.
     */
    protected TextComponentType componentType;

    /**
     * Sort strategy of text component.
     */
    protected SortTextStrategy sortStrategy;

    /**
     * Add test component as child.
     *
     * @param textComponent to add as child
     */
    public abstract void add(TextComponent textComponent);

    /**
     * Get child component with index {@code i}.
     *
     * @param i index of child component to get
     * @return child component
     */
    public abstract TextComponent getChild(int i);

    /**
     * Get size of child components.
     *
     * @return number of child components
     */
    public abstract long size();

    /**
     * Remove text component.
     *
     * @param component to remove
     */
    public abstract void remove(TextComponent component);

    /**
     * Get type of text component.
     *
     * @return type of the component
     */
    public TextComponentType getType() {
        return componentType;
    }

    /**
     * Set sort strategy of text component.
     *
     * @param inputSortStrategy the sort strategy of component
     */
    public void setSortStrategy(final SortTextStrategy inputSortStrategy) {
        sortStrategy = inputSortStrategy;
    }

    /**
     * Clone text component.
     *
     * @return clone of component
     */
    public abstract TextComponent clone();

    /**
     * Sort text component according to {@code SortStrategy}.
     *
     * @throws UnsupportedMethodException if sortStrategy == null
     * @throws WrongArgumentException     if this component hasn't child
     *                                    components for sorting
     */
    public void sort()
            throws UnsupportedMethodException, WrongArgumentException {
        if (sortStrategy == null) {
            throw new UnsupportedMethodException("No supported sort strategy.");
        }
        sortStrategy.sort(this);
    }

    /**
     * Initialize {@code Visitor}.
     *
     * @param visitor to traversal this and child components
     */
    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Returns a {@code String} object representing this {@code TextComponent}.
     *
     * @return string representation of object.
     */
    @Override
    public abstract String toString();

    /**
     * Collect text composite and calculate bit expressions.
     *
     * @return string representation of collected text composite
     */
    public abstract String collect();
}
