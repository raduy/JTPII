package pl.agh.jtp.lab06_home.application.plugins.core;

import pl.agh.jtp.lab06_home.application.Context;
import pl.agh.jtp.lab06_home.application.io.IO;
import pl.agh.jtp.lab06_home.application.plugins.AbstractPlugin;
import pl.agh.jtp.lab06_home.application.versioncontrol.License;
import pl.agh.jtp.lab06_home.domain.IEmployee;

import static pl.agh.jtp.lab06_home.application.versioncontrol.LicenseType.ENTERPRISE;
import static pl.agh.jtp.lab06_home.application.versioncontrol.LicenseType.STANDARD;

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
    public Context executeValidCommand(String command, Context context) {

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
