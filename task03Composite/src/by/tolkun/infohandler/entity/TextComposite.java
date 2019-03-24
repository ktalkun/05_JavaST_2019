package by.tolkun.infohandler.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComposite extends TextComponent {

    private List<TextComponent> textComponents;

    private static final Logger LOGGER
            = LogManager.getLogger(TextComposite.class);


    public TextComposite(final TextComponentType inputComponentType) {
        textComponents = new ArrayList<>();
        componentType = inputComponentType;
        LOGGER.debug("TextComposite created.");
    }

    public void add(final TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(final TextComponent component) {
        textComponents.remove(component);
    }

    @Override
    public TextComponent getChild(final int i) {
        return textComponents.get(i);
    }

    @Override
    public String toString() {
        String result = "";
        switch (componentType) {
            case PARAGRAPH:
                result = textComponents
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("", "\t",
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
}
