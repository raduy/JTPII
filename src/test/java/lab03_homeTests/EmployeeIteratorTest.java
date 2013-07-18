package lab03_homeTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03_home.GroupManager;
import pl.agh.jtp.lab03_home.hireStrategy.TeamSizeHireStrategy;

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
        employeeIterator = new GroupManager("Bob", "GM", 10, hireStrategy).iterator();
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
