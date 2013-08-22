package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.Company;
import pl.agh.jtp.lab06_home.Context;

import java.io.*;
import java.util.logging.Level;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SaveCompanyPlugin extends AbstractPlugin {
    private static final String commandRegexForm = "^save[ ]+[a-zA-Z]+[.][a-zA-Z]+[ ]*";

    public SaveCompanyPlugin() {
        super(new String[]{"save", "help save"});
    }

    @Override
    public String help(String command) {
        return "save [file path]\n"
                + "Command save company form current context into file";
    }

    @Override
    public Context execute(String command, Context context) {

        if(!checkWhetherCommandMatchesPattern(command, commandRegexForm)) {
            return context;
        }

        if(context == null) {
            System.out.println("No Company to save");
            return context;
        }

        String path = command.split("[ ]+")[1];
        File file = new File(path);

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(context.getCompany());

        } catch (FileNotFoundException e) {
            System.out.println("Ops.. Can't create such file. Try again");
            LOGGER.log(Level.FINE, "Bad file name given as an argument", e); //fine because users often put wrong data
        } catch (IOException e) {
            System.out.println("Ops.. There was a problem with writing into a file. Try again");
            LOGGER.log(Level.SEVERE, "I/O error occurs while writing FileInputStream", e);
        }
        return context;
    }
}
