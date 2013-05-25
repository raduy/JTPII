package pl.agh.jtp.lab03;


/**
 * Created with IntelliJ IDEA.
 * User: raduy
 * Date: 13.04.13
 * Time: 11:56
 */
public class Rectangle extends AbstractFigure {
    private final double a;
    private final double b;


    public Rectangle(double a, double b, Color color) {
        super(color);
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getArea() {
        return a * b;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 47 * result + (int)Double.doubleToLongBits(getA());
        result = 47 * result + (int)Double.doubleToLongBits(getB());
        return result;
    }

    @Override
    public String toString() {
        return "Rectangle of width: " + a + " height: " + b + " and color: " + this.getColor();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!o.getClass().equals(this.getClass()))
            return false;

        Rectangle rectangle = (Rectangle) o;

        if(!rectangle.getColor().equals(this.getColor()))
            return false;

        if(rectangle.getA() != this.getA() || rectangle.getB() != this.getB())
            return false;

        return true;
    }
}
