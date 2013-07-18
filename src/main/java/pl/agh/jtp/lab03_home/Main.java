package pl.agh.jtp.lab03_home;

import pl.agh.jtp.lab03_home.hireStrategy.BudgetBasedHireStrategy;
import pl.agh.jtp.lab03_home.hireStrategy.TeamSizeHireStrategy;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Test for company structure
 *
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Main {

    public static void main(String[] args) {
        Company company = Company.getInstance();

        GroupManager CEO = new GroupManager("Ozi", "CEO", 100, new TeamSizeHireStrategy(100));
        company.hireCEO(CEO);

        //Hire some managers to create company structure:
        IManager javaManager = new GroupManager("Kuba", "JavaCore Manager", 5, new BudgetBasedHireStrategy(BigDecimal.valueOf(999.)));
        CEO.hire(javaManager); //we have a CEO!

        javaManager.hire(new Developer("Jake", "Junior Java Dev", BigDecimal.valueOf(3000)));
        javaManager.hire(new Developer("Will", "Regular Java Dev", BigDecimal.valueOf(3000)));
        javaManager.hire(new Tester("Romek", "Java Tester"));


        IManager webFlowManager = new GroupManager("Ania", "FrontEnd Manager", 4, new TeamSizeHireStrategy(4));
//        When you uncomment line below it will throw CEOAlreadyHiredException
//        company.hireCEO(webFlowManager);

        CEO.hire(webFlowManager);
        webFlowManager.hire(new Developer("Mark", "JS Developer"));
        webFlowManager.hire(new Developer("Chuck", "Bootstrap Expert"));
        webFlowManager.hire(new Tester("Mili", "Tester"));

        IManager securityManager = new GroupManager("BigBob", "SecurityManager", 10, new TeamSizeHireStrategy(10));
        webFlowManager.hire(securityManager);
        securityManager.hire(new Tester("FatJoe", "TestGuy"));
        System.out.println();

        //'Test' for EmployeeIterator
        Iterator<IEmployee> iterator = javaManager.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next().getDescription());
        }
        System.out.println();

        try {
            iterator.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("You can't remove employees using iterator!");
        }
        System.out.println();

        System.out.println(securityManager.getResponsibilityChain());
        System.out.println();

        System.out.println(CEO.work());


        RandomEmployeeFactory factory = new RandomEmployeeFactory();

        System.out.println();

        IEmployee[] table = factory.getTableOfNRandomEmployees(10);
        for(int i = 0; i < 10; i++) {
            System.out.println(table[i].getDescription());
        }
    }
}
