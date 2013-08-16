package lab06Tests;

import org.junit.Test;
import pl.agh.jtp.lab03.Circle;
import pl.agh.jtp.lab03.Color;
import pl.agh.jtp.lab06.PropertyBasedComparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PropertyBasedComparatorTest {

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
    public void shouldReturnTheSameResultAsNativeCompareToMethodForPrivateFields() {
        //given
        Person person1 = new Person("Aaron", 20);
        Person person2 = new Person("Zack", 21);

        //when
        PropertyBasedComparator comparator = new PropertyBasedComparator("age");
        int result = comparator.compare(person1, person2);

        //then
        assertEquals(result, Integer.valueOf(20).compareTo(21));
    }

    @Test
    public void shouldReturnTheSameResultAsNativeCompareToMethodForPublicFields() {
        //given
        Person person1 = new Person("Aaron", 20);
        Person person2 = new Person("Zack", 21);

        //when
        PropertyBasedComparator comparator = new PropertyBasedComparator("name");
        int result = comparator.compare(person1, person2);

        //then
        assertEquals(result, "Aaron".compareTo("Zack"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArgsHaveDifferentTypes() {
        //given
        Person person = new Person("", 3);              //arg one is a Person
        Circle circle = new Circle(20., Color.BLUE);    //arg two is a Circle

        //when
        PropertyBasedComparator comparator = new PropertyBasedComparator("sth");
        int result = comparator.compare(person, circle); //exception should be thrown in this line

        //then
        fail("Exception should been thrown above");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEWhenObjectsDontHaveGivenProperty(){
        //given
        Person person1 = new Person("Aaron", 20);
        Person person2 = new Person("Zack", 21);

        //when
        PropertyBasedComparator comparator = new PropertyBasedComparator("surname");
        int result = comparator.compare(person1, person2);

        //then
        fail("Exception should been thrown above");
    }
}
