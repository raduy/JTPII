package lab06_homeTests.visitorTest;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.lab06_home.hireStrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.structure.IManager;
import pl.agh.jtp.lab06_home.structure.people.Developer;
import pl.agh.jtp.lab06_home.structure.people.GroupManager;
import pl.agh.jtp.lab06_home.structure.people.Tester;
import pl.agh.jtp.lab06_home.visitor.CompanyStructurePrintVisitor;

import static org.junit.Assert.assertEquals;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CompanyStructurePrintVisitorTest {

    private CompanyStructurePrintVisitor instance;
    private GroupManager headManger;
    private IEmployee directDeveloperUnderCEO;


    @Before
    public void setUp() {

        instance = new CompanyStructurePrintVisitor();

        GroupManager ceo = new GroupManager("John", "Manager", new TeamSizeHireStrategy(10));
        headManger = ceo;
        IManager javaManager = new GroupManager("Kuba", "JavaCore Manager", new TeamSizeHireStrategy(10));
        ceo.hire(javaManager); //we have a CEO!

        javaManager.hire(new Developer("Jake", "Junior Java Dev"));
        javaManager.hire(new Developer("Will", "Regular Java Dev"));
        javaManager.hire(new Tester("Romek", "Java Tester"));


        IManager webFlowManager = new GroupManager("Ania", "FrontEnd Manager", new TeamSizeHireStrategy(10));

        ceo.hire(webFlowManager);
        webFlowManager.hire(new Developer("Mark", "JS Developer"));
        webFlowManager.hire(new Developer("Chuck", "Bootstrap Expert"));
        webFlowManager.hire(new Tester("Mili", "Tester"));

        IManager securityManager = new GroupManager("BigBob", "SecurityManager", new TeamSizeHireStrategy(10));
        webFlowManager.hire(securityManager);
        securityManager.hire(new Tester("FatJoe", "TestGuy"));

        webFlowManager.hire(new Tester("Guy", "tester"));
        directDeveloperUnderCEO = new Developer("Bary", "Developer");
        ceo.hire(directDeveloperUnderCEO);
    }

    @Test
    public void stringToPrintTest() {
        //given
        headManger.accept(instance);

        //when
        String companyStructure = instance.stringToPrint();

        //then
        assertEquals("[John, Manager, 3]\n" +
                "|---[Kuba, JavaCore Manager, 3]\n" +
                "|---|---[Jake, Junior Java Dev, 0]\n" +
                "|---|---[Will, Regular Java Dev, 0]\n" +
                "|---|---[Romek, Java Tester, 0]\n" +
                "|---[Ania, FrontEnd Manager, 5]\n" +
                "|---|---[Mark, JS Developer, 0]\n" +
                "|---|---[Chuck, Bootstrap Expert, 0]\n" +
                "|---|---[Mili, Tester, 0]\n" +
                "|---|---[BigBob, SecurityManager, 1]\n" +
                "|---|---|---[FatJoe, TestGuy, 0]\n" +
                "|---|---[Guy, tester, 0]\n" +
                "|---[Bary, Developer, 0]\n", companyStructure);
    }

    @Test
    public void threeLevelManagerTest() throws Exception {
        //given
        headManger.fire(directDeveloperUnderCEO); //before it doesn't work with any direct dev under CEO
        headManger.accept(instance);

        //when
        String companyStructure = instance.stringToPrint();

        //then
        assertEquals("[John, Manager, 2]\n" +
                "|---[Kuba, JavaCore Manager, 3]\n" +
                "|---|---[Jake, Junior Java Dev, 0]\n" +
                "|---|---[Will, Regular Java Dev, 0]\n" +
                "|---|---[Romek, Java Tester, 0]\n" +
                "|---[Ania, FrontEnd Manager, 5]\n" +
                "|---|---[Mark, JS Developer, 0]\n" +
                "|---|---[Chuck, Bootstrap Expert, 0]\n" +
                "|---|---[Mili, Tester, 0]\n" +
                "|---|---[BigBob, SecurityManager, 1]\n" +
                "|---|---|---[FatJoe, TestGuy, 0]\n" +
                "|---|---[Guy, tester, 0]\n", companyStructure);
    }
}
