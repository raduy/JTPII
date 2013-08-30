package lab06_homeTests.visitorTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab06_home.domain.people.Developer;
import pl.agh.jtp.lab06_home.domain.people.Tester;
import pl.agh.jtp.lab06_home.visitor.MediumSalaryCalcVisitor;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class MediumSalaryCalcVisitorTest {
    private MediumSalaryCalcVisitor instance;

    @Mock
    Developer developer1;

    @Mock
    Developer developer2;

    @Mock
    Tester tester;

    @Before
    public void setUp() {
        instance = new MediumSalaryCalcVisitor("Developer");
    }

    @Test
    public void visitMethodTest() { /*How to test it properly ? */

        //First employee with matched role
        when(developer1.getRole()).thenReturn("Developer");
        when(developer1.getSalary()).thenReturn(BigDecimal.valueOf(5000));
        instance.visit(developer1);

        assertNotSame(instance.getMediumSalaryOfSpecifiedRole(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(5000), instance.getMediumSalaryOfSpecifiedRole());


        //Second employee with matched role
        when(developer2.getRole()).thenReturn("Developer");
        when(developer2.getSalary()).thenReturn(BigDecimal.valueOf(7000));
        instance.visit(developer2);

        assertNotSame(instance.getMediumSalaryOfSpecifiedRole(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(6000), instance.getMediumSalaryOfSpecifiedRole());

        //Third employee with NOT matched role - should not cause any different in result
        when(tester.getRole()).thenReturn("Tester");
        when(tester.getSalary()).thenReturn(BigDecimal.valueOf(7000));
        instance.visit(tester);

        assertNotSame(instance.getMediumSalaryOfSpecifiedRole(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(6000), instance.getMediumSalaryOfSpecifiedRole());
    }
}
