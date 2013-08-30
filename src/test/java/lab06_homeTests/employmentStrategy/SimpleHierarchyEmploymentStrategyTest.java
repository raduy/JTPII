package lab06_homeTests.employmentStrategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab06_home.domain.Company;
import pl.agh.jtp.lab06_home.domain.IEmployee;
import pl.agh.jtp.lab06_home.domain.IManager;
import pl.agh.jtp.lab06_home.employmentstrategy.SimpleHierarchyEmploymentStrategy;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleHierarchyEmploymentStrategyTest {
    private SimpleHierarchyEmploymentStrategy instance;

    @Mock
    Company company;

    @Mock
    IManager manager;

    @Mock
    IManager ceo;

    @Mock
    IEmployee employee;

    @Before
    public void setUp() {
        instance = new SimpleHierarchyEmploymentStrategy();
    }

    @Test
    public void chooseManagerTestForManager() {
        when(company.getCEO()).thenReturn(ceo);
        when(ceo.canHire(manager)).thenReturn(true);

        assertEquals(ceo, instance.chooseManger(company, manager));
    }

    @Test
    public void chooseManagerTestForNotManger() {
        when(company.getCEO()).thenReturn(ceo);
        when(ceo.iterator()).thenReturn(new Iterator<IEmployee>() {
            boolean flag = true;
            @Override
            public boolean hasNext() {
                boolean result = flag;
                flag = false;
                return result;
            }

            @Override
            public IEmployee next() {
                return manager;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
        when(manager.canHire(employee)).thenReturn(true);

//        System.out.println((manager instanceof IManager) + "instance");

        assertEquals(manager, instance.chooseManger(company, employee));
    }

}
