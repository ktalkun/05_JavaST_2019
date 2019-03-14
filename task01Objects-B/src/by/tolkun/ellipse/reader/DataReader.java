package by.tolkun.ellipse.reader;

import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.validator.FileValidator;
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
     * Default constructor.
     */
    public DataReader() {
        LOGGER.debug("DataReader created.");
    }

    /**
     * Read lines of file.
     *
     * @param path of file for reading
     * @return {@code List<String>} with lines of text file
     * @throws WrongArgumentException if file is invalid
     */
    public List<String> read(final String path) throws WrongArgumentException {
        FileValidator fileValidator = new FileValidator();
        if (!fileValidator.isValid(path)) {
            throw new WrongArgumentException("Invalid file.");
        }
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
