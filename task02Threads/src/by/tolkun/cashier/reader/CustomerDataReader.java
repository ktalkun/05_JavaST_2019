package by.tolkun.cashier.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading text files.
 *
 * @author Kirill Tolkun
 */
public class CustomerDataReader {

    /**
     * Logger of class {@code CustomerDataReader}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(CustomerDataReader.class);

    /**
     * Default constructor.
     */
    public CustomerDataReader() {
        LOGGER.debug("CustomerDataReader created.");
    }

    /**
     * Read lines from file and group them.
     *
     * @param path of file for reading
     * @return {@code List<List<String>>} with groups of text lines
     */
    public List<List<String>> read(final String path) {
        List<String> textLines = new ArrayList<>();
        try {
            textLines = Files.readAllLines(Paths.get(path),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();
        for (String textLine : textLines) {
            if (textLine.length() == 0) {
                groups.add(new ArrayList<>(group));
                group.clear();
                continue;
            }
            group.add(textLine);
        }
        groups.add(group);
        LOGGER.debug("File read.");
        return groups;
    }
}
