package pl.agh.jtp.lab06_home.application.performers;

import pl.agh.jtp.lab06_home.application.Application;
import pl.agh.jtp.lab06_home.application.io.IO;
import pl.agh.jtp.lab06_home.application.plugins.Plugin;
import pl.agh.jtp.lab06_home.application.stats.Statistics;

/**
 * Class provide help command execution.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class HelpCommandPerformer {
    private final IO io;
    private Statistics statistics = new Statistics();

    public HelpCommandPerformer(IO io) {
        this.io = io;
    }

    /**
     * Method tries to execute command "help".
     * Returns true if command is acceptable, false if there are no help for plugin commands
     * or given command isn't a basic help.
     * @param helpCommand given command
     * @param application application which call to perform help
     * @return true if command can be executed, false if not.
     */
    public boolean executeHelp(String helpCommand, Application application) {
        if("help".equals(helpCommand)) {
            statistics.incrementCommandInvocationCounterOrCreateANewEntry("help");
            application.listAcceptableCommands();
            return true;
        } else {
            return tryCallHelpForPluginCommands(helpCommand, application);
        }
    }

    private boolean tryCallHelpForPluginCommands(String helpCommand, Application application) {
        for(Plugin plugin : application.getPluginManager().getPluginList()) {
            if (helpCommand.equals("help " + plugin.getCommand())){
                io.writeln(plugin.help(plugin.getCommand()));
                return true;
            }
        }
        return false;
    }
}
