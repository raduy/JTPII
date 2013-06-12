package pl.agh.jtp.lab03;

public class TriangleDaltonist extends Triangle {

    public TriangleDaltonist(double a, double h, Color color) {
        super(a, h, color);
    }

    @Override
    public int hashCode() {
        int result = getColor().getB();
        long temp;
        temp = Double.doubleToLongBits(getA());
        result = 31 * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getH());
        result = 31 * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof TriangleDaltonist)) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        if(triangle.getColor().getB() != getColor().getB()) {
            return false;
        }
        if(Double.compare(triangle.getH(), getH()) != 0) {
            return false;
        }
        if(Double.compare(triangle.getA(), getA()) != 0) {
            return false;
        }

        return true;
    }

}

