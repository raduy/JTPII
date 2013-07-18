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

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupManagerTest {
    private GroupManager groupManager;

    @Mock
    HireStrategy hireStrategy;

    @Mock
    IEmployee employee;

    @Mock
    Visitor visitor;

    @Before
    public void setUp() {
        groupManager = new GroupManager("John", "GM", 10, hireStrategy);
    }

    @After
    public void tearDown() {
        groupManager = null;
    }

    @Test
    public void isCanHireMethodDelegatedToHireStrategy() {
        groupManager.canHire(employee);

        verify(hireStrategy, times(1)).canHire(groupManager);
    }

    @Test
    public void hireTest() {
        when(hireStrategy.canHire(groupManager)).thenReturn(true);
        groupManager.hire(employee);

        assertTrue(groupManager.iterator().hasNext());
    }

    @Test
    public void acceptMethodTest() {
        when(hireStrategy.canHire(groupManager)).thenReturn(true);
        groupManager.hire(employee);
        groupManager.accept(visitor);


        InOrder inOrder = inOrder(visitor);
        inOrder.verify(visitor).visit(groupManager);
        inOrder.verify(visitor, times(1)).goLevelDown();
        verify(employee).accept(visitor);
        inOrder.verify(visitor, times(1)).goLevelUp();
    }





}
