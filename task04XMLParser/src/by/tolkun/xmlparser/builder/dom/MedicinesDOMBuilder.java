package by.tolkun.xmlparser.builder.dom;

import by.tolkun.xmlparser.builder.ObjectFactory;
import by.tolkun.xmlparser.builder.XmlMedicinesBuilder;
import by.tolkun.xmlparser.entity.medicine.Certificate;
import by.tolkun.xmlparser.entity.medicine.CertificateCompany;
import by.tolkun.xmlparser.entity.medicine.Company;
import by.tolkun.xmlparser.entity.medicine.Currency;
import by.tolkun.xmlparser.entity.medicine.Dosage;
import by.tolkun.xmlparser.entity.medicine.Group;
import by.tolkun.xmlparser.entity.medicine.Medicine;
import by.tolkun.xmlparser.entity.medicine.Medicines;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class for parsing and building {@link Medicines} using xml file and DOM.
 *
 * @author Kirill Tolkun
 */
public class MedicinesDOMBuilder extends XmlMedicinesBuilder {

    /**
     * Logger of class {@code MedicinesDOMParser}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MedicinesDOMBuilder.class);
    /**
     * Object of class {@link Medicines} to buildMedicines xml file.
     */
    private Medicines medicines;
    /**
     * Factory to create medicine objects.
     */
    private ObjectFactory objectFactory;

    /**
     * Default constructor.
     */
    public MedicinesDOMBuilder() {
        medicines = new Medicines();
        objectFactory = new ObjectFactory();
        LOGGER.debug("MedicinesDOMParser created.");
    }

    /**
     * Get child {@code Element}.
     *
     * @param parent    element of element with name {@code childName}
     * @param childName the name of element to find
     * @return child element of {@code parent} with name {@code childName}
     */
    private static Element getChild(final Element parent,
                                    final String childName) {
        NodeList nodeList =
                parent.getElementsByTagName(childName);
        return (Element) nodeList.item(0);
    }

    /**
     * Get value of child {@code Element}.
     *
     * @param parent    element of element with name {@code childName}
     * @param childName the name of element to find
     * @return value of child element of {@code parent}
     * with name {@code childName}
     */
    private static String getChildValue(final Element parent,
                                        final String childName) {
        Element child = getChild(parent, childName);
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }

    /**
     * Get value of attribute of child {@code Element}.
     *
     * @param parent        element of element with name {@code childName}
     * @param childName     the name of element to find
     * @param attributeName the name of attribute to find
     * @return attribute of of child element of {@code parent}
     * with name {@code childName}
     */
    private static String getChildAttribute(final Element parent,
                                            final String childName,
                                            final String attributeName) {
        Element child = getChild(parent, childName);
        return child.getAttribute(attributeName);
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
    public void buildMedicines(
            final String xmlFilePath,
            final String xsdFilePath
    ) throws SAXException, IOException, ParserConfigurationException {
        // Clear old medicines
        medicines = objectFactory.createMedicines();

        // Validation of xml file using xsd scheme.
        String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLang);
        File xsdFile = new File(xsdFilePath);
        Schema schema = schemaFactory.newSchema(xsdFile);
        Validator validator = schema.newValidator();
        DocumentBuilderFactory documentBuilderFactory
                = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder
                = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File(xmlFilePath));
        DOMSource domSource = new DOMSource(document);
        validator.validate(domSource);

