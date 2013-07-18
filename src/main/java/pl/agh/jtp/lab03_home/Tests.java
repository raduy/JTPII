package pl.agh.jtp.lab03_home;

import org.junit.*;
import pl.agh.jtp.lab03_home.hireStrategy.BudgetBasedHireStrategy;
import pl.agh.jtp.lab03_home.hireStrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab03_home.visitor.CompanyStructurePrintVisitor;
import pl.agh.jtp.lab03_home.visitor.EmployeeCountVisitor;
import pl.agh.jtp.lab03_home.visitor.MediumSalaryCalcVisitor;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Tests {
    private Company company;
    private IManager manager;

    @Before
    public void initCompany() {
        company = Company.getInstance();
        IManager CEO = new GroupManager("Will Jackson", "CEO", 100,
                new TeamSizeHireStrategy(2));
        company.hireCEO(CEO);

        //CEO hire some managers...
        IManager javaManager = new GroupManager("Anakin Spoow", "Manager", 20,
                new BudgetBasedHireStrategy(BigDecimal.valueOf(23000)));
        CEO.hire(javaManager);
        manager = javaManager;
        javaManager.hire(new Developer("Luke Walker", "Java Developer"));
        javaManager.hire(new Developer("Kira Khizbu", "Java Developer"));
        javaManager.hire(new Tester("Paul Vecty", "Tester"));
        javaManager.hire(new Tester("Carl Marks", "Tester"));
        javaManager.hire(new Developer("Micky Mike", "Senior Java Dev"));

        IManager webFlowManager = new GroupManager("Kate Morel", "Manager", 20,
                new TeamSizeHireStrategy(20));
        CEO.hire(webFlowManager);
        webFlowManager.hire(new Developer("Inis Kyze", "Designer"));
        webFlowManager.hire(new Developer("Andrew Lawe", "Rails Developer"));
        webFlowManager.hire(new Tester("Adam Skinski", "GUI Tester"));
    }

    @Test(expected = CEOAlreadyHiredException.class)
    public void shouldThrowCEOAlreadyHiredException() {
        System.out.println(company.getCEO().getDescription());
        company.hireCEO(new GroupManager("Frank Simons", "Boss", 999,
                new TeamSizeHireStrategy(100)));
    }

    @Test
    public void testEmployeeCountingVisitor() {
        EmployeeCountVisitor visitor = new EmployeeCountVisitor();
        company.getCEO().accept(visitor);

        assertTrue(visitor.getCountOfEmployee() == 11);
    }

    @Ignore
    @Test
    public void testMediumSalaryCalcVisitor() {
        MediumSalaryCalcVisitor visitor = new MediumSalaryCalcVisitor("Manager");
        company.getCEO().accept(visitor);

        assertTrue(visitor.getMediumSalary().equals(BigDecimal.valueOf(10000)));
    }

    @Test
    public void testCompanyStructurePrintVisitor() {
        CompanyStructurePrintVisitor visitor = new CompanyStructurePrintVisitor();
        company.getCEO().accept(visitor);

        System.out.print(visitor.stringToPrint());
    }

    @Test
    public void testTeamSizeHireStrategy() {
        assertFalse(company.getCEO().canHire(new Developer("Tiki Took", "Dev")));
    }

    @Test
    public void testBudgetBasedHireStrategy() {
        assertFalse(manager.canHire(new Tester("Bill Bam", "Cpp Tester")));
    }

    @After
    public void tearDown() {
        company.fireCEO();
        company = null;
        manager = null;

    }
}
