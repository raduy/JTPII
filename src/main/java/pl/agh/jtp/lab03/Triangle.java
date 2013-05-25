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
        result = 47 * result + (int)Double.doubleToLongBits(getA());
        result = 47 * result + (int)Double.doubleToLongBits(getH());
        return result;
    }

    @Override
    public String toString() {
        return "Triangle of base: " + getA() + " height: " + getH() + " and color: " + getColor();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!this.getClass().equals(o.getClass()))
            return  false;

        Triangle triangle = (Triangle) o;

        if(!this.getColor().equals((triangle.getColor())))
            return false;
        return !(this.getA() != triangle.getA() || this.getH() != triangle.getH());

    }
}
