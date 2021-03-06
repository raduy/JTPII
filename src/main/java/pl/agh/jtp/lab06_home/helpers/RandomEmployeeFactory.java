package pl.agh.jtp.lab06_home.helpers;

import pl.agh.jtp.lab06_home.domain.IEmployee;
import pl.agh.jtp.lab06_home.domain.people.Developer;
import pl.agh.jtp.lab06_home.domain.people.GroupManager;
import pl.agh.jtp.lab06_home.domain.people.Tester;
import pl.agh.jtp.lab06_home.hirestrategy.TeamSizeHireStrategy;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class RandomEmployeeFactory {

    private static Random random = new Random();
    private final static String[] names = {"John Smith" , "Adrew Wolski", "Jigy Banow",
                                           "Kate Walker" ,"Spencer Ban", "Martin Falkow"};

    private final static String[] devRoles = {"Junior Java Dev", "Regular Java Dev", "Senior Java Dev"};

    private final static String[] testerRoles = {"Core Tester", "Web Flow Tester"};

    private final static String[] managerRoles = {"High Manger", "Project Manger"};

    private String getRandomName() {
        return names[random.nextInt(names.length)];
    }

    private String getRandomRoleForDeveloper() {
        return devRoles[random.nextInt(devRoles.length)];
    }

    private String getRandomRoleForTester() {
        return testerRoles[random.nextInt(testerRoles.length)];
    }

    private String getRandomRoleForManger() {
        return managerRoles[random.nextInt(managerRoles.length)];
    }

    public IEmployee getRandomEmployee() {
        switch (random.nextInt(3)) {
            case 0 : return new Developer(getRandomName(), getRandomRoleForDeveloper());
            case 1 : return new Tester(getRandomName(), getRandomRoleForTester());
            default: return new GroupManager(getRandomName(), getRandomRoleForManger(),
                    new TeamSizeHireStrategy(random.nextInt(5)));
        }
    }

    public IEmployee[] getTableOfNRandomEmployees(int n) {
        IEmployee[] result = new IEmployee[n];
        for (int i = 0; i < result.length; i++) {
            result[i] = getRandomEmployee();
        }

        return result;
    }

    public static void main(String[] args) {
        RandomEmployeeFactory employeeFactory = new RandomEmployeeFactory();
        IEmployee[] employees = employeeFactory.getTableOfNRandomEmployees(10);
        System.out.println(Arrays.toString(employees));
    }
}
