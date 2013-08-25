package pl.agh.jtp.lab06_home.plugins.core;

import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.IO.IO;
import pl.agh.jtp.lab06_home.versionControl.License;
import pl.agh.jtp.lab06_home.plugins.AbstractPlugin;
import pl.agh.jtp.lab06_home.versionControl.LicenseType;

import java.io.*;
import java.util.logging.Level;

import static pl.agh.jtp.lab06_home.versionControl.LicenseType.ENTERPRISE;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@License(licenseType = ENTERPRISE)
public class SaveCompanyPlugin extends AbstractPlugin {
    private static final String commandRegexForm = "^save[ ]+[a-zA-Z]+[.][a-zA-Z]+[ ]*";

    public SaveCompanyPlugin(IO io) {
        super(new String[]{"save", "help save"}, io);
    }

    @Override
    public String help(String command) {
        return "save [file path]\n"
                + "Command save company form current context into file";
    }

    @Override
    public Context execute(String command, Context context) {

        if(!validateCommand(command)) {
            commandNotValid();
            return context;
        }

        if(context == null) {
            getIO().writeln("No Company to save");
            return context;
        }

        String path = command.split("[ ]+")[1];
        File file = new File(path);

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(context.getCompany());

        } catch (FileNotFoundException e) {
            getIO().writeln("Ops.. Can't create such file. Try again");
            LOGGER.log(Level.FINE, "Bad file name given as an argument", e); //fine because users often put wrong data
        } catch (IOException e) {
            getIO().writeln("Ops.. There was a problem with writing into a file. Try again");
            LOGGER.log(Level.SEVERE, "I/O error occurs while writing FileInputStream", e);
        }
        return context;
    }

    @Override
    public String getCommandRegexForm() {
        return commandRegexForm;
    }
}