        // Parsing xml file
        Element root = document.getDocumentElement();
        NodeList medicineNodes = root.getElementsByTagName("medicine");
        for (int i = 0; i < medicineNodes.getLength(); i++) {
            Element medicineNode = (Element) medicineNodes.item(i);
            Medicine medicine = parseMedicine(medicineNode);
            medicines.getMedicine().add(medicine);
        }
    }

    /**
     * Parse {@code Element} to {@code Medicine}.
     *
     * @param medicineElement for parsing
     * @return built object of {@code Medicine}
     */
    private Medicine parseMedicine(final Element medicineElement) {
        Medicine medicine = objectFactory.createMedicine();
        medicine.setVersions(objectFactory.createVersions());
        medicine.setName(getChildValue(medicineElement, "name"));
        medicine.setGroup(Group.fromValue(getChildValue(
                medicineElement,
                "group"
        )));
        medicine.getAnalogs().addAll(IntStream
                .range(0, medicineElement.getElementsByTagName("analogs").
                        getLength())
                .mapToObj(index -> medicineElement
                        .getElementsByTagName("analogs")
                        .item(index)
                        .getFirstChild()
                        .getNodeValue())
                .collect(Collectors.toList())
        );
        NodeList versionNodes
                = medicineElement.getElementsByTagName("version");
        for (int j = 0; j < versionNodes.getLength(); j++) {
            Element versionNode = (Element) versionNodes.item(j);
            Version version = parseVersion(versionNode);
            medicine.getVersions().getVersion().add(version);
        }
        return medicine;
    }

    /**
     * Parse {@code Element} to {@code Version}.
     *
     * @param versionElement for parsing
     * @return built object of {@code Medicine}
     */
    private Version parseVersion(final Element versionElement) {
        Version version = objectFactory.createVersion();
        version.setType(VersionType.fromValue(versionElement
                .getAttribute("type")
        ));
        NodeList companyNodes
                = versionElement.getElementsByTagName("company");
        for (int k = 0; k < companyNodes.getLength(); k++) {
            Element companyNode = (Element) companyNodes.item(k);
            Company company = parseCompany(companyNode);
            version.getCompanyOrCompanyCertificate().add(company);
        }
        NodeList companyCertificateNodes = versionElement
                .getElementsByTagName("company-certificate");
        for (int k = 0; k < companyCertificateNodes.getLength(); k++) {
            Element companyCertificateNode
                    = (Element) companyCertificateNodes.item(k);

            CertificateCompany certificateCompany
                    = parseCertificateCompany(companyCertificateNode);
            version.getCompanyOrCompanyCertificate()
                    .add(certificateCompany);
        }
        return version;
    }

    /**
     * Parse {@code Element} to {@code CertificateCompany}.
     *
     * @param certificateCompanyElement for parsing
     * @return built object of {@code Medicine}
     */
    private CertificateCompany parseCertificateCompany(
            final Element certificateCompanyElement
    ) {
        Company company = parseCompany(certificateCompanyElement);
        CertificateCompany certificateCompany
                = objectFactory.createCertificateCompany();
        certificateCompany.setName(company.getName());
        certificateCompany.setPackage(company.getPackage());
        certificateCompany.setDosage(company.getDosage());
        Element certificateElement = getChild(
                certificateCompanyElement,
                "certificate"
        );
        Certificate certificate = objectFactory.createCertificate();
        certificate.setNumber(certificateElement
                .getAttribute("number")
        );
        String[] dateIssueUnits = getChildValue(
                certificateElement,
                "date-issue"
        ).split("-");
        certificate.setDateIssue(new XMLGregorianCalendarImpl(
                new GregorianCalendar(
                        Integer.parseInt(dateIssueUnits[0]),
                        Integer.parseInt(dateIssueUnits[1]),
                        Integer.parseInt(dateIssueUnits[2])
                )
        ));
        String[] dateExpUnits = getChildValue(
                certificateElement,
                "date-expiration"
        ).split("-");
        certificate.setDateExpiration(new XMLGregorianCalendarImpl(
                new GregorianCalendar(
                        Integer.parseInt(dateExpUnits[0]),
                        Integer.parseInt(dateExpUnits[1]),
                        Integer.parseInt(dateExpUnits[2])
                )
        ));
        certificate.setRegOrganization(getChildValue(
                certificateElement,
                "reg-organization"
        ));
        certificateCompany.setCertificate(certificate);
        return certificateCompany;
    }

    /**
     * Parse {@code Element} to {@code Company}.
     *
     * @param companyElement for parsing
     * @return built object of {@code Medicine}
     */
    private Company parseCompany(final Element companyElement) {
        Company company = objectFactory.createCompany();

        company.setName(companyElement.getAttribute("name"));

        Element tmpPackageElement
                = getChild(companyElement, "package");
        Package tmpPackage = objectFactory.createPackage();
        tmpPackage.setType(PackageType.fromValue(tmpPackageElement
                .getAttribute("type")
        ));
        tmpPackage.setQuantity(Integer.parseInt(getChildValue(
                tmpPackageElement,
                "quantity"
        )));
        Price price = objectFactory.createPrice();
        String currency = getChildAttribute(
                tmpPackageElement,
                "price",
                "currency"
        );
        if (currency.length() != 0) {
            price.setCurrency(Currency.fromValue(currency));
        }
        price.setValue(Double.parseDouble(getChildValue(
                tmpPackageElement,
                "price"
        )));
        tmpPackage.setPrice(price);
        company.setPackage(tmpPackage);

        Element dosageElement
                = getChild(companyElement, "dosage");
        Dosage dosage = objectFactory.createDosage();
        dosage.setSize(Integer.parseInt(getChildValue(
                dosageElement,
                "size"
        )));
        Period period = objectFactory.createPeriod();
        String type = getChildAttribute(
                dosageElement,
                "period",
                "type"
        );
        if (type.length() != 0) {
            period.setType(PeriodType.fromValue(type));
        }
        period.setValue(Integer.parseInt(getChildValue(
                dosageElement,
                "period"
        )));
        dosage.setPeriod(period);
        company.setDosage(dosage);
        return company;
    }
}
