package by.tolkun.infohandler.interpreter;

import by.tolkun.infohandler.exception.WrongArgumentException;

import java.util.function.UnaryOperator;

/**
 * Abstract class of not terminal operator with one operand.
 *
 * @author Kirill Tolkun
 */
public abstract class UnaryNonTerminal implements AbstractBitExpression {
    /**
     * Function to execute calculating.
     */
    protected UnaryOperator<Integer> operator;

    /**
     * Interpret not terminal.
     *
     * @param context to cache numbers during the calculating
     * @throws WrongArgumentException if expression for calculating is invalid
     */
    @Override
    public void interpret(final Context context) throws WrongArgumentException {
        if (context.size() == 0) {
            throw new WrongArgumentException("Invalid expression.");
        }
        int rightOperand = context.popValue();
        context.pushValue(operator.apply(rightOperand));
    }
}
