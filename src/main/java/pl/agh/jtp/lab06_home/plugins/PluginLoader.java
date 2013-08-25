package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.IO.IO;
import pl.agh.jtp.lab06_home.versionControl.License;
import pl.agh.jtp.lab06_home.versionControl.LicenseType;
import pl.agh.jtp.lab06_home.versionControl.VersionController;

import java.io.*;
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
        LicenseType applicationLicenseType = versionController.getVersion();

        String pluginName;
        try(BufferedReader inputStream = new BufferedReader(new FileReader(path))) {
            while((pluginName = inputStream.readLine()) != null) {
                Class<? extends Plugin> pluginClass = (Class<? extends Plugin>) Class.forName(pluginName);

                if(pluginClass.isAnnotationPresent(License.class)) {
                    License license = pluginClass.getAnnotation(License.class);
                    for(LicenseType licenseType : license.licenseType()) {
                        if(licenseType.equals(applicationLicenseType)) {
                            Plugin plugin = pluginClass.getConstructor(IO.class).newInstance(io);
                            plugins.add(plugin);
                            LOGGER.log(Level.INFO, "Plugin: " + plugin.getClass().getSimpleName() + " loaded");
                            io.writeln(plugin.toString());
                        }
                    }
                }
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
}
