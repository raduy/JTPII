package pl.agh.jtp.lab06_home.application;

import pl.agh.jtp.lab06_home.application.io.ConsoleIO;
import pl.agh.jtp.lab06_home.application.io.IO;
import pl.agh.jtp.lab06_home.application.performers.ConfigurationLoader;
import pl.agh.jtp.lab06_home.application.performers.Interpreter;
import pl.agh.jtp.lab06_home.application.plugins.PluginManager;
import pl.agh.jtp.lab06_home.application.stats.Statistics;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main application class.
 * Create a new instance to run application.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    private static Session session = Session.INSTANCE;
    private static PluginManager pluginManager;
    private final IO io = new ConsoleIO();
    private Interpreter interpreter;
    private Statistics statistics;

    public Application() {
        ConfigurationLoader.loadConfiguration();
        LOGGER.log(Level.INFO, "CONFIGURATION LOADED");
        LOGGER.log(Level.INFO, session.toString());

        if(session.isActive()) {
            LOGGER.log(Level.INFO, "Some try to create doubled session");
            io.writeln("Application is already running");
        } else {
            session.setActive(true);
            LOGGER.log(Level.INFO, session.toString());

            statistics = new Statistics();
            pluginManager = new PluginManager(io);
            interpreter = new Interpreter(this);

            startNewSession();
        }
    }

    private void startNewSession() {
        LOGGER.log(Level.INFO, "New session started");
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
        try {
            Application app = new Application();
        } catch (Exception e) {
            System.out.println("Ups.. something went wrong. Please restart application.");
            LOGGER.log(Level.SEVERE, "TOTALLY-UNEXPECTED-EXCEPTION ! ! !", e);
        }
    }
}
