package by.tolkun.ellipse.reader;

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
public class DataReader {

    /**
     * Logger of class {@code DataReader}.
     */
    public static final Logger LOGGER = LogManager.getLogger(DataReader.class);

    /**
     * Constructor with parameter of class {@code DataReader}.
     */
    public DataReader() {
        LOGGER.debug("DataReader created.");
    }

    /**
     * Read lines of file.
     *
     * @param path of file for reading
     * @return {@code List<String>} with lines of text file
     */
    public List<String> read(final String path) {
        List<String> textLines = new ArrayList<>();
        try {
            textLines = Files.readAllLines(Paths.get(path),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("File read.");
        return textLines;
    }
}
