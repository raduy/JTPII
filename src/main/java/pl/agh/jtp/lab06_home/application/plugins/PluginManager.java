package pl.agh.jtp.lab06_home.application.plugins;

import pl.agh.jtp.lab06_home.application.Session;
import pl.agh.jtp.lab06_home.application.io.IO;

import java.util.ArrayList;
import java.util.List;

/**
 * Class manage plugins.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PluginManager {
    private static List<Plugin> plugins = new ArrayList<>();
    public final IO io;
    private final PluginLoader pluginLoader;

    public PluginManager(IO io) {
        this.io = io;
        this.pluginLoader = new PluginLoader(io);
        plugins = pluginLoader.loadPlugins();
    }

    public List<Plugin> getPluginList() {
        return plugins;
    }

    public static List<String> getAcceptablePluginCommands() {
        List<String> result = new ArrayList<>();
        for(Plugin plugin : plugins) {
            result.add(plugin.getCommand());
        }
        return result;
    }

    public void listAcceptablePluginCommands() {
        for(String command : getAcceptablePluginCommands()) {
            io.writeln(command);
        }
    }

    public boolean tryExecuteCommandByPlugin(String s, Session session) {
        String command = s.split(" ")[0];
        for(Plugin plugin : plugins) {
            if(plugin.accept(command)) {
                session.setCurrentContext(plugin.execute(s, session.getCurrentContext()));
                return true;
            }
        }
        return false;
    }
}
