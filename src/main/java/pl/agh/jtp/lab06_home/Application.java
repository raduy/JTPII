package pl.agh.jtp.lab06_home;

import pl.agh.jtp.lab06_home.employmentStrategy.SimpleHierarchyEmploymentStrategy;
import pl.agh.jtp.lab06_home.hireStrategy.TeamSizeHireStrategy;
import pl.agh.jtp.lab06_home.plugins.Plugin;
import pl.agh.jtp.lab06_home.plugins.PluginManager;
import pl.agh.jtp.lab06_home.structure.IManager;
import pl.agh.jtp.lab06_home.structure.people.GroupManager;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Application {
    private final static Logger LOGGER = Logger.getLogger(Application.class.getName());
    private Context currentContext;
    private static Session session = Session.INSTANCE;
    private List<Plugin> plugins;
    private final static char prompt = '>';
    private Scanner input = new Scanner(System.in);
    private List<String> acceptablePluginCommands = Collections.emptyList();

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
        loadPlugins();
        //other things needed to start application
        nextCommand();
    }

    private void loadPlugins() {
        plugins = PluginManager.getPluginList();
        acceptablePluginCommands = PluginManager.getAcceptablePluginCommands();
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
        if(s.contains("help")) {
            commandFound = executeHelp(s);
        } else if(s.equals("exit")) {
            commandFound = true;
            exitApplication();
        } else {
            commandFound = tryExecuteCommandByPlugin(s);
        }
        if(!commandFound) {
            System.out.println("Command not supported :/ List of acceptable commands:");
            listAcceptableCommands();
        }
        nextCommand();
    }

    private boolean tryExecuteCommandByPlugin(String s) {
        String command = s.split(" ")[0];
        for(Plugin plugin : plugins) {
            if(plugin.accept(command)) {
                currentContext = plugin.execute(s, currentContext);
                return true;
            }
        }
        return false;
    }

    private boolean executeHelp(String helpCommand) {
        if(helpCommand.equals("help")) {
            listAcceptableCommands();
            return true;
        } else {
            return tryCallHelpForPluginCommands(helpCommand);
        }
    }

    private boolean tryCallHelpForPluginCommands(String helpCommand) {
        for(Plugin plugin : plugins) {
            if (helpCommand.equals("help " + plugin.getCommand())){
                System.out.println(plugin.help(plugin.getCommand()));
                return true;
            }
        }
        return false;
    }

    private void exitApplication() {
        System.out.println("Bye bye");
        input.close();
        session.setActive(false);
        //other thing which be done before close
        System.exit(0);
    }

    private void listAcceptableCommands() {
        System.out.println("help");
        System.out.println("exit");
        for(String command : acceptablePluginCommands) {
            System.out.println(command);
        }
    }

    private void printContext() {
        //LOGGER.log(Level.INFO, "STATE: " + session);
        if(currentContext == null) {
            System.out.println("No Company");
        } else {
            System.out.printf("%s selected employee: %s\n", currentContext.getCompany().getCompanyName(), currentContext.getCurrentEmployee());
        }
    }

    public static void main(String[] args) {
        Application app = new Application();
    }
}
