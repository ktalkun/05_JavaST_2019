//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2019.04.02 at 08:11:49 PM MSK
//


package by.tolkun.xmlparser.entity.medicine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for Company complex type.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
 * <pre>
 * &lt;complexType name="Company">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="package"
 *         type="{http://www.tolkun.by/xmlparser}Package"/>
 *         &lt;element name="dosage"
 *         type="{http://www.tolkun.by/xmlparser}Dosage"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required"
 *       type="{http://www.tolkun.by/xmlparser}Name" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 * @author Kirill Tolkun
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Company", propOrder = {
        "_package",
        "dosage"
})
@XmlSeeAlso({
        CertificateCompany.class
})
public class Company {

    /**
     * Logger of class {@code Company}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Company.class);
    /**
     * Package of {@code Medicine}.
     */
    @XmlElement(name = "package", required = true)
    private Package _package;
    /**
     * Dosage of {@code Medicine}.
     */
    @XmlElement(required = true)
    private Dosage dosage;
    /**
     * Name of {@code Company}.
     */
    @XmlAttribute(name = "name", required = true)
    private String name;

    /**
     * Default constructor.
     */
    public Company() {
        LOGGER.debug("Company created.");
    }

    /**
     * Gets the value of the package property.
     *
     * @return possible object is
     * {@link Package }
     */
    public Package getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     *
     * @param value allowed object is
     *              {@link Package }
     */
    public void setPackage(final Package value) {
        this._package = value;
    }

    /**
     * Gets the value of the dosage property.
     *
     * @return possible object is
     * {@link Dosage }
     */
    public Dosage getDosage() {
        return dosage;
    }

    /**
     * Sets the value of the dosage property.
     *
     * @param value allowed object is
     *              {@link Dosage }
     */
    public void setDosage(final Dosage value) {
        this.dosage = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Company} object that
     * contains the same field values.
     *
     * @param o the object to compare with.
     * @return {@code true} if the objects are the same;
     * {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        return Objects.equals(_package, company._package)
                && Objects.equals(dosage, company.dosage)
                && Objects.equals(name, company.name);
    }

    /**
     * Returns a hash code for a {@code Company}.
     *
     * @return a hash code value for a {@code Company}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(_package, dosage, name);
    }

    /**
     * Returns a {@code String} object representing this
     * {@code Company}.
     *
     * @return a string representation of the information of this
     * object
     */
    @Override
    public String toString() {
        return "Company{"
                + "_package=" + _package
                + ", dosage=" + dosage
                + ", name='" + name + '\''
                + '}';
    }
}
