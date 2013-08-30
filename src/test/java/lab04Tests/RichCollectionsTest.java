package lab04Tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03.*;
import pl.agh.jtp.lab04.Function;
import pl.agh.jtp.lab04.Predicate;
import pl.agh.jtp.lab03.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static pl.agh.jtp.lab04.RichCollections.filter;
import static pl.agh.jtp.lab04.RichCollections.map;
import static pl.agh.jtp.lab04.RichCollections.groupBy;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class RichCollectionsTest {
    private Collection<Figure> collection;

    @Mock
    private Circle circle1;

    @Mock
    private Circle doubledSizeCircle1;

    @Mock
    private Circle circle2;

    @Mock
    private Circle doubledSizeCircle2;

    @Mock
    private Rectangle rectangle;

    @Mock
    private Rectangle doubledSizeRectangle;

    @Mock
    private Triangle triangle;

    @Mock
    private Triangle doubledSizeTriangle;

    @Before
    public void setUp() {
        collection = new ArrayList<Figure>();
    }

    @Test
    public void filterShouldReturnEmptyCollectionWhenGivenCollectionIsEmpty() {
        //given
        collection = Collections.emptyList();

        //when
        Collection<Circle> filteredCollection = filter(collection, Circle.class);

        //then
        assertTrue(filteredCollection.isEmpty());
    }

    @Test
    public void filterShouldReturnCollectionWithOnlyCircles() {
        //given
        collection.add(circle1);
        collection.add(circle2);
        collection.add(rectangle);
        collection.add(triangle);


        //when
        Collection<Circle> filteredCollection = filter(collection, Circle.class);

        //then
        assertFalse(filteredCollection.isEmpty());
        assertFalse(filteredCollection.contains(rectangle));
        assertFalse(filteredCollection.contains(triangle));
        assertEquals(2, filteredCollection.size());
    }

    @Test
    public void filterShouldReturnEmptyCollectionWhenAnyElementIsRightClass() {
        //given
        collection.add(rectangle);
        collection.add(triangle);

        //when
        Collection<Circle> filteredCollection = filter(collection, Circle.class);

        //then
        assertTrue(filteredCollection.isEmpty());
    }

    @Test
    public void mapShouldReturnCollectionOfAreas() {
        //given
        collection.add(circle1);
        collection.add(circle2);
        collection.add(rectangle);
        collection.add(triangle);
        when(circle1.getArea()).thenReturn(1.);
        when(circle2.getArea()).thenReturn(2.);
        when(rectangle.getArea()).thenReturn(3.);
        when(triangle.getArea()).thenReturn(4.);

        //when
        Collection<Double> areas = map(collection, new Function<Figure, Double>() {
            @Override
            public Double apply(Figure arg) {
                return arg.getArea();
            }
        });

        //then
        assertEquals(4, areas.size());
        assertTrue(areas.contains(1.));
        assertTrue(areas.contains(2.));
        assertTrue(areas.contains(3.));
        assertTrue(areas.contains(4.));
    }

    @Test
    public void mapShouldReturnCollectionWithDoubledAreasFigures() {
        //given
        collection.add(circle1);
        collection.add(circle2);
        collection.add(rectangle);
        collection.add(triangle);
        when(circle1.getArea()).thenReturn(1.);
        when(circle2.getArea()).thenReturn(2.);
        when(rectangle.getArea()).thenReturn(3.);
        when(triangle.getArea()).thenReturn(4.);

        when(circle1.getDoubledSizeFigure()).thenReturn(doubledSizeCircle1);
        when(circle2.getDoubledSizeFigure()).thenReturn(doubledSizeCircle2);
        when(rectangle.getDoubledSizeFigure()).thenReturn(doubledSizeRectangle);
        when(triangle.getDoubledSizeFigure()).thenReturn(doubledSizeTriangle);

        //when
        Collection<Figure> doubledSizeCollection = map(collection, new Function<Figure, Figure>() {
            @Override
            public Figure apply(Figure arg) {
                return arg.getDoubledSizeFigure();
            }
        });

        //then
        assertEquals(4, doubledSizeCollection.size());
        assertTrue(doubledSizeCollection.contains(doubledSizeCircle1));
        assertTrue(doubledSizeCollection.contains(doubledSizeCircle2));
        assertTrue(doubledSizeCollection.contains(doubledSizeRectangle));
        assertTrue(doubledSizeCollection.contains(doubledSizeTriangle));
    }

    @Test
    public void filterByConditionShouldReturnEmptyCollectionWhenGivenIsEmpty() {
        //given
        collection = Collections.emptyList();

        //when
        Collection<Figure> filteredCollection = filter(collection, new Predicate<Figure>() {
            @Override
            public boolean apply(Figure element) {
                return element.getColor().equals(Color.BLUE);
            }
        });

        //then
        assertTrue(filteredCollection.isEmpty());
    }

    @Test
    public void mapByFunctionShouldNotMergeSameElements() {
        //given
        Integer integer1 = 6,
                integer2 = 6;

        Collection<Integer> intCollection = new ArrayList<Integer>();
        intCollection.add(integer1);
        intCollection.add(integer2);

        //when
         Collection<Double> mappedIntCollection = map(intCollection, new Function<Integer, Double>() {
             @Override
             public Double apply(Integer arg) {
                 return arg.doubleValue();
             }
         });

        //then
        assertEquals(2, mappedIntCollection.size());
    }
    @Test
    public void filterByRedColorShouldReturnOnlyRedFigures() {
        //given
        collection.add(circle1);
        collection.add(rectangle);
        collection.add(triangle);

        when(circle1.getColor()).thenReturn(Color.GREEN);
        when(rectangle.getColor()).thenReturn(Color.RED);
        when(triangle.getColor()).thenReturn(Color.BLUE);

        //when
        Collection<Figure> filteredCollection = filter(collection, new Predicate<Figure>() {
            @Override
            public boolean apply(Figure element) {
                return element.getColor().equals(Color.RED);
            }
        });

        //then
        assertEquals(1, filteredCollection.size());
        assertTrue(filteredCollection.contains(rectangle));
    }

    @Test
    public void groupByShouldCreateOneElementMapWhenAllGivenElementsHaveSameColor() {
        //given
        collection.add(circle1);
        collection.add(rectangle);
        collection.add(triangle);

        when(circle1.getColor()).thenReturn(Color.BLUE);
        when(rectangle.getColor()).thenReturn(Color.BLUE);
        when(triangle.getColor()).thenReturn(Color.BLUE);

        //when
        Map<Color, Collection<Figure>> byColorMap = groupBy(collection, new Function<Figure, Color>() {
            @Override
            public Color apply(Figure arg) {
                return arg.getColor();
            }
        });

        //then
        assertEquals(1, byColorMap.size());
        assertTrue(byColorMap.containsKey(Color.BLUE));
        assertTrue(byColorMap.get(Color.BLUE).contains(circle1));
        assertTrue(byColorMap.get(Color.BLUE).contains(rectangle));
        assertTrue(byColorMap.get(Color.BLUE).contains(triangle));
    }

    @Test
    public void groupByShouldMapGivenMapInOrderOfAreaInThreeEntryMap() {
        //given
        collection.add(circle1);
        collection.add(circle2);
        collection.add(rectangle);
        collection.add(triangle);

        when(circle1.getArea()).thenReturn(1.);
        when(circle2.getArea()).thenReturn(3.);   // same
        when(rectangle.getArea()).thenReturn(3.); // area
        when(triangle.getArea()).thenReturn(4.);

        //when
        Map<Double, Collection<Figure>> areaMap = groupBy(collection, new Function<Figure, Double>() {
            @Override
            public Double apply(Figure arg) {
                return arg.getArea();
            }
        });

        //then
        assertEquals(3, areaMap.size());
        assertTrue(areaMap.get(1.).contains(circle1));
        assertTrue(areaMap.get(3.).contains(rectangle));
        assertTrue(areaMap.get(3.).contains(circle2));
        assertTrue(areaMap.get(4.).contains(triangle));
    }

}
