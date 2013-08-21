package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.Company;
import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.helpers.StringMatcher;

import java.io.*;
import java.util.logging.Level;

/**
 * Plugin provide the 'open' command.
 * It reads a Company from file and create a new Context.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class OpenCompanyPlugin extends AbstractPlugin {
    private static final String commandRegexForm = "^open[ ]+[a-zA-Z]+[.][a-zA-Z]+[ ]*";

    /**
     * {@inheritDoc}
     */
    public OpenCompanyPlugin() {
        super(new String[]{"open", "help open"});
    }

    @Override
    public String help(String command) {
        return "open [file with company data]";
    }

    @Override
    public Context execute(String command, Context context) {

        if(!checkWhetherCommandMatchesPattern(command, commandRegexForm)) {
            return context;
        }

        Context result = null;
        String path = command.split("[ ]+")[1];
        File serializedCompanyFile = new File(path);

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(serializedCompanyFile))) {
            Company company = (Company) inputStream.readObject();
            result = new Context(company, company.getCEO());

        } catch (FileNotFoundException e) {
            System.out.println("Ops.. Can't find such file. Try again");
            LOGGER.log(Level.FINE, "Bad file name given as an argument", e); //fine because users often put wrong data
        } catch (IOException e) {
            System.out.println("Ops.. There was a problem with reading a file. Try again");
            LOGGER.log(Level.SEVERE, "I/O error occurs while reading FileInputStream", e);
        } catch (ClassNotFoundException e) {
            System.out.println("Ops.. Problem occurs. Company class not found");
            LOGGER.log(Level.SEVERE, "Company.class not found!!!", e);
        }
        return result;
    }
}
