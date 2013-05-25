package pl.agh.jtp.lab03;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class RectangleDaltonist extends Rectangle {

    public RectangleDaltonist(double a, double b, Color color) {
        super(a, b, color);
    }

    @Override
    public int hashCode() {
        int result = getColor().getB();
        result =  47 * result + (int)Double.doubleToLongBits(getA());
        result =  47 * result + (int)Double.doubleToLongBits(getB());

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }
        Rectangle rect = (Rectangle) o;
        if (rect.getColor().getB() != getColor().getB()) {
            return false;
        }
        if (rect.getA() != this.getA() || rect.getB() != this.getB()) {
            return false;
        }
        return true;
    }
}
