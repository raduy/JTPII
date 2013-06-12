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
        long temp;
        temp = Double.doubleToLongBits(getA());
        result =  31 * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getB());
        result =  31 * result + (int)(temp ^ (temp >>> 32));

        return result;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if (!(o instanceof RectangleDaltonist)) {
            return false;
        }

        Rectangle rect = (Rectangle) o;

        if(rect.getColor().getB() != getColor().getB()) {
            return false;
        }
        if (Double.compare(rect.getA(), getA()) != 0) {
            return false;
        }
        if (Double.compare(rect.getB(), getB()) != 0) {
            return false;
        }
        return true;
    }
}
