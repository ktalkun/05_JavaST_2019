package by.tolkun.infohandler.interpreter;

import by.tolkun.infohandler.exception.WrongArgumentException;

import java.util.function.BinaryOperator;

/**
 * Abstract class of not terminal operator with two operands.
 *
 * @author Kirill Tolkun
 */
public abstract class BinaryNonTerminal implements AbstractBitExpression {
    /**
     * Function to execute calculating.
     */
    protected BinaryOperator<Integer> operator;

    /**
     * Interpret not terminal.
     *
     * @param context to cache numbers during the calculating
     * @throws WrongArgumentException if expression for calculating is invalid
     */
    @Override
    public void interpret(final Context context) throws WrongArgumentException {
        if (context.size() < 2) {
            throw new WrongArgumentException("Invalid expression.");
        }
        int rightOperand = context.popValue();
        int leftOperand = context.popValue();
        context.pushValue(operator.apply(leftOperand, rightOperand));
    }
}
