package lab03_homeTests.visitorTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.agh.jtp.lab03_home.Developer;
import pl.agh.jtp.lab03_home.GroupManager;
import pl.agh.jtp.lab03_home.IManager;
import pl.agh.jtp.lab03_home.Tester;
import pl.agh.jtp.lab03_home.hireStrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab03_home.visitor.CompanyStructurePrintVisitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CompanyStructurePrintVisitorTest {

    private CompanyStructurePrintVisitor instance;
    private GroupManager headManger;


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
        ceo.hire(new Developer("Bary", "Developer"));
    }

    @Test
    public void stringToPrintTest() {
        headManger.accept(instance);

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
                "|---[Bary, Developer, 0]\n", instance.stringToPrint());
    }
}
