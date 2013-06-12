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
        long temp;
        temp = Double.doubleToLongBits(a);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Rectangle of width: " + a + " height: " + b + " and color: " + this.getColor();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Rectangle)) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;

        if(Double.compare(rectangle.a, a) != 0) {
            return false;
        }
        if(Double.compare(rectangle.b, b) != 0) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(AbstractFigure abstractFigure) {
        Double temp = new Double(getArea());
        return temp.compareTo(abstractFigure.getArea());
    }
}
