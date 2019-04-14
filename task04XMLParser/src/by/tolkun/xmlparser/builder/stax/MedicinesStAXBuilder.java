package by.tolkun.xmlparser.builder.stax;

import by.tolkun.xmlparser.builder.ObjectFactory;
import by.tolkun.xmlparser.builder.XmlMedicinesBuilder;
import by.tolkun.xmlparser.builder.sax.MedicinesSAXBuilder;
import by.tolkun.xmlparser.entity.medicine.Certificate;
import by.tolkun.xmlparser.entity.medicine.CertificateCompany;
import by.tolkun.xmlparser.entity.medicine.Company;
import by.tolkun.xmlparser.entity.medicine.Currency;
import by.tolkun.xmlparser.entity.medicine.Group;
import by.tolkun.xmlparser.entity.medicine.Medicine;
import by.tolkun.xmlparser.entity.medicine.MedicinesEnum;
import by.tolkun.xmlparser.entity.medicine.Package;
import by.tolkun.xmlparser.entity.medicine.PackageType;
import by.tolkun.xmlparser.entity.medicine.Period;
import by.tolkun.xmlparser.entity.medicine.PeriodType;
import by.tolkun.xmlparser.entity.medicine.Price;
import by.tolkun.xmlparser.entity.medicine.Version;
import by.tolkun.xmlparser.entity.medicine.VersionType;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Class for parsing and building {@code Medicines} using xml file and StAX.
 *
 * @author Kirill Tolkun
 */
public class MedicinesStAXBuilder extends XmlMedicinesBuilder {

    /**
     * Logger of class {@code MedicinesStAXParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MedicinesSAXBuilder.class);
    /**
     * Current {@code Medicine} in process of parsing.
     */
    private Medicine currentMedicine;
    /**
     * Current {@code Version} in process of parsing.
     */
    private Version currentVersion;
    /**
     * Current {@code Company} in process of parsing.
     */
    private Company currentCompany;
    /**
     * Current tag in process of parsing.
     */
    private MedicinesEnum currentMedicinesEnum;
    /**
     * Factory to create medicine objects.
     */
    private ObjectFactory objectFactory;

    /**
     * Default constructor.
     */
    public MedicinesStAXBuilder() {
        objectFactory = new ObjectFactory();
        currentMedicine = null;
        currentMedicinesEnum = null;
        currentVersion = null;
        currentCompany = null;
        LOGGER.debug("MedicinesStAXParser created.");
    }

    /**
     * @param xmlFilePath for parsing
     * @param xsdFilePath for validation
     * @throws SAXException       if xml file doesn't correspond to
     *                            xsd scheme
     * @throws IOException        if {@code xmlFilePath} of
     *                            {@code xsdFilePath} is invalid
     * @throws XMLStreamException used to report well-formedness
     *                            errors as well as unexpected
     *                            processing conditions.
     */
    public void buildMedicines(
            final String xmlFilePath,
            final String xsdFilePath
    )
            throws SAXException, IOException, XMLStreamException {
        // Clear old medicines
        medicines = objectFactory.createMedicines();
        // Validation of xml file using xsd scheme.
        String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLang);
        File xsdFile = new File(xsdFilePath);
        Schema schema = schemaFactory.newSchema(xsdFile);
        Validator validator = schema.newValidator();
        XMLStreamReader xmlStreamReaderValidate = XMLInputFactory
                .newInstance()
                .createXMLStreamReader(new FileInputStream(xmlFilePath));
        StAXSource staxSource = new StAXSource(xmlStreamReaderValidate);
        validator.validate(staxSource);

