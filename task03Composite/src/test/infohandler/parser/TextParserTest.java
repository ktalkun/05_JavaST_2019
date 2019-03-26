package test.infohandler.parser;

import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.exception.ParserException;
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
 * Test class {@code TextParser}.
 *
 * @author Kirill Tolkun
 */
public class TextParserTest {

    /**
     * Object of {@code DataReader} for testing methods.
     */
    private DataReader dataReader = new DataReader();

    /**
     * Object of {@code TextParser} for testing methods.
     */
    private Parser textParser = new TextParser(
            new ParagraphParser(
                    new SentenceParser(
                            new LexemeParser(
                                    new WordParser(null)
                            )
                    )
            )
    );

    /**
     * Provide positive data for testing {@code TextParser::parse}.
     *
     * @return {@code Object[][]} of data to collate
     * @throws IOException if file is not found
     * @throws WrongArgumentException if file is invalid
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData()
            throws IOException, WrongArgumentException {
        return new Object[][]{
                {
                        dataReader.read("data/test/test1.txt"),
                        String.join("\n", Files.readAllLines(
                                Paths.get("data/test/test1.txt"),
                                StandardCharsets.UTF_8)) + "\n"
                },
                {
                        dataReader.read("data/test/test2.txt"),
                        String.join("\n", Files.readAllLines(
                                Paths.get("data/test/test2.txt"),
                                StandardCharsets.UTF_8)) + "\n"
                },
                {
                        dataReader.read("data/test/test3.txt"),
                        String.join("\n", Files.readAllLines(
                                Paths.get("data/test/test3.txt"),
                                StandardCharsets.UTF_8)) + "\n"
                },
        };
    }


    /**
     * Test positive script in method {@code TextParser::parse}.
     *
     * @param actualString for validating
     * @param expected     list of text components
     * @throws ParserException if {@code actualString} is invalid to parse
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveParse(final String actualString,
                                  final String expected)
            throws ParserException {
        TextComposite text = new TextComposite(TextComponentType.TEXT);
        textParser.parse(actualString, text);
        String actual = text.toString();
        Assert.assertEquals(actual, expected);
    }
}
