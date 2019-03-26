package test.infohandler.composite.visitor.impl;

import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.composite.visitor.impl.LexemeVisitor;
import by.tolkun.infohandler.exception.ParserException;
import by.tolkun.infohandler.exception.WrongArgumentException;
import by.tolkun.infohandler.parser.LexemeParser;
import by.tolkun.infohandler.parser.ParagraphParser;
import by.tolkun.infohandler.parser.SentenceParser;
import by.tolkun.infohandler.parser.TextParser;
import by.tolkun.infohandler.parser.WordParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Test class {@code LexemeVisitor}.
 *
 * @author Kirill Tolkun
 */
public class LexemeVisitorTest {

    /**
     * Object of {@code LexemeVisitor} for testing methods.
     */
    private LexemeVisitor lexemeVisitor = new LexemeVisitor();

    /**
     * Object of {@code TextParser} for testing methods.
     */
    private TextParser textParser = new TextParser(
            new ParagraphParser(
                    new SentenceParser(
                            new LexemeParser(
                                    new WordParser(null)
                            )
                    )
            )
    );

    /**
     * Object of {@code LexemeParser} for testing methods.
     */
    private LexemeParser lexemeParser = new LexemeParser(
            new WordParser(null)
    );

    /**
     * Provide positive data for testing {@code LexemeVisitor::parse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {"centuries, but also the leap into 13<<2"},
                {"\t~6&9|(3&4) unchanged.\tIt was popularised"},
                {"\tusing (Content here), content here',\tmaking"},
        };
    }


    /**
     * Test positive script in method {@code LexemeVisitor::parse}.
     *
     * @param text to create text composite and use {@code LexemeVisitor}
     * @throws WrongArgumentException if {@code inputExpression} is invalid
     * @throws ParserException        {@code text} is invalid to parse
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveParse(final String text)
            throws WrongArgumentException, ParserException {
        TextComposite textComponent = new TextComposite(TextComponentType.TEXT);
        textParser.parse(text, textComponent);
        lexemeVisitor.clear();
        textComponent.accept(lexemeVisitor);
        List<TextComponent> actualListLexemes = lexemeVisitor.getLexemes();
        TextComponent[] actual = actualListLexemes
                .toArray(new TextComponent[0]);
        List<TextComponent> expectedListLexemes = new ArrayList<>();
        StringTokenizer stringTokenizer
                = new StringTokenizer(text, " \t");
        while (stringTokenizer.hasMoreTokens()) {
            TextComponent lexeme = new TextComposite(TextComponentType.LEXEME);
            lexemeParser.parse(stringTokenizer.nextToken(), lexeme);
            expectedListLexemes.add(lexeme);
        }
        TextComponent[] expected = expectedListLexemes
                .toArray(new TextComponent[0]);
        Assert.assertEqualsNoOrder(actual, expected);
    }
}
