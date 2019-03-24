package by.tolkun.infohandler.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Class for validation on the possibility of reading file.
 *
 * @author Kirill Tolkun
 */
public class FileValidator {

    /**
     * Logger of class {@code FileValidator}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FileValidator.class);

    /**
     * Default constructor.
     */
    public FileValidator() {
        LOGGER.debug("FileValidator created.");
    }

    /**
     * Validate file for reading.
     *
     * @param path of file
     * @return {@code true} if file is valid, {@code false} otherwise
     */
    public boolean isValid(final String path) {
        File file = new File(path);
        return file.isFile() && file.canRead();
    }
}
