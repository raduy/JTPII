package pl.agh.jtp.lab06_home.plugins;

import java.util.ArrayList;
import java.util.List;

/**
 * Class manage plugins.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PluginManager {
    private static List<Plugin> plugins = new ArrayList<>();

    public static List<Plugin> getPluginList() {
        Plugin showPlugin = new ShowStructurePlugin();
        plugins.add(showPlugin);
        Plugin openCompanyPlugin = new OpenCompanyPlugin();
        plugins.add(openCompanyPlugin);
        return plugins;
    }

    public static List<String> getAcceptablePluginCommands() {
        List<String> result = new ArrayList<>();
        for(Plugin plugin : plugins) {
            result.add(plugin.getCommand());
        }
        return result;
    }
}
