package lab03_homeTests.visitorTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03_home.Developer;
import pl.agh.jtp.lab03_home.Tester;
import pl.agh.jtp.lab03_home.visitor.MediumSalaryCalcVisitor;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static org.mockito.Mockito.when;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class MediumSalaryCalcVisitorTest {
    private MediumSalaryCalcVisitor instance;

    //@Mock
    Developer developer1 = new Developer("dev", "Developer", BigDecimal.valueOf(5000));

    //@Mock
    Developer developer2 = new Developer("dg", "Developer", BigDecimal.valueOf(4000));

    @Mock
    Tester tester;

    @Before
    public void setUp() {
        instance = new MediumSalaryCalcVisitor("Developer");
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void visitMethodTest() { /*How to test it properly ? */

        instance.visit(developer1);
        instance.visit(developer2);
        assertEquals(BigDecimal.valueOf(4500), instance.getMediumSalary());
        /*//First employee with matched role
        when(developer1.getRole()).thenReturn(String.valueOf("Developer"));
        when(developer1.getSalary()).thenReturn(BigDecimal.valueOf(5000));
        instance.visit(developer1);

        assertNotSame(instance.getMediumSalary(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(5000), instance.getMediumSalary());

        //Second employee with matched role
        when(developer2.getRole()).thenReturn("Developer");
        when(developer2.getSalary()).thenReturn(BigDecimal.valueOf(7000));
        instance.visit(developer2);

        assertNotSame(instance.getMediumSalary(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(6000), instance.getMediumSalary());

        //Third employee with NOT matched role - should not cause any different in result
        when(tester.getRole()).thenReturn("Tester");
        when(tester.getSalary()).thenReturn(BigDecimal.valueOf(7000));
        instance.visit(tester);

        assertNotSame(instance.getMediumSalary(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(6000), instance.getMediumSalary());*/
    }
}
