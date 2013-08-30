package lab06_homeTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab06_home.domain.Company;
import pl.agh.jtp.lab06_home.domain.IManager;
import pl.agh.jtp.lab06_home.domain.people.Developer;
import pl.agh.jtp.lab06_home.domain.people.GroupManager;
import pl.agh.jtp.lab06_home.domain.people.Tester;
import pl.agh.jtp.lab06_home.employmentstrategy.SimpleHierarchyEmploymentStrategy;
import pl.agh.jtp.lab06_home.hirestrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab06_home.iterators.CompanyIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class CompanyIteratorTest {
    private CompanyIterator instance;

    @Before
    public void setUp() {
        GroupManager ceo = new GroupManager("John", "Manager", new TeamSizeHireStrategy(10));

        Company company = new Company(ceo, new SimpleHierarchyEmploymentStrategy());

        IManager javaManager = new GroupManager("Kuba", "JavaCore Manager", new TeamSizeHireStrategy(10));
        ceo.hire(javaManager);
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

        instance = new CompanyIterator(company);
    }

    @Test
    public void hasNextShouldReturnTrueWhenCompanyExist() {
        //given
        //company made in setUp()

        //when
        boolean hasNext = instance.hasNext();

        //then
        assertTrue(hasNext);
    }

    @Test
    public void nextShouldReturnTenEmployees() {
        //given
        int counter = 0;

        //when
        while (instance.hasNext()) {
            counter++;
            instance.next();
        }

        //then
        assertEquals(10, counter);
    }

}
