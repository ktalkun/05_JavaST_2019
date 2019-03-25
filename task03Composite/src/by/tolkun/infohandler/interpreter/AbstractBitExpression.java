package by.tolkun.infohandler.interpreter;

/**
 * Abstract class interpretable bit expressions.
 *
 * @author Kirill Tolkun
 */
public interface AbstractBitExpression {

    /**
     * Interpret terminals and not terminals.
     *
     * @param context to cache numbers during the calculating
     */
    void interpret(Context context);
}
