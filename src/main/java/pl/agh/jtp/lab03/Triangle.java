package pl.agh.jtp.lab03;

public class Triangle extends AbstractFigure {
    private final double a;
    private final double h;

    public Triangle(double a, double h, Color color) {
        super(color);
        this.a = a;
        this.h = h;
    }

    public double getA() {
        return a;
    }

    public double getH() {
        return h;
    }

    public double getArea() {
        return a*h/2;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(getArea());
        result = 31 * result + (int)(temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getH());
        result = 31 * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Triangle of base: " + getA() + " height: " + getH() + " and color: " + getColor();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Triangle)) {
            return  false;
        }

        Triangle triangle = (Triangle) o;

        if(!getColor().equals((triangle.getColor()))) {
            return false;
        }
        if(Double.compare(triangle.getA(), getA()) != 0) {
            return false;
        }
        if(Double.compare(triangle.getH(), getH()) != 0) {
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
