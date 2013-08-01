package lab04Tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03.Circle;
import pl.agh.jtp.lab03.Figure;
import pl.agh.jtp.lab03.Rectangle;
import pl.agh.jtp.lab03.Triangle;
import pl.agh.jtp.lab_04.Function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static pl.agh.jtp.lab_04.RichCollections.filter;
import static pl.agh.jtp.lab_04.RichCollections.map;

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

        when(doubledSizeCircle1.getArea()).thenReturn(2.);
        when(doubledSizeCircle2.getArea()).thenReturn(4.);
        when(doubledSizeRectangle.getArea()).thenReturn(6.);
        when(doubledSizeTriangle.getArea()).thenReturn(8.);

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
}
