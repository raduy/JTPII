package pl.agh.jtp.lab03;


public class Circle extends AbstractFigure {
    private final double r;

    public Circle(double r, Color color) {
        super(color);
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public double getArea() {
        return Math.PI * r * r;
    }

    @Override
    public Figure getDoubledSizeFigure() {
        return new Circle(Math.sqrt(2)*r, getColor());
    }

    public Color getColor() {
        return getColor();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        Circle circle = (Circle) o;

        if(Double.compare(circle.r, r) != 0) {
            return false;
        }
        if(!getColor().equals(circle.getColor())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Circle of radius: " + getR() + " and color:  " + getColor();
    }

    @Override
    public int compareTo(AbstractFigure abstractFigure) {
        Double temp = getArea();
        return temp.compareTo(abstractFigure.getArea());
    }
}
