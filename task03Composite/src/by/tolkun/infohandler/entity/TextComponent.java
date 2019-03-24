package by.tolkun.infohandler.entity;

public abstract class TextComponent {

    protected TextComponentType componentType;

    public abstract void add(TextComponent textComponent);

    public abstract TextComponent getChild(int i);

    public abstract void remove(TextComponent component);

    public TextComponentType getComponentType() {
        return componentType;
    }

    @Override
    public abstract String toString();
}