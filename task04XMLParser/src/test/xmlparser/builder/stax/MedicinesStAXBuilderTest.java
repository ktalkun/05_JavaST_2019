package test.xmlparser.builder.stax;

import by.tolkun.xmlparser.builder.stax.MedicinesStAXBuilder;
import by.tolkun.xmlparser.entity.medicine.Medicines;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import test.xmlparser.builder.TestMedicinesProvider;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * Test class {@code MedicinesStAXBuilder}.
 *
 * @author Kirill Tolkun
 */
public class MedicinesStAXBuilderTest {

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
    private MedicinesStAXBuilder medicinesStAXBuilder;
    /**
     * Provider to create expected {@code Medicines}.
     */
    private TestMedicinesProvider testMedicinesProvider;

    /**
     * Default constructor.
     */
    public MedicinesStAXBuilderTest() {
        medicinesStAXBuilder = new MedicinesStAXBuilder();
        testMedicinesProvider = new TestMedicinesProvider();
    }

    /**
     * Provide positive data for testing
     * {@code MedicinesStAXBuilder::buildMedicines}.
     *
     * @return {@code Object[][]} of data to collate
     * @throws IOException                  if file is not found
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws IOException                  if {@code xmlFilePath} of
     *                                      {@code xsdFilePath} is invalid
     * @throws XMLStreamException           used to report well-formedness
     *                                      errors as well as unexpected
     *                                      processing conditions.
     */
    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData()
            throws IOException, SAXException, XMLStreamException {
        Medicines expectedMedicines1
                = testMedicinesProvider.createExpectedMedicines1();
        Medicines expectedMedicines2
                = testMedicinesProvider.createExpectedMedicines2();
        Medicines expectedMedicines3
                = testMedicinesProvider.createExpectedMedicines3();

        medicinesStAXBuilder.buildMedicines(
                XML_FILE_PATH + "test1.xml",
                XSD_FILE_PATH
        );
        Medicines actualMedicines1 = medicinesStAXBuilder.getMedicines();

        medicinesStAXBuilder.buildMedicines(
                XML_FILE_PATH + "test2.xml",
                XSD_FILE_PATH
        );
        Medicines actualMedicines2 = medicinesStAXBuilder.getMedicines();

        medicinesStAXBuilder.buildMedicines(
                XML_FILE_PATH + "test3.xml",
                XSD_FILE_PATH
        );
        Medicines actualMedicines3 = medicinesStAXBuilder.getMedicines();


        return new Object[][]{
                {actualMedicines1, expectedMedicines1},
                {actualMedicines2, expectedMedicines2},
                {actualMedicines3, expectedMedicines3},
        };
    }


    /**
     * Test positive script in method
     * {@code MedicinesStAXBuilder::buildMedicines}.
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
