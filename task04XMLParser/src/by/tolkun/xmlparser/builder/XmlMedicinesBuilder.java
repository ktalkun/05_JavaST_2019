package by.tolkun.xmlparser.builder;


import by.tolkun.xmlparser.entity.medicine.Medicines;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Interface for parsing xml data and creating {@code Medicines}.
 *
 * @author Kirill Tolkun
 */
public abstract class XmlMedicinesBuilder {

    /**
     * List of medicines.
     */
    protected Medicines medicines;

    /**
     * Default constructor.
     */
    public XmlMedicinesBuilder() {
        medicines = new Medicines();
    }

    /**
     * Get medicines.
     *
     * @return object of class {@link Medicines}
     */
    public Medicines getMedicines() {
        return medicines;
    }

    /**
     * Validate and buildMedicines xml file into {@code Medicines}.
     *
     * @param xmlFilePath for parsing
     * @param xsdFilePath for validation
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
    public abstract void buildMedicines(String xmlFilePath, String xsdFilePath)
            throws SAXException, ParserConfigurationException, IOException,
            XMLStreamException, JAXBException;
}
