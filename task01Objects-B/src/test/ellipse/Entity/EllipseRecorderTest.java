package test.ellipse.entity;

import by.tolkun.ellipse.action.EllipseAction;
import by.tolkun.ellipse.entity.geometry.Ellipse;
import by.tolkun.ellipse.entity.geometry.ObservableEllipse;
import by.tolkun.ellipse.entity.geometry.Point2D;
import by.tolkun.ellipse.entity.recorder.EllipseRecorder;
import by.tolkun.ellipse.exception.WrongArgumentException;
import by.tolkun.ellipse.factory.geometry.EllipseFactory;
import by.tolkun.ellipse.factory.geometry.ObservableEllipseFactory;
import by.tolkun.ellipse.factory.geometry.Point2DFactory;
import by.tolkun.ellipse.factory.recorder.EllipseRecorderBuilder;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class {@code EllipseRecorder}.
 *
 * @author Kirill Tolkun
 */
public class EllipseRecorderTest {
    /**
     * Provide positive data for testing interaction between
     * {@code ObservableEllipse} or {@code Ellipse} and
     * {@code EllipseRecorder}.
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
     * Testing interaction {@code ObservableEllipse} and
     * {@code EllipseRecorder}.
     *
     * @param coordinates of rectangle point described near {@code Ellipse}
     * @throws WrongArgumentException if points not set {@code Ellipse}
     */
    @Test(description = "positive script",
            dataProvider = "positiveDataEllipses")
    public void testPositiveInteraction(final double[] coordinates)
            throws WrongArgumentException {
        ObservableEllipseFactory observableEllipseFactory
                = new ObservableEllipseFactory();
        ObservableEllipse observableEllipse
                = (ObservableEllipse) observableEllipseFactory
                .createFigure(coordinates);
        EllipseRecorderBuilder recorderBuilder = new EllipseRecorderBuilder();
        recorderBuilder.setEllipse(observableEllipse);
        EllipseRecorder ellipseRecorder = recorderBuilder.build();
        Point2DFactory point2DFactory = new Point2DFactory();
        final int coeff = 20;
        Point2D point2D = (Point2D) point2DFactory.createFigure(
                observableEllipse.getBeginPoint().getX()
                        - Math.random() * coeff,
                observableEllipse.getBeginPoint().getY()
                        + Math.random() * coeff
        );
        observableEllipse.setBeginPoint(point2D);
        EllipseAction ellipseAction = new EllipseAction();
        double[] actual = new double[]{
                ellipseAction.calcPerimeter(observableEllipse),
                ellipseAction.calcSquare(observableEllipse)
        };
        double[] expected = new double[]{
                ellipseRecorder.getPerimeter(),
                ellipseRecorder.getSquare()
        };
        Assert.assertEquals(actual, expected);
    }

    /**
     * Testing interaction {@code Ellipse} and
     * {@code EllipseRecorder}.
     *
     * @param coordinates of rectangle point described near {@code Ellipse}
     * @throws WrongArgumentException if points not set {@code Ellipse}
     */
    @Test(description = "positive script",
            dataProvider = "positiveDataEllipses")
    public void testNegativeInteraction(final double[] coordinates)
            throws WrongArgumentException {
        EllipseFactory ellipseFactory = new EllipseFactory();
        Ellipse ellipse = (Ellipse) ellipseFactory.createFigure(coordinates);
        EllipseRecorderBuilder recorderBuilder = new EllipseRecorderBuilder();
        recorderBuilder.setEllipse(ellipse);
        EllipseRecorder ellipseRecorder = recorderBuilder.build();
        Point2DFactory point2DFactory = new Point2DFactory();
        final int coeff = 20;
        Point2D point2D = (Point2D) point2DFactory.createFigure(
                ellipse.getBeginPoint().getX()
                        - Math.random() * coeff,
                ellipse.getBeginPoint().getY()
                        + Math.random() * coeff
        );
        ellipse.setBeginPoint(point2D);
        EllipseAction ellipseAction = new EllipseAction();
        double[] actual = new double[]{
                ellipseAction.calcPerimeter(ellipse),
                ellipseAction.calcSquare(ellipse)
        };
        double[] expected = new double[]{
                ellipseRecorder.getPerimeter(),
                ellipseRecorder.getSquare()
        };
        Assert.assertNotEquals(actual, expected);
    }
}
