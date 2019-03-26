package test.infohandler.parser;

import by.tolkun.infohandler.composite.Symbol;
import by.tolkun.infohandler.composite.TextComponent;
import by.tolkun.infohandler.composite.TextComponentType;
import by.tolkun.infohandler.composite.TextComposite;
import by.tolkun.infohandler.parser.WordParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Test class {@code WordParser}.
 *
 * @author Kirill Tolkun
 */
public class WordParserTest {
    /**
     * Object of {@code WordParser} for testing methods.
     */
    private WordParser wordParser = new WordParser(null);

    /**
     * Provide positive data for testing {@code WordParser::parse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        return new Object[][]{
                {"established", "established".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"that", "that".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"reader", "reader".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"readable", "readable".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"page!", "page!".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
        };
    }


    /**
     * Test positive script in method {@code WordParser::parse}.
     *
     * @param actualString for validating
     * @param expected     list of text components
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testPositiveParse(final String actualString,
                                  final List<String> expected) {
        TextComposite word = new TextComposite(TextComponentType.WORD);
        wordParser.parse(actualString, word);
        List<TextComponent> actual = word.getComponents();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Provide negative data for testing {@code WordParser::parse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData() {
        return new Object[][]{
                {"when", "".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"layout", "louyat".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"point", "poin".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"unchanged", "Unchanged".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
                {"has!", "has!!".chars()
                        .mapToObj(Symbol::new)
                        .collect(Collectors.toList())},
        };
    }

    /**
     * Test negative script in method {@code WordParser::parse}.
     *
     * @param actualString for validating
     * @param expected     list of text components
     */
    @Test(description = "negative script",
            dataProvider = "negativeData")
    public void testNegativeParse(final String actualString,
                                  final List<String> expected) {
        TextComposite word = new TextComposite(TextComponentType.WORD);
        wordParser.parse(actualString, word);
        List<TextComponent> actual = word.getComponents();
        Assert.assertNotEquals(actual, expected);
    }
}
