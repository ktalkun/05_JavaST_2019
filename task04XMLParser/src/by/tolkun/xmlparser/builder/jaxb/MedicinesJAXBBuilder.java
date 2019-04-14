package by.tolkun.xmlparser.builder.jaxb;

import by.tolkun.xmlparser.builder.XmlMedicinesBuilder;
import by.tolkun.xmlparser.entity.medicine.Medicines;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class for parsing and building {@link Medicines} using xml file and JAXB.
 *
 * @author Kirill Tolkun
 */
public class MedicinesJAXBBuilder extends XmlMedicinesBuilder {

    /**
     * Logger of class {@code MedicineJAXBParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MedicinesJAXBBuilder.class);

    /**
     * Default constructor.
     */
    public MedicinesJAXBBuilder() {
        medicines = new Medicines();
        LOGGER.debug("MedicineJAXBParser created.");
    }

    /**
     * Validate and buildMedicines xml file into {@code Medicines}.
     *
     * @param xmlFilePath for parsing
     * @param xsdFilePath for validation
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws JAXBException                if an error is encountered while
     *                                      creating the JAXBSource or if either
     *                                      of the parameters are null.
     */
    @Override
    public void buildMedicines(
            final String xmlFilePath,
            final String xsdFilePath
    )
            throws SAXException, JAXBException {
        // Clear old medicines
        medicines = new Medicines();

        // Validation during the marshaling of xml file using xsd scheme.
        JAXBContext jaxbContext = JAXBContext.newInstance(Medicines.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File xmlFile = new File(xmlFilePath);
        medicines = (Medicines) unmarshaller.unmarshal(xmlFile);
    }
}
