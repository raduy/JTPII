package pl.agh.jtp.lab06_home.plugins.core;

import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.IO.IO;
import pl.agh.jtp.lab06_home.plugins.AbstractPlugin;
import pl.agh.jtp.lab06_home.versionControl.License;
import pl.agh.jtp.lab06_home.visitor.CompanyStructurePrintVisitor;

import static pl.agh.jtp.lab06_home.versionControl.LicenseType.ENTERPRISE;
import static pl.agh.jtp.lab06_home.versionControl.LicenseType.STANDARD;

/**
 * Plugin provide show company command.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@License(licenseType = {STANDARD, ENTERPRISE})
public class ShowStructurePlugin extends AbstractPlugin {
    private static String commandRegexForm = "^show[ ]*";

    /**
     * {@inheritDoc}
     */
    public ShowStructurePlugin(IO io) {
        super(new String[]{"show", "help show"}, io);
    }

    @Override
    public String help(String command) {
        return "show []";
    }

    @Override
    public Context execute(String command, Context context) {

        if(!validateCommand(command)) {
            commandNotValid();
            return context;
        }

        if(context == null) {
            getIO().writeln("No company to show!");
            return context;
        }
        CompanyStructurePrintVisitor visitor = new CompanyStructurePrintVisitor();
        context.getCompany().accept(visitor);
        getIO().writeln(visitor.stringToPrint());
        return context;
    }

    @Override
    public String getCommandRegexForm() {
        return commandRegexForm;
    }
}
