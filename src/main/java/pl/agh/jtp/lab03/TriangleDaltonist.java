package pl.agh.jtp.lab03;

public class TriangleDaltonist extends Triangle {

    public TriangleDaltonist(double a, double h, Color color) {
        super(a, h, color);
    }
    @Override
    public int hashCode() {
        int result = getColor().getB();
        result = 26 * result + (int)Double.doubleToLongBits(getA());
        result = 26 * result + (int)Double.doubleToLongBits(getH());
        return result;
    }
    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Triangle triangle = (Triangle) o;

        if(triangle.getColor().getB() != getColor().getB()) {
            return false;
        }
        if(triangle.getA() != getA() || triangle.getH() != getH()) {
            return false;
        }

        return true;
    }

}

