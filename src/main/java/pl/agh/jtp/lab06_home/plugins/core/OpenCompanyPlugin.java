package pl.agh.jtp.lab06_home.plugins.core;

import pl.agh.jtp.lab06_home.Company;
import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.IO.IO;
import pl.agh.jtp.lab06_home.plugins.AbstractPlugin;
import pl.agh.jtp.lab06_home.versionControl.License;

import java.io.*;
import java.util.logging.Level;

import static pl.agh.jtp.lab06_home.versionControl.LicenseType.STANDARD;

/**
 * Plugin provide the 'open' command.
 * It reads a Company from file and create a new Context.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@License(licenseType = STANDARD)
public class OpenCompanyPlugin extends AbstractPlugin {
    private static final String commandRegexForm = "^open[ ]+[a-zA-Z]+[.][a-zA-Z]+[ ]*";

    /**
     * {@inheritDoc}
     */
    public OpenCompanyPlugin(IO io) {
        super(new String[]{"open", "help open"}, io);
    }

    @Override
    public String help(String command) {
        return "open [file with company data]";
    }

    @Override
    public Context executeValidCommand(String command, Context context) {

        Context result = null;
        String path = command.split("[ ]+")[1];
        File serializedCompanyFile = new File(path);

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(serializedCompanyFile))) {
            Company company = (Company) inputStream.readObject();
            result = new Context(company, company.getCEO());

        } catch (FileNotFoundException e) {
            getIO().writeln("Ops.. Can't find such file. Try again");
            LOGGER.log(Level.FINE, "Bad file name given as an argument", e); //fine because users often put wrong data
        } catch (IOException e) {
            getIO().writeln("Ops.. There was a problem with reading a file. Try again");
            LOGGER.log(Level.SEVERE, "I/O error occurs while reading FileInputStream", e);
        } catch (ClassNotFoundException e) {
            getIO().writeln("Ops.. Problem occurs. Company class not found");
            LOGGER.log(Level.SEVERE, "Company.class not found!!!", e);
        }
        return result;
    }

    @Override
    public String getCommandRegexForm() {
        return commandRegexForm;
    }
}
