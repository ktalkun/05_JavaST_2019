package by.tolkun.xmlparser.main;

import by.tolkun.xmlparser.builder.dom.MedicinesDOMBuilder;
import by.tolkun.xmlparser.builder.jaxb.MedicinesJAXBBuilder;
import by.tolkun.xmlparser.builder.sax.MedicinesSAXBuilder;
import by.tolkun.xmlparser.builder.stax.MedicinesStAXBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Class to parse using different parsers and print medicines to console.
 *
 * @author Kirill Tolkun
 */
final class Main {

    /**
     * Logger of class {@code Main}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Main.class);

    /**
     * Path of xml file to parse it.
     */
    private static final String XML_FILE_PATH = "data/medicines.xml";

    /**
     * Path of xsd file to validate xml file.
     */
    private static final String XSD_FILE_PATH = "data/medicines.xsd";

    /**
     * Default private constructor.
     */
    private Main() {
    }

    /**
     * Validate and buildMedicines xml file into {@code Medicines}.
     *
     * @param args of command line
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws IOException                  if {@code xmlFilePath} of
     *                                      {@code xsdFilePath} is invalid
     * @throws ParserConfigurationException if sax builder can not be created
     * @throws XMLStreamException           used to report well-formedness
     *                                      errors as well as unexpected
     *                                      processing conditions.
     * @throws JAXBException                if an error is encountered while
     *                                      creating the JAXBSource or if either
     *                                      of the parameters are null.
     */
    public static void main(final String[] args)
            throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException, JAXBException {

        LOGGER.debug("Main method started.");

        MedicinesSAXBuilder medicinesSAXParser = new MedicinesSAXBuilder();
        medicinesSAXParser.buildMedicines(XML_FILE_PATH, XSD_FILE_PATH);
        medicinesSAXParser
                .getMedicines()
                .getMedicine()
                .forEach(System.out::println);
        System.out.println();

        MedicinesDOMBuilder medicinesDOMParser = new MedicinesDOMBuilder();
        medicinesDOMParser.buildMedicines(XML_FILE_PATH, XSD_FILE_PATH);
        medicinesDOMParser
                .getMedicines()
                .getMedicine()
                .forEach(System.out::println);
        System.out.println();

        MedicinesStAXBuilder medicinesStAXParser = new MedicinesStAXBuilder();
        medicinesStAXParser.buildMedicines(XML_FILE_PATH, XSD_FILE_PATH);
        medicinesStAXParser
                .getMedicines()
                .getMedicine()
                .forEach(System.out::println);
        System.out.println();

        MedicinesJAXBBuilder medicinesJAXBParser = new MedicinesJAXBBuilder();
        medicinesJAXBParser.buildMedicines(XML_FILE_PATH, XSD_FILE_PATH);
        medicinesJAXBParser
                .getMedicines()
                .getMedicine()
                .forEach(System.out::println);
    }
}
