package pl.agh.jtp.lab03;

/**
 * Created with IntelliJ IDEA.
 * User: raduy
 * Date: 13.04.13
 * Time: 11:58
 */
abstract class AbstractFigure implements Figure, Comparable<AbstractFigure> {
    private final Color color;

    public AbstractFigure(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int hashCode() {
        return getColor().hashCode();
    }

}
