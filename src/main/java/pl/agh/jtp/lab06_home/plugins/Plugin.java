package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.Context;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Plugin {
    /**
     * @return name of the command that this plugin provides
     */
    String getCommand();

    /**
     * @param command user provided command
     * @return true if plugin implementation can process the command
     */
    boolean accept(String command);

    /**
     * @param command
     * @return provide help for the command e.g arguments for command
     */
    String help(String command);

    /**
     * Execute action provided by the plugin using current application context
     * @param command
     * user provided command
     * @param context
     * application context
     * @return application context after command execution. It can be the same
     * object as provide as a param or a new application context if
     * plugin execution modifies it.
     */
    Context execute(String command, Context context);
}