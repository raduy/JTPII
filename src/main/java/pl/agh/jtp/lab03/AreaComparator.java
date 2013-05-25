package pl.agh.jtp.lab03;

import java.util.Comparator;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */

public class AreaComparator implements Comparator<Figure> {

    public AreaComparator() {
    }

    public int compare(Figure f1, Figure f2) {
        double delta = f1.getArea() - f2.getArea();
        return (delta == 0) ? 0 : ((delta > 0) ? 1 : -1);
    }

    public boolean equals(Figure f1, Figure f2) {
        return f1.getArea() == f2.getArea();
    }
}
