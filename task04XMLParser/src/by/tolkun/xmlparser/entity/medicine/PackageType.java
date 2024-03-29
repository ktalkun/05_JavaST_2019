//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2019.04.02 at 08:11:49 PM MSK
//


package by.tolkun.xmlparser.entity.medicine;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PackageType.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PackageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="bank"/>
 *     &lt;enumeration value="test_tube"/>
 *     &lt;enumeration value="vial"/>
 *     &lt;enumeration value="bottle"/>
 *     &lt;enumeration value="ampule"/>
 *     &lt;enumeration value="aerosol"/>
 *     &lt;enumeration value="box"/>
 *     &lt;enumeration value="tube"/>
 *     &lt;enumeration value="package"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 * @author Kirill Tolkun
 */
@XmlType(name = "PackageType")
@XmlEnum
public enum PackageType {

    /**
     * Type of bank.
     */
    @XmlEnumValue("bank")
    BANK("bank"),
    /**
     * Type of test tube.
     */
    @XmlEnumValue("test_tube")
    TEST_TUBE("test_tube"),
    /**
     * Type of vial.
     */
    @XmlEnumValue("vial")
    VIAL("vial"),
    /**
     * Type of bottle.
     */
    @XmlEnumValue("bottle")
    BOTTLE("bottle"),
    /**
     * Type of ampule.
     */
    @XmlEnumValue("ampule")
    AMPULE("ampule"),
    /**
     * Type of aerosol.
     */
    @XmlEnumValue("aerosol")
    AEROSOL("aerosol"),
    /**
     * Type of box.
     */
    @XmlEnumValue("box")
    BOX("box"),
    /**
     * Type of tube.
     */
    @XmlEnumValue("tube")
    TUBE("tube"),
    /**
     * Type of package.
     */
    @XmlEnumValue("package")
    PACKAGE("package");
    /**
     * Value of {@code PackageType}.
     */
    private final String value;

    /**
     * Constructor with parameters.
     *
     * @param v the string to create {@code PackageType}
     */
    PackageType(final String v) {
        value = v;
    }

    /**
     * Form {@code PackageType} from {@code v}.
     *
     * @param v the string to create enum
     * @return object of {@code PackageType}
     */
    public static PackageType fromValue(final String v) {
        for (PackageType c : PackageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    /**
     * Get value of enum.
     *
     * @return name of enum element
     */
    public String value() {
        return value;
    }

}
