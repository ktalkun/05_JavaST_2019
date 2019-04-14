package test.xmlparser.builder.jaxb;

import by.tolkun.xmlparser.builder.jaxb.MedicinesJAXBBuilder;
import by.tolkun.xmlparser.entity.medicine.Medicines;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import test.xmlparser.builder.TestMedicinesProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Test class {@code MedicinesJAXBBuilder}.
 *
 * @author Kirill Tolkun
 */
public class MedicinesJAXBBuilderTest {

    /**
     * Path of xml file to parse it.
     */
    private static final String XML_FILE_PATH = "data/test/";
    /**
     * Path of xsd file to validate xml file.
     */
    private static final String XSD_FILE_PATH = "data/medicines.xsd";
    /**
     * Builder to build {@code Medicines} from file.
     */
    private MedicinesJAXBBuilder medicinesJAXBBuilder;
    /**
     * Provider to create expected {@code Medicines}.
     */
    private TestMedicinesProvider testMedicinesProvider;

    /**
     * Default constructor.
     */
    public MedicinesJAXBBuilderTest() {
        medicinesJAXBBuilder = new MedicinesJAXBBuilder();
        testMedicinesProvider = new TestMedicinesProvider();
    }

    /**
     * Provide positive data for testing
     * {@code MedicinesJAXBBuilder::buildMedicines}.
     *
     * @return {@code Object[][]} of data to collate
     * @throws IOException   if file is not found
     * @throws SAXException  if xml file doesn't correspond to
     *                       xsd scheme
     * @throws IOException   if {@code xmlFilePath} of
     *                       {@code xsdFilePath} is invalid
     * @throws JAXBException if an error is encountered while
     *                       creating the JAXBSource or if either
     *                       of the parameters are null.
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData()
            throws IOException, JAXBException, SAXException {
        Medicines expectedMedicines1
                = testMedicinesProvider.createExpectedMedicines1();
        Medicines expectedMedicines2
                = testMedicinesProvider.createExpectedMedicines2();
        Medicines expectedMedicines3
                = testMedicinesProvider.createExpectedMedicines3();

        medicinesJAXBBuilder.buildMedicines(
                XML_FILE_PATH + "test1.xml",
                XSD_FILE_PATH
        );
        Medicines actualMedicines1 = medicinesJAXBBuilder.getMedicines();

        medicinesJAXBBuilder.buildMedicines(
                XML_FILE_PATH + "test2.xml",
                XSD_FILE_PATH
        );
        Medicines actualMedicines2 = medicinesJAXBBuilder.getMedicines();

        medicinesJAXBBuilder.buildMedicines(
                XML_FILE_PATH + "test3.xml",
                XSD_FILE_PATH
        );
        Medicines actualMedicines3 = medicinesJAXBBuilder.getMedicines();


        return new Object[][]{
                {actualMedicines1, expectedMedicines1},
                {actualMedicines2, expectedMedicines2},
                {actualMedicines3, expectedMedicines3},
        };
    }

    /**
     * Test positive script in method
     * {@code MedicinesJAXBBuilder::buildMedicines}.
     *
     * @param actual   {@code Medicines}
     * @param expected {@code Medicines}
     */
    @Test(description = "positive script",
            dataProvider = "positiveData")
    public void testBuildMedicines(
            final Medicines actual,
            final Medicines expected
    ) {
        Assert.assertEquals(actual, expected);
    }
}
