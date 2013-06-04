package pl.agh.jtp.lab02_home;

/**
 * Test for company structure
 *
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Main {

    public static void main(String[] args) {
        Company company = Company.getInstance();

        GroupManager CEO = new GroupManager("Ozi", "CEO", 14);
        company.hireCEO(CEO);

        //hire some managers:
        IManager javaManager = new GroupManager("Kuba", "JavaCore Manager", 5);
        CEO.hire(javaManager);
        javaManager.hire(new Developer("Jake", "Junior Java Dev"));
        javaManager.hire(new Developer("Will", "Regular Java Dev"));
        javaManager.hire(new Tester("Romek", "Java Tester"));

        IManager webFlowManager = new GroupManager("Ania", "FrontEnd Manager", 4);
        CEO.hire(webFlowManager);
        webFlowManager.hire(new Developer("Mark", "JS Developer"));
        webFlowManager.hire(new Developer("Chuck", "Bootstrap Expert"));
        webFlowManager.hire(new Tester("Mili", "Tester"));

        IManager securityManager = new GroupManager("BigBob", "SecurityManager", 10);
        webFlowManager.hire(securityManager);
        securityManager.hire(new Tester("FatJoe", "TestGuy"));

        System.out.println(CEO.work());
    }

}
