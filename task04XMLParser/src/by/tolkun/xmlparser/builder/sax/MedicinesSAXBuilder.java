package by.tolkun.xmlparser.builder.sax;

import by.tolkun.xmlparser.builder.XmlMedicinesBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class for parsing and building {@code Medicines} using xml file and SAX.
 *
 * @author Kirill Tolkun
 */
public class MedicinesSAXBuilder extends XmlMedicinesBuilder {

    /**
     * Logger of class {@code MedicinesSAXParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MedicinesSAXBuilder.class);
    /**
     * Object of class {@code Medicines} to buildMedicines xml file.
     */

    private MedicineHandler medicineHandler;

    /**
     * Default constructor.
     */
    public MedicinesSAXBuilder() {
        medicineHandler = new MedicineHandler();
        LOGGER.debug("MedicinesSAXParser created.");
    }

    /**
     * Validate and build medicines xml file into {@code Medicines}.
     *
     * @param xmlFilePath for parsing
     * @param xsdFilePath for validation
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws IOException                  if {@code xmlFilePath} of
     *                                      {@code xsdFilePath} is invalid
     * @throws ParserConfigurationException if sax builder can not be created
     */
    public void buildMedicines(
            final String xmlFilePath,
            final String xsdFilePath
    ) throws SAXException, IOException, ParserConfigurationException {

        // Validation of xml file using xsd scheme.
        String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLang);
        File xsdFile = new File(xsdFilePath);
        Schema schema = schemaFactory.newSchema(xsdFile);
        Validator validator = schema.newValidator();
        SAXSource saxSource = new SAXSource(new InputSource(
                new FileInputStream(xmlFilePath)));
        validator.validate(saxSource);

        // Parsing xml file
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new FileInputStream(xmlFilePath), medicineHandler);
        medicines.getMedicine().clear();
        medicines.getMedicine().addAll(
                medicineHandler
                        .getMedicines()
                        .getMedicine()
        );
        medicineHandler.clear();
    }
}
