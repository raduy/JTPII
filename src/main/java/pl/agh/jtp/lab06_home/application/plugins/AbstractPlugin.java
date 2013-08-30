package pl.agh.jtp.lab06_home.application.plugins;

import pl.agh.jtp.lab06_home.application.Context;
import pl.agh.jtp.lab06_home.application.io.IO;
import pl.agh.jtp.lab06_home.helpers.StringMatcher;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Plugin interface skeleton.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public abstract class AbstractPlugin implements Plugin {
    protected static final Logger LOGGER = Logger.getLogger(AbstractPlugin.class.getName());
    private static IO io;
    //base command must be on of first position! e.g. "open", "open help"
    private String[] commands;

    /**
     * Constructor set a table of acceptable commands by a this plugin.
     * Important! put a basic command on first position.
     * @param commandsProvidedByThisPlugin Table of acceptable commands.
     */
    public AbstractPlugin(String[] commandsProvidedByThisPlugin, IO io) {
        this.io = io;
        commands = commandsProvidedByThisPlugin;
    }

    @Override
    public String getCommand() {
        return commands[0];
    }

    @Override
    public boolean accept(String command) {
        for(String command1 : commands) {
            if (command1.equals(command)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Context execute(String command, Context context) {
        LOGGER.log(Level.INFO, "Try invoke:" + command + " command with context:\n" + context);
        if(!validateCommand(command)) {
            commandNotValid();
            return context;
        }

        return executeValidCommand(command, context);
    }

    protected abstract Context executeValidCommand(String command, Context context);

    private boolean validateCommand(String command) {
        return checkWeatherCommandMatchesPattern(command, getCommandRegexForm());
    }

    private boolean checkWeatherCommandMatchesPattern(String command, String regex) {
        return StringMatcher.match(command, regex);
    }

    private void commandNotValid() {
        io.writeln("Bad command form! Right is:");
        io.writeln(help(getCommand()));
    }

    abstract public String getCommandRegexForm();

    public static IO getIO() {
        return io;
    }

    @Override
    public String toString() {
        return "Plugin{" +
                "commands=" + Arrays.toString(commands) +
                '}';
    }
}
