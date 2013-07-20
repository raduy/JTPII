package lab03_homeTests.visitorTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03_home.Developer;
import pl.agh.jtp.lab03_home.GroupManager;
import pl.agh.jtp.lab03_home.Tester;
import pl.agh.jtp.lab03_home.visitor.EmployeeCountVisitor;

import static junit.framework.Assert.assertEquals;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeCountVisitorTest {
    private EmployeeCountVisitor visitor;

    @Mock
    Developer developer;

    @Mock
    Tester tester;

    @Mock
    GroupManager groupManager;

    @Before
    public void setUp() {
        visitor = new EmployeeCountVisitor();
    }

    @After
    public void tearDown() {
        visitor = null;
    }

    @Test
    public void visitMethodTest() {
        visitor.visit(developer);
        visitor.visit(tester);
        visitor.visit(groupManager);

        assertEquals(3, visitor.getCountOfEmployee());

    }
}
