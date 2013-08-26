package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.IO.IO;
import pl.agh.jtp.lab06_home.stats.CommandStatisticsProxy;
import pl.agh.jtp.lab06_home.versionControl.License;
import pl.agh.jtp.lab06_home.versionControl.LicenseType;
import pl.agh.jtp.lab06_home.versionControl.VersionController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class loads plugins from file.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PluginLoader {
    public static final String path = "plugin.configuration";
    private static final Logger LOGGER = Logger.getLogger(PluginLoader.class.getName());
    private final IO io;
    private final VersionController versionController = new VersionController();

    public PluginLoader(IO io) {
        this.io = io;
    }

    public List<Plugin> loadPlugins() {
        List<Plugin> plugins = new ArrayList<>();

        String pluginName;
        try(BufferedReader inputStream = new BufferedReader(new FileReader(path))) {
            while((pluginName = inputStream.readLine()) != null) {
                Class<? extends Plugin> pluginClass = (Class<? extends Plugin>) Class.forName(pluginName);
                addAnnotatedPluginToList(pluginClass, plugins);
            }
        }  catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "License file not found!!!", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception during reading license file!", e);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Plugin class not found!", e);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, "Problem while instantiating a plugin!", e);
        }
        return plugins;
    }

    private void addAnnotatedPluginToList(Class<? extends Plugin> pluginClass, List<Plugin> plugins) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        LicenseType applicationLicenseType = versionController.getVersion();

        if(pluginClass.isAnnotationPresent(License.class)) {
            License license = pluginClass.getAnnotation(License.class);
            for(LicenseType licenseType : license.licenseType()) {
                if(licenseType.equals(applicationLicenseType)) {
                    wrapPluginIntoStatisticsProxyAndAddToList(pluginClass, plugins);
                }
            }
        }
    }

    private void wrapPluginIntoStatisticsProxyAndAddToList(Class<? extends Plugin> pluginClass, List<Plugin> plugins) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Plugin plugin = pluginClass.getConstructor(IO.class).newInstance(io);

        CommandStatisticsProxy pluginProxy = new CommandStatisticsProxy(plugin);
        plugins.add(pluginProxy);
        LOGGER.log(Level.INFO, "Plugin: " + plugin.getClass().getSimpleName() + " loaded");
    }
}
