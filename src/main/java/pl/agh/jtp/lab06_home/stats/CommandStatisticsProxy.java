package pl.agh.jtp.lab06_home.stats;

import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.plugins.Plugin;

/**
 * Plugin proxy, which counts commands invocations.
 * It use Statistics object to store that information.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CommandStatisticsProxy implements Plugin {
    private static final Statistics stats = new Statistics();
    private final Plugin plugin;

    public CommandStatisticsProxy(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return plugin.getCommand();
    }

    @Override
    public boolean accept(String command) {
        return plugin.accept(command);
    }

    @Override
    public String help(String command) {
        stats.incrementCommandInvocationCounterOrCreateANewEntry("help " + getCommand());

        return plugin.help(command);
    }

    @Override
    public Context execute(String command, Context context) {
        stats.incrementCommandInvocationCounterOrCreateANewEntry(getCommand());

        return plugin.execute(command, context);
    }
}
