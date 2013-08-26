package pl.agh.jtp.lab06_home;

import pl.agh.jtp.lab06_home.IO.ConsoleIO;
import pl.agh.jtp.lab06_home.IO.IO;
import pl.agh.jtp.lab06_home.plugins.PluginManager;
import pl.agh.jtp.lab06_home.stats.Statistics;

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
    private static PluginManager pluginManager;
    private final IO io = new ConsoleIO();
    private Statistics statistics;

    public Application() {
        LOGGER.log(Level.INFO, session.toString());
        if(session.isActive()) {
            LOGGER.log(Level.INFO, "Some try to create doubled session");
            System.out.println("Application is already running");
        } else {
            LOGGER.log(Level.INFO, "New session started");
            session.setActive(true);
            LOGGER.log(Level.INFO, session.toString());

            startNewSession();
        }
    }

    private void startNewSession() {
        statistics = new Statistics();
        pluginManager = new PluginManager(io);
        Interpreter interpreter = new Interpreter(this);
        interpreter.nextCommand();
    }

    public void exitApplication() {
        io.writeln("Bye bye");
        statistics.incrementCommandInvocationCounterOrCreateANewEntry("exit");
        statistics.logSingleSessionStatistics();
        io.closeResources();
        session.setActive(false);
        System.exit(0);
    }

    public void listAcceptableCommands() {
        io.writeln("help");
        io.writeln("exit");
        pluginManager.listAcceptablePluginCommands();
    }

    public void printContext() {
        //LOGGER.log(Level.INFO, "STATE: " + session);
        if(session.getCurrentContext() == null) {
            io.writeln("No Company");
        } else {
            io.writeln(session.getCurrentContext().getCompany().getCompanyName() +
                    " selected employee: " +
                    session.getCurrentContext().getCurrentEmployee() +
                    " and ID: "  + session.getCurrentContext().getCurrentEmployee().getID());
        }
    }

    public Session getSession() {
        return session;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public IO getIO() {
        return io;
    }

    public static void main(String[] args) {
        Application app = new Application();
    }
}
