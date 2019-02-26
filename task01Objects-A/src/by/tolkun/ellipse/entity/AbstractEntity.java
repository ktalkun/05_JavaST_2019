package by.tolkun.ellipse.entity;

/**
 * Class for making common field {@code id}.
 *
 * @author Kirill Tolkun
 */
public abstract class AbstractEntity {

    /**
     * Static field for counting objects and then setting {@code id} field.
     */
    private static int count = 0;
    /**
     * Final field for identification objects.
     */
    private final int id;

    /**
     * Default constructor for initializing field {@code id}.
     */
    public AbstractEntity() {
        id = ++count;
    }

    /**
     * @return {@code id} of point
     */
    public int getId() {
        return id;
    }
}
