package lab06_homeTests.visitorTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab06_home.structure.people.Developer;
import pl.agh.jtp.lab06_home.structure.people.GroupManager;
import pl.agh.jtp.lab06_home.structure.people.Tester;
import pl.agh.jtp.lab06_home.visitor.EmployeeCountVisitor;

import static junit.framework.Assert.assertEquals;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeCountVisitorTest {
    private EmployeeCountVisitor instance;

    @Mock
    Developer developer;

    @Mock
    Tester tester;

    @Mock
    GroupManager groupManager;

    @Before
    public void setUp() {
        instance = new EmployeeCountVisitor();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void visitMethodTest() {
        instance.visit(developer);
        instance.visit(tester);
        instance.visit(groupManager);

        assertEquals(3, instance.getCountOfEmployee());
        assertEquals(1, instance.getCountOfDevelopers());
        assertEquals(1, instance.getCountOfTesters());
        assertEquals(1, instance.getCountOfManagers());
    }
}
