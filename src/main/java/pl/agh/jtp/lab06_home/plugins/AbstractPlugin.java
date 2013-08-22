package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.helpers.StringMatcher;

import java.util.logging.Logger;

/**
 * Plugin interface skeleton.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
abstract class AbstractPlugin implements Plugin {
    protected static final Logger LOGGER = Logger.getLogger(AbstractPlugin.class.getName());

    //base command must be on of first position! e.g. "open", "open help"
    private String[] commands;

    /**
     * Constructor set a table of acceptable commands by a this plugin.
     * Important! put a basic command on first position.
     * @param commandsProvidedByThisPlugin Table of acceptable commands.
     */
    protected AbstractPlugin(String[] commandsProvidedByThisPlugin) {
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

    protected boolean checkWhetherCommandMatchesPattern(String command, String regex) {
        if(!StringMatcher.match(command, regex)) {
            System.out.println("Bad command form! Right is:");
            System.out.println(help(command));
            return false;
        }
        return true;
    }
}
