package lab06_homeTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab06_home.structure.people.Developer;
import pl.agh.jtp.lab06_home.structure.people.GroupManager;
import pl.agh.jtp.lab06_home.structure.IManager;
import pl.agh.jtp.lab06_home.hireStrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab06_home.visitor.Visitor;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class DeveloperTest {
    private Developer dev;


    @Mock
    Visitor visitor;

    @Before
    public void setUp() {
        dev = new Developer("Barry Boom", "Scala Dev");
    }

    @After
    public void tearDown() {
        dev = null;
    }

    @Test
    public void defaultSalaryTest() {
        assertTrue(dev.getSalary().equals(BigDecimal.valueOf(5000)));
    }

    @Test
    public void shouldReturnSetManager() {
        IManager manager = new GroupManager("Jo Jytr", "Manger", new TeamSizeHireStrategy(10));
        dev.setSupervisor(manager);

        assertTrue(dev.getSupervisor().equals(manager));
    }

    @Test
    public void setSalaryTest() {
        dev.setSalary(BigDecimal.valueOf(999));

        assertEquals(BigDecimal.valueOf(999),dev.getSalary());
    }

    @Test
    public void acceptTest() {
        dev.accept(visitor);

        verify(visitor, times(1)).visit(dev);
    }

}