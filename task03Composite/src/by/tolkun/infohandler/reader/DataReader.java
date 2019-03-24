package by.tolkun.infohandler.reader;

import by.tolkun.infohandler.exception.WrongArgumentException;
import by.tolkun.infohandler.validator.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

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
    public String read(final String path) throws WrongArgumentException {
        FileValidator fileValidator = new FileValidator();
        if (!fileValidator.isValid(path)) {
            throw new WrongArgumentException("Invalid file.");
        }

        String result = "";
        try {
            result = Files.readAllLines(Paths.get(path),
                    StandardCharsets.UTF_8).stream().collect(Collectors.joining());
        } catch (IOException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("File read.");
        return result;
    }
}
