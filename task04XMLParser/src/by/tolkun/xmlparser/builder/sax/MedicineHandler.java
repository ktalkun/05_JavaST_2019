package by.tolkun.xmlparser.builder.sax;

import by.tolkun.xmlparser.builder.ObjectFactory;
import by.tolkun.xmlparser.entity.medicine.Certificate;
import by.tolkun.xmlparser.entity.medicine.CertificateCompany;
import by.tolkun.xmlparser.entity.medicine.Company;
import by.tolkun.xmlparser.entity.medicine.Currency;
import by.tolkun.xmlparser.entity.medicine.Group;
import by.tolkun.xmlparser.entity.medicine.Medicine;
import by.tolkun.xmlparser.entity.medicine.Medicines;
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
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
import java.util.GregorianCalendar;

/**
 * Class to handle xml elements and form list of medicines.
 *
 * @author Kirill Tolkun
 */
public class MedicineHandler extends DefaultHandler {
    /**
     * Logger of class {@code MedicineHandler}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MedicinesSAXBuilder.class);
    /**
     * Object of class {@link Medicines} to buildMedicines xml file.
     */
    private Medicines medicines;
    /**
     * Current {@link Medicine} in process of parsing.
     */
    private Medicine currentMedicine;
    /**
     * Current {@link Version} in process of parsing.
     */
    private Version currentVersion;
    /**
     * Current {@link Company} in process of parsing.
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
    public MedicineHandler() {
        objectFactory = new ObjectFactory();
        medicines = objectFactory.createMedicines();
        currentMedicine = null;
        currentMedicinesEnum = null;
        currentVersion = null;
        currentCompany = null;
        LOGGER.debug("MedicineHandler created.");
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
     * Validate and buildMedicines xml file into {@link Medicines}.
     *
     * @param xmlFilePath for parsing
     * @param xsdFilePath for validation
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws IOException                  if {@code xmlFilePath} of
     *                                      {@code xsdFilePath} is invalid
     * @throws ParserConfigurationException if sax builder can not be created
     */
    public void parse(final String xmlFilePath, final String xsdFilePath)
            throws SAXException, IOException, ParserConfigurationException {
        // Clear old medicines
        medicines = objectFactory.createMedicines();

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
        saxParser.parse(new FileInputStream(xmlFilePath), this);
    }

    /**
     * Receive notification of the start of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     * @see org.xml.sax.ContentHandler#startElement
     */
    @Override
    public void startElement(final String uri,
                             final String localName,
                             final String qName,
                             final Attributes attributes) {
        switch (MedicinesEnum.valueOf(qName
                .replaceAll("-", "_")
                .toUpperCase())) {
            case MEDICINE:
                currentMedicine = objectFactory.createMedicine();
                break;
            case VERSIONS:
                currentMedicine.setVersions(objectFactory.createVersions());
                break;
            case VERSION:
                currentVersion = objectFactory.createVersion();
                currentVersion.setType(VersionType
                        .fromValue(attributes.getValue("type"))
                );
                break;
            case COMPANY:
                currentCompany = objectFactory.createCompany();
                currentCompany.setName(attributes.getValue("name"));
                break;
            case COMPANY_CERTIFICATE:
                currentCompany = objectFactory.createCertificateCompany();
                currentCompany.setName(attributes.getValue("name"));
                break;
            case PACKAGE:
                Package tmpPackage = objectFactory.createPackage();
                tmpPackage.setType(PackageType.fromValue(
                        attributes.getValue("type")
                ));
                currentCompany.setPackage(tmpPackage);
                break;
            case PRICE:
                Price price = objectFactory.createPrice();
                String currency = attributes.getValue("currency");
                if (currency != null) {
                    price.setCurrency(Currency.fromValue(currency));
                }
                currentCompany.getPackage().setPrice(price);
                break;
            case DOSAGE:
                currentCompany.setDosage(objectFactory.createDosage());
                break;
            case PERIOD:
                Period period = objectFactory.createPeriod();
                String type = attributes.getValue("type");
                if (type != null) {
                    period.setType(PeriodType.fromValue(type));
                }
                currentCompany.getDosage().setPeriod(period);
                break;
            case CERTIFICATE:
                Certificate certificate = objectFactory.createCertificate();
                certificate.setNumber(attributes.getValue("number"));
                ((CertificateCompany) currentCompany)
                        .setCertificate(certificate);
                ((CertificateCompany) currentCompany)
                        .setCertificate(certificate);
                break;
            default:
                break;
        }
        currentMedicinesEnum = MedicinesEnum.valueOf(qName
                .replaceAll("-", "_")
                .toUpperCase());
    }

    /**
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @see org.xml.sax.ContentHandler#characters
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
        String tagValue = new String(ch, start, length);
        if (currentMedicinesEnum == null) {
            return;
        }
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
     * Receive notification of the end of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * @see org.xml.sax.ContentHandler#endElement
     */
    @Override
    public void endElement(final String uri,
                           final String localName,
                           final String qName) {
        switch (MedicinesEnum.valueOf(qName
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

    /**
     * Clear list of medicines.
     */
    public void clear() {
        medicines.getMedicine().clear();
    }
}
