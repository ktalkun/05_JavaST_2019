package by.tolkun.infohandler.composite.strategy;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.exception.WrongArgumentException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

/**
 * Abstract class of sort strategy.
 *
 * @author Kirill Tolkun
 */
public abstract class SortTextStrategy {

    /**
     * Get list of sign components.
     *
     * @param currentComponent the current component
     * @param componentType    the type of sign component
     * @return list of sign components corresponding {@code componentType}
     */
    private List<TextComponent> getComponentsByType(
            final TextComponent currentComponent,
            final TextComponentType componentType) {
        Deque<TextComponent> traversalComponents = new ArrayDeque<>();
        List<TextComponent> resultComponents = new ArrayList<>();
        traversalComponents.push(currentComponent);
        // Go around and collect all the components with the specified type.
        while (!traversalComponents.isEmpty()) {
            TextComponent traversalComponent = traversalComponents.pop();
            List<TextComponent> listComponents
                    = ((TextComposite) traversalComponent).getComponents();
            for (TextComponent component : listComponents) {
                if (component.getType() == componentType) {
                    resultComponents.add(component);
                } else if (component.getType() != TextComponentType.SYMBOL) {
                    traversalComponents.push(component);
                }
            }
        }
        return resultComponents;
    }

    /**
     * Sort text components any type by any sign.
     *
     * @param currentComponent the current component
     * @param typeToSort       the type of components to sort
     * @param typeOfSign       the type of components that by sorting
     * @param function         the function for applying to list sign components
     *                         for comparing sort components
     * @throws WrongArgumentException if {@code currentComponent} hasn't child
     *                                components for sorting
     */
    protected void sort(
            final TextComponent currentComponent,
            final TextComponentType typeToSort,
            final TextComponentType typeOfSign,
            final Function<List<TextComponent>, Integer> function)
            throws WrongArgumentException {
        if (typeToSort == TextComponentType.SYMBOL
                || currentComponent.getType() == TextComponentType.SYMBOL) {
            throw new WrongArgumentException("Wrong TextComponentType.");
        }
        List<TextComponent> componentsToSort = new ArrayList<>();
        Deque<TextComponent> componentsToTraversal = new ArrayDeque<>();
        componentsToTraversal.push(currentComponent);
        // Go around "currentComponent" and collect all the components
        // which contain components of "typeToSort".
        while (!componentsToTraversal.isEmpty()
                && componentsToTraversal.peek().getType()
                != TextComponentType.SYMBOL) {
            TextComponent componentToTraversal = componentsToTraversal.pop();
            List<TextComponent> listComponents
                    = ((TextComposite) componentToTraversal).getComponents();
            if (listComponents
                    .stream()
                    .anyMatch(textComponent ->
                            textComponent.getType() == typeToSort)
            ) {
                componentsToSort.add(componentToTraversal);
            } else {
                componentsToTraversal.addAll(listComponents);
            }
        }

        // Sort component lists of collected components.
        for (TextComponent textComponent : componentsToSort) {
            ((TextComposite) textComponent)
                    .getComponents()
                    .sort((final TextComponent component1,
                           final TextComponent component2) -> {
                                List<TextComponent> signList1
                                        = getComponentsByType(
                                        component1,
                                        typeOfSign
                                );
                                List<TextComponent> signList2
                                        = getComponentsByType(
                                        component2,
                                        typeOfSign
                                );
                                return Integer.compare(
                                        function.apply(signList1),
                                        function.apply(signList2)
                                );
                            }
                    );
        }
    }

    /**
     * Sort text components.
     *
     * @param currentTextComponent the component for sorting
     * @throws WrongArgumentException if {@code thisComponent} hasn't child
     *                                components for sorting
     */
    public abstract void sort(TextComponent currentTextComponent)
            throws WrongArgumentException;
}
