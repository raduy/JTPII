package pl.agh.jtp.lab03;

/**
 * Created with IntelliJ IDEA.
 * User: raduy
 * Date: 13.04.13
 * Time: 11:53
 */
public interface Figure {
    Color getColor();

    double getArea();

    /**
     * Returns figure with the same color and two times bigger area.
     * @return
     */
    Figure getDoubledSizeFigure();

}
