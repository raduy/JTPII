package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.structure.IEmployee;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class ChangeEmployeePlugin extends AbstractPlugin {
    private static final String commandRegexForm = "^cd[ ]+[a-zA-Z]+[ ]+[a-zA-Z]+[ ]*";

    public ChangeEmployeePlugin() {
        super(new String[]{"cd", "cd help"});
    }

    @Override
    public String help(String command) {
        return "cd [name][surname]" +
                "\nChanges employee of current context";
    }

    @Override
    public Context execute(String command, Context context) {

        if(!checkWhetherCommandMatchesPattern(command, commandRegexForm)) {
            return context;
        }

        if(context == null) {
            System.out.println("There are no company selected !" +
                    "\n Create context to use this command");
            return context;
        }

        String employeeName = parseEmployeeName(command);

        for(IEmployee employee : context.getCompany()) {
            if(employeeName.equals(employee.getName())) {
                return new Context(context.getCompany(), employee);
            }
        }

        System.out.printf("There are no such employee. Check the spelling");
        return context;
    }

    private String parseEmployeeName(String command) {
        String[] personalData = command.split("[ ]+");
        String name = personalData[1],
               surname = personalData[2];

        return name + " " + surname;
    }
}
