package lab03_homeTests.hireStrategyTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03_home.IEmployee;
import pl.agh.jtp.lab03_home.IManager;
import pl.agh.jtp.lab03_home.hireStrategy.TeamSizeHireStrategy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class TeamSizeHireStrategyTest {
    private TeamSizeHireStrategy hireStrategy;

    @Mock
    IManager manager;

    @Mock
    IEmployee employee1;

    @Mock
    IEmployee employee2;

    @Mock
    IEmployee employee3;

    @Before
    public void setUp() {
        hireStrategy = new TeamSizeHireStrategy(3);
    }

    @After
    public void tearDown() {
        hireStrategy = null;
    }

    @Test
    public void canHireTest() {

        /*partial mock*/
        List<IEmployee> employee = spy(new LinkedList<IEmployee>());
        employee.add(employee1);
        employee.add(employee2);
        employee.add(employee3);
        Iterator<IEmployee> iterator = employee.iterator();


        when(manager.iterator()).thenReturn(iterator);
        assertFalse(hireStrategy.canHire(manager));

        employee.remove(employee3);
        assertTrue(hireStrategy.canHire(manager));
    }
}
