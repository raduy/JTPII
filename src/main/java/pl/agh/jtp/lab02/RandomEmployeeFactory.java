package pl.agh.jtp.lab02;

import java.util.Random;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class RandomEmployeeFactory {

    private static Random random = new Random();
    private final int maxNumberOfSubordinatesForManger = 10;
    private final static String[] names = {"John Smith" , "Adrew Wolski", "Jigy Banow",
                                           "Kate Walker" ,"Spencer Ban", "Martin Falkow"};

    private final static String[] devRoles = {"Junior Java Dev", "Regular Java Dev", "Senior Java Dev"};

    private final static String[] testerRoles = {"Core Tester", "Web Flow Tester"};

    private final static String[] managerRoles = {"CEO", "Project Manger"};

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
        switch (random.nextInt(2)) {
            case 0 : return new Developer(getRandomName(), getRandomRoleForDeveloper());
            case 1 : return new Tester(getRandomName(), getRandomRoleForTester());
            default: return new GroupManager(getRandomName(), getRandomRoleForManger(),
                    random.nextInt(maxNumberOfSubordinatesForManger));
        }
    }

    public IEmployee[] getTableOfNRandomEmployees(int n) {
        IEmployee[] result = new IEmployee[n];
        for (int i = 0; i < result.length; i++) {
            result[i] = getRandomEmployee();
        }

        return result;
    }
}