        // Parsing xml file
        XMLStreamReader xmlStreamReaderParse = XMLInputFactory
                .newInstance()
                .createXMLStreamReader(new FileInputStream(xmlFilePath));
        while (xmlStreamReaderParse.hasNext()) {
            int xmlElementType = xmlStreamReaderParse.next();
            String tagName;
            switch (xmlElementType) {
                case XMLStreamConstants.START_ELEMENT:
                    tagName = xmlStreamReaderParse.getLocalName();
                    parseStartElement(xmlStreamReaderParse, tagName);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String tagValue = xmlStreamReaderParse.getText();
                    if (currentMedicinesEnum == null) {
                        break;
                    }
                    parseCharacters(xmlStreamReaderParse, tagValue);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    tagName = xmlStreamReaderParse.getLocalName();
                    parseEndElement(tagName);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Parse start element of xml.
     *
     * @param xmlStreamReader the reader of xml file
     * @param tagName         the name of start element (tag)
     */
    private void parseStartElement(final XMLStreamReader xmlStreamReader,
                                   final String tagName) {
        switch (MedicinesEnum.valueOf(tagName
                .replaceAll("-", "_")
                .toUpperCase())) {
            case MEDICINE:
                currentMedicine = objectFactory.createMedicine();
                break;
            case VERSIONS:
                currentMedicine.setVersions(objectFactory
                        .createVersions()
                );
                break;
            case VERSION:
                currentVersion = objectFactory.createVersion();
                currentVersion.setType(VersionType
                        .fromValue(xmlStreamReader
                                .getAttributeValue(
                                        null,
                                        "type"
                                )
                        )
                );
                break;
            case COMPANY:
                currentCompany = objectFactory.createCompany();
                currentCompany.setName(xmlStreamReader
                        .getAttributeValue(
                                null,
                                "name"
                        )
                );
                break;
            case COMPANY_CERTIFICATE:
                currentCompany
                        = objectFactory.createCertificateCompany();
                currentCompany.setName(xmlStreamReader
                        .getAttributeValue(
                                null,
                                "name"
                        )
                );
                break;
            case PACKAGE:
                Package tmpPackage = objectFactory.createPackage();
                tmpPackage.setType(PackageType.fromValue(
                        xmlStreamReader.getAttributeValue(
                                null,
                                "type"
                        )
                ));
                currentCompany.setPackage(tmpPackage);
                break;
            case PRICE:
                Price price = objectFactory.createPrice();
                String currency = xmlStreamReader.getAttributeValue(
                        null,
                        "currency"
                );
                if (currency != null) {
                    price.setCurrency(Currency.fromValue(currency));
                }
                currentCompany.getPackage().setPrice(price);
                break;
            case DOSAGE:
                currentCompany
                        .setDosage(objectFactory.createDosage());
                break;
            case PERIOD:
                Period period = objectFactory.createPeriod();
                String type = xmlStreamReader.getAttributeValue(
                        null,
                        "type"
                );
                if (type != null) {
                    period.setType(PeriodType.fromValue(type));
                }
                currentCompany.getDosage().setPeriod(period);
                break;
            case CERTIFICATE:
                Certificate certificate
                        = objectFactory.createCertificate();
                certificate.setNumber(xmlStreamReader
                        .getAttributeValue(
                                null,
                                "number"
                        )
                );
                ((CertificateCompany) currentCompany)
                        .setCertificate(certificate);
                ((CertificateCompany) currentCompany)
                        .setCertificate(certificate);
                break;
            default:
                break;
        }
        currentMedicinesEnum = MedicinesEnum.valueOf(tagName
                .replaceAll("-", "_")
                .toUpperCase());
    }

    /**
     * Parse element's value of xml.
     *
     * @param xmlStreamReader the reader of xml file
     * @param tagValue        the value of tag
     */
    private void parseCharacters(final XMLStreamReader xmlStreamReader,
                                 final String tagValue) {
        switch (currentMedicinesEnum) {
            case NAME:
                currentMedicine.setName(tagValue);
                break;
            case GROUP:
                currentMedicine.setGroup(Group.fromValue(tagValue));
                break;
            case ANALOGS:
                currentMedicine.getAnalogs().add(tagValue);
                break;
            case QUANTITY:
                currentCompany
                        .getPackage()
                        .setQuantity(Integer.parseInt(tagValue));
                break;
            case PRICE:
                currentCompany
                        .getPackage()
                        .getPrice()
                        .setValue(Double.parseDouble(tagValue));
                break;
            case SIZE:
                currentCompany
                        .getDosage()
                        .setSize(Integer.parseInt(tagValue));
                break;
            case PERIOD:
                currentCompany
                        .getDosage()
                        .getPeriod()
                        .setValue(Integer.parseInt(tagValue));
                break;
            case DATE_ISSUE:
                String[] dateIssueUnits = tagValue.split("-");
                ((CertificateCompany) currentCompany)
                        .getCertificate()
                        .setDateIssue(new XMLGregorianCalendarImpl(
                                new GregorianCalendar(
                                        Integer.parseInt(dateIssueUnits[0]),
                                        Integer.parseInt(dateIssueUnits[1]),
                                        Integer.parseInt(dateIssueUnits[2])
                                )
                        ));
                break;

            case DATE_EXPIRATION:
                String[] dateExpUnits = tagValue.split("-");
                ((CertificateCompany) currentCompany)
                        .getCertificate()
                        .setDateExpiration(new XMLGregorianCalendarImpl(
                                new GregorianCalendar(
                                        Integer.parseInt(dateExpUnits[0]),
                                        Integer.parseInt(dateExpUnits[1]),
                                        Integer.parseInt(dateExpUnits[2])
                                )
                        ));
                break;
            case REG_ORGANIZATION:
                ((CertificateCompany) currentCompany)
                        .getCertificate()
                        .setRegOrganization(tagValue);
                break;
            default:
                break;
        }
    }

    /**
     * Parse end element of xml.
     *
     * @param tagName the name of end element (tag)
     */
    private void parseEndElement(final String tagName) {
        switch (MedicinesEnum.valueOf(tagName
                .replaceAll("-", "_")
                .toUpperCase())) {
            case MEDICINE:
                medicines
                        .getMedicine()
                        .add(currentMedicine);
                currentMedicine = null;
                break;
            case VERSION:
                currentMedicine
                        .getVersions()
                        .getVersion()
                        .add(currentVersion);
                currentVersion = null;
                break;
            case COMPANY:
                currentVersion
                        .getCompanyOrCompanyCertificate()
                        .add(currentCompany);
                currentCompany = null;
                break;
            case COMPANY_CERTIFICATE:
                currentVersion
                        .getCompanyOrCompanyCertificate()
                        .add(currentCompany);
                currentCompany = null;
                break;
            default:
                break;
        }
        currentMedicinesEnum = null;
    }
}

