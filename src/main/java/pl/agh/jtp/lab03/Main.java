package pl.agh.jtp.lab03;

import java.util.*;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Main {

    public static void main(String[] args) {

        List<Figure> list = new LinkedList<Figure>();
        list.add(new Circle(2, Color.BLUE));
        list.add(new Triangle(4D, 7D, Color.GREEN));
        list.add(new Rectangle(6D, 4D, Color.RED));

        System.out.println("Display content with foreach loop:");
        for (Figure figure : list) {
            System.out.println(figure);
        }

        System.out.println();
        System.out.println("Display content using iterator:");
        Iterator<Figure> iterator = list.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println();

        System.out.println("Sorting in view of area: ");
        Collections.sort(list, new AreaComparator());
        for(Figure figure : list) {
            System.out.printf("Figure with area: %.2f\n", figure.getArea());
        }

        System.out.println();

        System.out.println("Now I'm a daltonist!");
        Set<Figure> set = new HashSet<Figure>();
        set.add(new TriangleDaltonist(2D, 3D, Color.RED));
        set.add(new TriangleDaltonist(2D, 3D, Color.GREEN));
        set.add(new TriangleDaltonist(2D, 3D, Color.BLUE));

        set.add(new RectangleDaltonist(3., 1., Color.GREEN));
        set.add(new RectangleDaltonist(3., 1., Color.RED));
        set.add(new RectangleDaltonist(3., 1., Color.BLUE));

        set.add(new CircleDaltonist(6., Color.GREEN));
        set.add(new CircleDaltonist(6., Color.RED));
        set.add(new CircleDaltonist(6., Color.BLUE));

        for(Figure figure : set) {
            System.out.println(figure);
        }

        System.out.println();

        System.out.println("Set that stores items in insertion order: ");
        Set<Figure> sequenceSet = new LinkedHashSet<Figure>();
        sequenceSet.add(new Circle(3D, Color.BLUE));
        sequenceSet.add(new Triangle(5D, 6D, Color.GREEN));
        sequenceSet.add(new Rectangle(2D, 8D, Color.RED));

        for(Figure figure : sequenceSet) {
            System.out.println(figure);
        }

        System.out.println();

        System.out.println("Set that stores items in descending order: ");
        Set<Figure> descendingOrderSet = new TreeSet<Figure>(new DescendingAreaComparator());
        descendingOrderSet.add(new Circle(6D, Color.RED));
        descendingOrderSet.add(new Circle(4D, Color.BLUE));
        descendingOrderSet.add(new Rectangle(2D, 3D, Color.GREEN));
        descendingOrderSet.add(new Rectangle(4D, 4D, Color.GREEN));
        descendingOrderSet.add(new Triangle(3D, 1.4, Color.BLUE));

        for(Figure figure : descendingOrderSet) {
            System.out.printf("Figure with area: %.2f\n", figure.getArea());

        }
    }


}