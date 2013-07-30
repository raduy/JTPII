package lab03_homeTests;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.lab03_home.Predicate;
import pl.agh.jtp.lab03_home.PredicateIterator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author Michal Partyka
 */
public class PredicateIteratorTest {

    private PredicateIterator<Integer> instance;

    @Before
    public void setUp( ) {

    }

    @Test
    public void hasNextShouldReturnFalseIfNoElementsApplyToPredicate( ) {
        //given
        List<Integer> list = Arrays.asList(1, 4, 5, 2, 1, 4, 12, 1, 41, 12);
        instance = new PredicateIterator<Integer>(list.iterator(), new Predicate<Integer>() {
            @Override
            public boolean apply(Integer element) {
                return element > 100;
            }
        });

        //when
        boolean hasNext = instance.hasNext();

        //then
        assertEquals(false, hasNext);
    }

    @Test
    public void hasNextShouldReturnTrueIfSomeElementsApplyToPredicate( ) {
        //given
        List<Integer> list = Arrays.asList(1, 4, 5, 2, 1, 4, 12, 1, 41, 12);
        instance = new PredicateIterator<Integer>(list.iterator(), new Predicate<Integer>() {
            @Override
            public boolean apply(Integer element) {
                return element > 40;
            }
        });

        //when
        boolean hasNext = instance.hasNext();

        //then
        assertEquals(true, hasNext);
    }

    @Test
    public void hasNextShouldReturnFalseIfApplyReturnsTrueForNull() {
        //given
        List<Integer> list = Arrays.asList(1, 4, 5, 2, 1, 4, 12, 1, 41, 12);
        instance = new PredicateIterator<Integer>(list.iterator(), new Predicate<Integer>() {
            @Override
            public boolean apply(Integer element) {
                return element==null;
            }
        });

        //when
        boolean hasNext = instance.hasNext();

        //then
        assertEquals(false, hasNext);
    }
}
