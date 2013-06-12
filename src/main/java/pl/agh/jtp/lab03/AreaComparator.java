package pl.agh.jtp.lab03;

import java.util.Comparator;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */

public class AreaComparator implements Comparator<Figure> {

    @Override
    public int compare(Figure f1, Figure f2) {
        return Double.compare(f1.getArea(), f2.getArea());
    }

}
