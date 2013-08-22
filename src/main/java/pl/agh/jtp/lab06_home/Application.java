package pl.agh.jtp.lab06_home;

import pl.agh.jtp.lab06_home.plugins.PluginManager;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main application class.
 * Create a new instance to run application.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Application {
    private final static Logger LOGGER = Logger.getLogger(Application.class.getName());
    private static Session session = Session.INSTANCE;
    private static PluginManager pluginManager = new PluginManager();
    private static HelpCommandPerformer helpPerformer = new HelpCommandPerformer();
    private Scanner input = new Scanner(System.in);
    private final static char prompt = '>';

    public Application() {
        LOGGER.log(Level.INFO, session.toString());
        if(session.isActive()) {
            LOGGER.log(Level.INFO, "Some try to create doubled session");
            System.out.println("Application is already running");
        } else {
            LOGGER.log(Level.INFO, "New session started");
            session.setActive(true);
            LOGGER.log(Level.INFO, session.toString());

            initializeSession();
        }
    }

    private void initializeSession() {
        nextCommand();
    }

    private void nextCommand() {
        printContext();
        System.out.print(prompt + " ");
        String inputString = input.nextLine();
        if(inputString.equals("")) {
            nextCommand();
        }
        //LOGGER.log(Level.INFO, "Command: '" + inputString + "' call");
        parseCommand(inputString);
    }

    private void parseCommand(String s) {
        boolean commandFound;
        if(s.startsWith("help")) {
            commandFound = helpPerformer.executeHelp(s,this);
        } else if(s.equals("exit")) {
            commandFound = true;
            exitApplication();
        } else {
            commandFound = pluginManager.tryExecuteCommandByPlugin(s, session);
        }
        if(!commandFound) {
            System.out.println("Command not supported :/ List of acceptable commands:");
            listAcceptableCommands();
        }
        nextCommand();
    }

    private void exitApplication() {
        System.out.println("Bye bye");
        input.close();
        session.setActive(false);
        //other thing which must be done before exit
        System.exit(0);
    }

    public void listAcceptableCommands() {
        System.out.println("help");
        System.out.println("exit");
        pluginManager.listAcceptablePluginCommands();
    }

    private void printContext() {
        //LOGGER.log(Level.INFO, "STATE: " + session);
        if(session.getCurrentContext() == null) {
            System.out.println("No Company");
        } else {
            System.out.printf("%s selected employee: %s%n",
                    session.getCurrentContext().getCompany().getCompanyName(),
                    session.getCurrentContext().getCurrentEmployee());
        }
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public static void main(String[] args) {
        Application app = new Application();
    }
}
