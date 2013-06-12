package pl.agh.jtp.lab03;

import java.util.Comparator;
/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class DescendingAreaComparator implements Comparator<Figure> {

    public int compare(Figure f1, Figure f2) {
        return Double.compare(f2.getArea(), f1.getArea());
    }

}
