package lab06Tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03.Circle;
import pl.agh.jtp.lab03.Figure;
import pl.agh.jtp.lab03.Triangle;
import pl.agh.jtp.lab06.PropertyBasedComparator;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class PropertyBasedComparatorTest {

    @Mock
    Circle circle1;

    @Mock
    Circle circle2;

    @Mock
    Triangle triangle;

    class Person {
        public String name;
        private Integer age;

        Person(String name, Integer age) {
            this.age = age;
            this.name = name;
        }

        Integer getAge() {
            return age;
        }

        String getName() {
            return name;
        }
    }

    @Test
    public void shouldReturnTheSameResultAsNativeCompareToMethod() {
        //given
        when(circle1.getArea()).thenReturn(Double.valueOf(20));
        when(circle1.getArea()).thenReturn(Double.valueOf(10));

        //when
        Comparator<Circle> comparator = new PropertyBasedComparator<>("area");

        //then
        assertEquals(Double.compare(circle1.getArea(), circle2.getArea()),comparator.compare(circle1, circle2));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEWhenObjectsDontHaveGivenProperty(){
        //given
        Person person1 = new Person("Aaron", 20);
        Person person2 = new Person("Zack", 21);

        //when
        Comparator comparator = new PropertyBasedComparator("surname");
        int result = comparator.compare(person1, person2);

        //then
        fail();
    }

    @Test
    public void comparatorShouldWorksForDifferentClassesObject() throws Exception {
        //given
        when(circle1.getArea()).thenReturn(Double.valueOf(8));
        when(triangle.getArea()).thenReturn(Double.valueOf(14));

        //when
        Comparator<Figure> comparator = new PropertyBasedComparator<>("area");
        int result = comparator.compare(circle1, triangle);

        //then
        assertEquals(Double.compare(circle1.getArea(), triangle.getArea()), result);
    }
}
