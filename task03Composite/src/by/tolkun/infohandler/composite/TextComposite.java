package by.tolkun.infohandler.composite;

import by.tolkun.infohandler.exception.WrongArgumentException;
import by.tolkun.infohandler.interpreter.Interpreter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Composite class of text to store and work with different text components.
 *
 * @author Kirill Tolkun
 */
public class TextComposite extends TextComponent {

    /**
     * List of text components.
     */
    private List<TextComponent> textComponents;

    /**
     * Logger of class {@code TextComposite}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TextComposite.class);


    /**
     * Constructor with parameters.
     *
     * @param inputComponentType the type of text component
     */
    public TextComposite(final TextComponentType inputComponentType) {
        textComponents = new ArrayList<>();
        componentType = inputComponentType;
        LOGGER.debug("TextComposite created.");
    }

    /**
     * Add text component add child to list of children.
     *
     * @param textComponent to add as child
     */
    @Override
    public void add(final TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    /**
     * Remove text component from list of children.
     *
     * @param component to remove
     */
    @Override
    public void remove(final TextComponent component) {
        textComponents.remove(component);
    }

    /**
     * Get child text component by index.
     *
     * @param i index of child component to get
     * @return child text component from list by index {@code i}
     */
    @Override
    public TextComponent getChild(final int i) {
        return textComponents.get(i);
    }

    /**
     * Get size of child components.
     *
     * @return number of child components
     */
    @Override
    public long size() {
        return textComponents.size();
    }

    /**
     * Get list of text components.
     *
     * @return list the composite's components
     */
    public List<TextComponent> getComponents() {
        return textComponents;
    }

    /**
     * Clone text composite.
     *
     * @return clone of composite
     */
    @Override
    public TextComponent clone() {
        TextComposite textComposite = new TextComposite(componentType);
        for (TextComponent textComponent : textComponents) {
            textComposite.add(textComponent.clone());
        }
        return textComposite;
    }

    /**
     * Returns a {@code String} object representing this {@code TextComponent}.
     *
     * @return string representation of {@code TextComposite}
     */
    @Override
    public String toString() {
        String result = "";
        switch (componentType) {
            case PARAGRAPH:
                result = textComponents
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" ", "\t",
                                "\n"));
                break;
            case SENTENCE:
                result = textComponents
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" "));
                break;
            default:
                result = textComponents
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(""));
        }
        return result;
    }

    /**
     * Collect text composite and calculate bit expressions.
     *
     * @return string representation of collected text composite
     */
    @Override
    public String collect() {
        String result = "";
        switch (componentType) {
            case PARAGRAPH:
                result = textComponents
                        .stream()
                        .map(TextComponent::collect)
                        .collect(Collectors.joining(" ", "\t",
                                "\n"));
                break;
            case SENTENCE:
                result = textComponents
                        .stream()
                        .map(TextComponent::collect)
                        .collect(Collectors.joining(" "));
                break;
            case EXPRESSION:
                String expression = textComponents
                        .stream()
                        .map(TextComponent::collect)
                        .collect(Collectors.joining());
                try {
                    Interpreter interpreter = new Interpreter(expression);
                    result = String.valueOf(interpreter.calculate());
                } catch (WrongArgumentException e) {
                    LOGGER.error(e);
                }
                break;
            default:
                result = textComponents
                        .stream()
                        .map(TextComponent::collect)
                        .collect(Collectors.joining(""));
        }
        return result;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code TextComposite} object that
     * contains the same text components is the same type.
     *
     * @param o the object to compare with.
     * @return {@code true} if the objects are the same;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        return componentType.equals(that.componentType)
                && Objects.equals(textComponents, that.textComponents);
    }

    /**
     * Returns a hash code for a {@code TextComposite}.
     *
     * @return a hash code value for a {@code TextComposite}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(textComponents);
    }
}
