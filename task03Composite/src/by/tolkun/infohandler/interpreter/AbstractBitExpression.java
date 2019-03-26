package by.tolkun.infohandler.interpreter;

import by.tolkun.infohandler.exception.WrongArgumentException;

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
     * @throws WrongArgumentException if expression for calculating is invalid
     */
    void interpret(Context context) throws WrongArgumentException;
}
