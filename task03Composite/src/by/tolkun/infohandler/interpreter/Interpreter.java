package by.tolkun.infohandler.interpreter;

import by.tolkun.infohandler.exception.WrongArgumentException;
import by.tolkun.infohandler.parser.PolishParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for interpreting (calculation) bit expression.
 *
 * @author Kirill Tolkun
 */
public class Interpreter {

    /**
     * List of terminals for calculating.
     */
    private List<AbstractBitExpression> listExpression;

    /**
     * Pattern of bit terminals.
     */
    private static final Pattern LEXEME_PATTERN
            = Pattern.compile("([+-]?\\d+)|(<{2}|>{2,3}|[~&\\|\\^])");

    /**
     * Logger of class {@code Interpreter}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Interpreter.class);

    /**
     * Constructor with parameters.
     *
     * @param infixExpression for calculating
     * @throws WrongArgumentException if infixExpression is invalid
     */
    public Interpreter(final String infixExpression)
            throws WrongArgumentException {
        listExpression = new ArrayList<>();
        initialize(infixExpression);
        LOGGER.debug("Interpreter created.");
    }


    /**
     * Initialize list of terminals from terminals string.
     *
     * @param infixExpression for converting to list of terminals
     * @throws WrongArgumentException if infixExpression is invalid
     */
    private void initialize(final String infixExpression)
            throws WrongArgumentException {
        PolishParser polishParser = new PolishParser();
        String postfixExpression = polishParser.parse(infixExpression);
        Matcher tokenMatcher = LEXEME_PATTERN.matcher(postfixExpression);
        while (tokenMatcher.find()) {
            if (tokenMatcher.group(1) != null) {
                int number = Integer.parseInt(tokenMatcher.group(1));
                listExpression.add(new TerminalNumber(number));
            } else {
                String notNumber = tokenMatcher.group(2);
                switch (notNumber) {
                    case "~":
                        listExpression.add(new NonTerminalNot());
                        break;
                    case ">>":
                        listExpression.add(new NonTerminalShiftRight());
                        break;
                    case ">>>":
                        listExpression.add(new NonTerminalShiftRightZero());
                        break;
                    case "<<":
                        listExpression.add(new NonTerminalShiftLeft());
                        break;
                    case "&":
                        listExpression.add(new NonTerminalAnd());
                        break;
                    case "^":
                        listExpression.add(new NonTerminalXor());
                        break;
                    case "|":
                        listExpression.add(new NonTerminalOr());
                        break;
                    default:
                        throw new WrongArgumentException("Invalid input data.");
                }
            }
        }
    }

    /**
     * Calculate bit expression.
     *
     * @return result of calculation bit expression
     * @throws WrongArgumentException if expression for calculating is invalid
     */
    public int calculate() throws WrongArgumentException {
        Context context = new Context();
        for (AbstractBitExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        if (context.size() != 1) {
            throw new WrongArgumentException("Invalid expression.");
        }
        return context.popValue();
    }
}
