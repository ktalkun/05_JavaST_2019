package by.tolkun.infohandler.parser;

import by.tolkun.infohandler.exception.WrongArgumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class to parse infix expression to postfix (polish) expression.
 *
 * @author Kirill Tolkun
 */
public final class PolishParser {

    /**
     * Pattern to split into numbers (operands) and operators.
     */
    private static final Pattern LEXEME_PATTERN
            = Pattern.compile("([+-]?\\d+)|(<{2}|>{2,3}|[\\(\\)~&\\|\\^])");

    /**
     * Logger of class {@code PolishParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(PolishParser.class);

    /**
     * Default constructor.
     */
    public PolishParser() {
        LOGGER.debug("PolishParser created.");
    }

    /**
     * Parse infix expression to postfix (polish) expression.
     *
     * @param infixExpression to parsing (converting).
     * @return postfix expression
     * @throws WrongArgumentException if infixExpression is invalid.
     */
    public String parse(final String infixExpression)
            throws WrongArgumentException {
        Deque<String> postfixExpression = new ArrayDeque<>();
        Deque<String> nonTerminals = new ArrayDeque<>();
        Matcher tokenMatcher = LEXEME_PATTERN.matcher(infixExpression);
        while (tokenMatcher.find()) {
            if (tokenMatcher.group(1) != null) {
                postfixExpression.offer(tokenMatcher.group(1));
                postfixExpression.offer(" ");
            } else {
                String notNumber = tokenMatcher.group(2);
                switch (notNumber) {
                    case "(":
                        nonTerminals.push("(");
                        break;
                    case ")":
                        while (!"(".equals(nonTerminals.peek())) {
                            if (nonTerminals.isEmpty()) {
                                String message = "Wrong number of brackets.";
                                throw new WrongArgumentException(message);
                            }
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.pop();
                        break;
                    case "~":
                        while (!nonTerminals.isEmpty()
                                && !"(".equals(nonTerminals.peek())) {
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.push("~");
                        break;
                    case ">>":
                        while (!nonTerminals.isEmpty()
                                && "~".contains(nonTerminals.peek())) {
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.push(">>");
                        break;
                    case ">>>":
                        while (!nonTerminals.isEmpty()
                                && "~".contains(nonTerminals.peek())) {
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.push(">>>");
                        break;
                    case "<<":
                        while (!nonTerminals.isEmpty()
                                && "~".contains(nonTerminals.peek())) {
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.push("<<");
                        break;
                    case "&":
                        while (!nonTerminals.isEmpty()
                                && "~>><<>>>".contains(nonTerminals.peek())) {
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.push("&");
                        break;
                    case "^":
                        while (!nonTerminals.isEmpty()
                                && "~>><<>>>&".contains(nonTerminals.peek())) {
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.push("^");
                        break;
                    case "|":
                        while (!nonTerminals.isEmpty()
                                && "~>><<>>>&^".contains(nonTerminals.peek())) {
                            postfixExpression.offer(nonTerminals.pop());
                            postfixExpression.offer(" ");
                        }
                        nonTerminals.push("|");
                        break;
                    default:
                        throw new WrongArgumentException("Invalid input data.");
                }
            }
        }
        while (!nonTerminals.isEmpty()) {
            if ("()".contains(nonTerminals.peek())) {
                throw new WrongArgumentException("Wrong numbers of brackets.");
            }
            postfixExpression.offer(nonTerminals.pop());
        }
        return postfixExpression
                .stream()
                .collect(Collectors.joining(""));
    }
}
