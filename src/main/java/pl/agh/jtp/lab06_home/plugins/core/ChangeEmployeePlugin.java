package pl.agh.jtp.lab06_home.plugins.core;

import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.IO.IO;
import pl.agh.jtp.lab06_home.plugins.AbstractPlugin;
import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.versionControl.License;

import static pl.agh.jtp.lab06_home.versionControl.LicenseType.ENTERPRISE;
import static pl.agh.jtp.lab06_home.versionControl.LicenseType.STANDARD;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@License(licenseType = {STANDARD, ENTERPRISE})
public class ChangeEmployeePlugin extends AbstractPlugin {
    private static String commandRegexForm = "^cd[ ]+[0-9]+[ ]*";

    public ChangeEmployeePlugin(IO io) {
        super(new String[]{"cd", "cd help"}, io);
    }

    @Override
    public String help(String command) {
        return "cd [ID]" +
                "\nChanges employee of current context";
    }

    @Override
    public Context execute(String command, Context context) {

        if(!validateCommand(command)) {
            commandNotValid();
            return context;
        }

        if(context == null) {
            getIO().writeln("There are no company selected !" +
                            "\nCreate context to use this command");
            return context;
        }

        int employeeID = parseEmployeeID(command);

        for(IEmployee employee : context.getCompany()) {
            if(employeeID == employee.getID()) {
                return new Context(context.getCompany(), employee);
            }
        }

        getIO().writeln("There are no such employee. Check the spelling");
        return context;
    }

    private int parseEmployeeID(String command) {
        String[] personalData = command.split("[ ]+");
        int ID = Integer.parseInt(personalData[1]);

        return ID;
    }

    @Override
    public String getCommandRegexForm() {
        return commandRegexForm;
    }
}
