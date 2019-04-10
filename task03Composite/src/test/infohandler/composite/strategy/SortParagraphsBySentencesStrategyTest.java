package test.infohandler.composite.strategy;

import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.composite.strategy.SortParagraphsBySentencesStrategy;
import by.tolkun.infohandler.exception.ParserException;
import by.tolkun.infohandler.exception.UnsupportedMethodException;
import by.tolkun.infohandler.exception.WrongArgumentException;
import by.tolkun.infohandler.parser.LexemeParser;
import by.tolkun.infohandler.parser.ParagraphParser;
import by.tolkun.infohandler.parser.Parser;
import by.tolkun.infohandler.parser.SentenceParser;
import by.tolkun.infohandler.parser.TextParser;
import by.tolkun.infohandler.parser.WordParser;
import by.tolkun.infohandler.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Test class {@code SortParagraphsBySentencesStrategy}.
 *
 * @author Kirill Tolkun
 */
public class SortParagraphsBySentencesStrategyTest {

    /**
     * Object of {@code TextComposite}.
     */
    private TextComposite textComposite;
    /**
     * Object of {@code DataReader} for testing methods.
     */
    private DataReader dataReader;

    /**
     * Object of {@code TextParser} for testing methods.
     */
    private Parser textParser;

    /**
     * Default constructor.
     */
    public SortParagraphsBySentencesStrategyTest() {
        textComposite = new TextComposite(TextComponentType.TEXT);
        textComposite.setSortStrategy(new SortParagraphsBySentencesStrategy());
        dataReader = new DataReader();
        textParser = new TextParser(
                new ParagraphParser(
                        new SentenceParser(
                                new LexemeParser(
                                        new WordParser(null)
                                )
                        )
                )
        );
    }

    /**
     * Provide positive data for testing
     * {@code SortParagraphsBySentencesStrategy::sort}.
     *
     * @return {@code Object[][]} of data to collate
     * @throws IOException            if file is not found
     * @throws WrongArgumentException if file is invalid
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData()
            throws IOException, WrongArgumentException {
        return new Object[][]{
                {
                        dataReader.read("data/test/test1.txt"),
                        String.join("\n", Files.readAllLines(
                                Paths.get(
                                        "data/test/test1SortParagraphs.txt"
                                ),
                                StandardCharsets.UTF_8)) + "\n"
                },
                {
                        dataReader.read("data/test/test2.txt"),
                        String.join("\n", Files.readAllLines(
                                Paths.get(
                                        "data/test/test2SortParagraphs.txt"
                                ),
                                StandardCharsets.UTF_8)) + "\n"
                },
                {
                        dataReader.read("data/test/test3.txt"),
                        String.join("\n", Files.readAllLines(
                                Paths.get(
                                        "data/test/test3SortParagraphs.txt"
                                ),
                                StandardCharsets.UTF_8)) + "\n"
                },
        };
    }


    /**
     * Test positive script in method
     * {@code SortParagraphsBySentencesStrategy::sort}.
     *
     * @param actualString text to parse as {@code String}
     * @param expected     sorted text as {@code String}
     * @throws ParserException            if {@code actualString} is invalid
     *                                    to parse
     * @throws UnsupportedMethodException if text composite hasn't sort strategy
     * @throws WrongArgumentException     the wrong argument exception
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveSort(
            final String actualString,
            final String expected
    ) throws ParserException, UnsupportedMethodException,
            WrongArgumentException {
        textParser.parse(actualString, textComposite);
        textComposite.sort();
        String actual = textComposite.toString();
        Assert.assertEquals(actual, expected);
    }
}
