package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Class manage plugins.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PluginManager {
    private static List<Plugin> plugins = new ArrayList<>();

    public PluginManager() {
        loadPlugins();
    }

    //TODO create PluginLoader
    private void loadPlugins() {
        Plugin showPlugin = new ShowStructurePlugin();
        plugins.add(showPlugin);
        Plugin openCompanyPlugin = new OpenCompanyPlugin();
        plugins.add(openCompanyPlugin);
        Plugin saveCompanyPlugin = new SaveCompanyPlugin();
        plugins.add(saveCompanyPlugin);
        Plugin changeEmployeePlugin = new ChangeEmployeePlugin();
        plugins.add(changeEmployeePlugin);
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
            System.out.println(command);
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
