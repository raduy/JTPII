package lab03_homeTests.hireStrategyTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03_home.IEmployee;
import pl.agh.jtp.lab03_home.IManager;
import pl.agh.jtp.lab03_home.hireStrategy.BudgetBasedHireStrategy;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class BudgetBasedHireStrategyTest {
    private BudgetBasedHireStrategy instance;

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
        instance = new BudgetBasedHireStrategy(BigDecimal.valueOf(20000));
    }

    @Test
    public void canHireMethodTest() {
        //given
        /*partial mock*/
        List<IEmployee> employees = spy(new CopyOnWriteArrayList<IEmployee>());
        Iterator<IEmployee> employeeIterator = employees.iterator();

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        when(employees.get(0).getSalary()).thenReturn(BigDecimal.valueOf(10));
        when(employee1.getSalary()).thenReturn(BigDecimal.valueOf(12000));
        when(employee2.getSalary()).thenReturn(BigDecimal.valueOf(2000));
        when(employee3.getSalary()).thenReturn(BigDecimal.valueOf(10000));

        //when
        when(manager.iterator()).thenReturn(employeeIterator);

        //then
        assertTrue(instance.canHire(manager));

    }
}
