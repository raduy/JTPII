package lab03_homeTests.visitorTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab03_home.IEmployee;
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
    private MediumSalaryCalcVisitor visitor;

    @Mock
    IEmployee employee1;

    @Mock
    IEmployee employee2;

    @Mock
    IEmployee employee3;

    @Before
    public void setUp() {
        visitor = new MediumSalaryCalcVisitor("Manager");
    }

    @After
    public void tearDown() {
        visitor = null;
    }

    @Test
    public void visitMethodTest() { /*How to test it properly ? */
        //First employee with matched role
        when(employee1.getRole()).thenReturn("Manager");
        when(employee1.getSalary()).thenReturn(BigDecimal.valueOf(5000));
        visitor.visit(employee1);

        assertNotSame(visitor.getMediumSalary(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(5000), visitor.getMediumSalary());

        //Second employee with matched role
        when(employee2.getRole()).thenReturn("Manager");
        when(employee2.getSalary()).thenReturn(BigDecimal.valueOf(7000));
        visitor.visit(employee2);

        assertNotSame(visitor.getMediumSalary(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(6000), visitor.getMediumSalary());

        //Third employee with NOT matched role - should not cause any different in result
        when(employee3.getRole()).thenReturn("Developer");
        when(employee3.getSalary()).thenReturn(BigDecimal.valueOf(7000));
        visitor.visit(employee3);

        assertNotSame(visitor.getMediumSalary(), BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(6000), visitor.getMediumSalary());
    }
}
