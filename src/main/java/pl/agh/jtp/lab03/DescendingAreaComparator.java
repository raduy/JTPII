package pl.agh.jtp.lab03;

import java.util.Comparator;
/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class DescendingAreaComparator implements Comparator<Figure> {

    public DescendingAreaComparator() {
    }

    public int compare(Figure f1, Figure f2) {
        double areaDiff = f2.getArea() - f1.getArea();
        if(areaDiff == 0D) {
            int hashDiff = f1.hashCode() - f2.hashCode();
            if(hashDiff == 0) {
                return 0;
            }
            else return (hashDiff > 0) ? 1 : -1;
        }
        else return (areaDiff > 0D) ? 1 : -1;

    }

}
