package by.tolkun.ellipse.factory.geometry;

import by.tolkun.ellipse.entity.geometry.Geometry2D;
import by.tolkun.ellipse.exception.WrongArgumentException;

/**
 * Abstract class of factory for producing geometric figures in a square.
 *
 * @author Kirill Tolkun
 */
public abstract class Geometric2DFactory {

    /**
     * Abstract factory method for creating geometric figures in a square.
     *
     * @param inputData is string of information for creating object. It's not
     *                  parsed and validate, therefore these operations executes
     *                  in method {@code createFigure}
     * @return geometric figure in a square
     * @throws WrongArgumentException the wrong argument exception
     */
    public abstract Geometry2D createFigure(String inputData)
            throws WrongArgumentException;


    /**
     * Abstract factory method for creating geometric figure in a square.
     *
     * @param coordinates the coordinates
     * @return the geometric 2 d
     * @throws WrongArgumentException the wrong argument exception
     */
    public abstract Geometry2D createFigure(double... coordinates)
            throws WrongArgumentException;
}
