package lab03_homeTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03_home.GroupManager;
import pl.agh.jtp.lab03_home.IEmployee;
import pl.agh.jtp.lab03_home.hireStrategy.HireStrategy;
import pl.agh.jtp.lab03_home.visitor.Visitor;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupManagerTest {
    private GroupManager instance;

    @Mock
    HireStrategy hireStrategy;

    @Mock
    IEmployee employee;

    @Mock
    Visitor visitor;

    @Before
    public void setUp() {
        instance = new GroupManager("John", "GM", hireStrategy);
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void isCanHireMethodDelegatedToHireStrategy() {
        instance.canHire(employee);

        verify(hireStrategy, times(1)).canHire(instance);
    }

    @Test
    public void hireTest() {
        when(hireStrategy.canHire(instance)).thenReturn(true);
        instance.hire(employee);
        verify(employee).setSupervisor(instance);

        assertTrue(instance.iterator().hasNext());
    }

    @Test
    public void notHireTest() {
        when(hireStrategy.canHire(instance)).thenReturn(false);
        instance.hire(employee);
        verify(employee, times(0)).setSupervisor(instance);

        assertFalse(instance.iterator().hasNext());
    }

    @Test
    public void acceptMethodTest() {
        when(hireStrategy.canHire(instance)).thenReturn(true);
        instance.hire(employee);
        instance.accept(visitor);


        InOrder inOrder = inOrder(visitor);
        inOrder.verify(visitor).visit(instance);
        verify(employee).accept(visitor);
    }





}
