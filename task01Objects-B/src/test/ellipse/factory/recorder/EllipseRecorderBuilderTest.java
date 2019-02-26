package test.ellipse.factory.recorder;

import by.tolkun.ellipse.action.EllipseAction;
import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.factory.geometry.EllipseFactory;
import by.tolkun.ellipse.factory.recorder.EllipseRecorderBuilder;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code EllipseRecorderBuilder}.
 *
 * @author Kirill Tolkun
 */
public class EllipseRecorderBuilderTest {
    /**
     * Provide positive data for testing
     * {@code EllipseRecorderBuilder::setEllipse}.
     *
     * @return {@code Object[][]} of data to collate
     */
    @DataProvider(name = "positiveDataEllipses")
    public Object[][] createPositiveDataEllipses() {
        return new Object[][]{
                {new double[]{0.0, 0.0, 4.0, 4.0}},
                {new double[]{2.5, 3.2, -6.6, -3.1}},
                {new double[]{-6.6, -3.1, 2.5, 3.2}},
                {new double[]{-3.0, -1.0, -1.0, -5.0}},
                {new double[]{-2.0, 3.0, 0.0, -3.0}},
                {new double[]{0.0, -3.0, -2.0, 3.0}}
        };
    }

    /**
     * Testing positive script in method
     * {@code EllipseRecorderBuilder::setEllipse}.
     *
     * @param coordinates of the rectangle points described near {@code Ellipse}
     * @throws WrongArgumentException if points not set {@code Ellipse}
     */
    @Test(description = "positive script",
            dataProvider = "positiveDataEllipses")
    public void testPositiveSetEllipse(final double[] coordinates)
            throws WrongArgumentException {
        EllipseFactory ellipseFactory = new EllipseFactory();
        Ellipse ellipse = (Ellipse) ellipseFactory.createFigure(coordinates);
        EllipseRecorderBuilder ellipseRecorderBuilder
                = new EllipseRecorderBuilder();
        ellipseRecorderBuilder.setEllipse(ellipse);
        EllipseRecorder ellipseRecorder = ellipseRecorderBuilder.build();
        double[] actual = new double[]{
                ellipseRecorder.getPerimeter(),
                ellipseRecorder.getSquare()
        };
        EllipseAction ellipseAction = new EllipseAction();
        double[] expected = new double[]{
                ellipseAction.calcPerimeter(ellipse),
                ellipseAction.calcSquare(ellipse)
        };
        Assert.assertEquals(actual, expected);
    }
}
