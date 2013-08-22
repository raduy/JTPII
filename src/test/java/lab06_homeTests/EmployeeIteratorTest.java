package lab06_homeTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab06_home.hireStrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab06_home.structure.people.GroupManager;

import java.util.Iterator;


/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeIteratorTest {
    private Iterator employeeIterator;

    @Mock
    GroupManager manager;

    @Mock
    TeamSizeHireStrategy hireStrategy;

    @Before
    public void setUp() {
        employeeIterator = new GroupManager("Bob", "GM", hireStrategy).iterator();
    }

    @After
    public void tearDown() {
        manager = null;
        hireStrategy = null;
        employeeIterator = null;
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowNewUnsupportedOperationException() {

        employeeIterator.remove();
    }
}
