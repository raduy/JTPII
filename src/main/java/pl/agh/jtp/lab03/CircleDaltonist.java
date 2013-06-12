package pl.agh.jtp.lab03;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CircleDaltonist extends Circle {

    public CircleDaltonist(double r, Color color) {
        super(r, color);
    }

    @Override
    public int hashCode() {
        int result = getColor().getB();
        long temp;
        temp = Double.doubleToLongBits(getR());
        result = 31 * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof CircleDaltonist)) {
            return false;
        }

        CircleDaltonist cd = (CircleDaltonist) o;
        if(getColor().getB() != cd.getColor().getB()) {
            return false;
        }
        if(getArea() != cd.getArea()) {
            return false;
        }

        return true;
    }

}
