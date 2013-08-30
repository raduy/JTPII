package pl.agh.jtp.lab06_home.application.plugins;

import pl.agh.jtp.lab06_home.application.io.IO;
import pl.agh.jtp.lab06_home.application.stats.CommandStatisticsProxy;
import pl.agh.jtp.lab06_home.application.versioncontrol.License;
import pl.agh.jtp.lab06_home.application.versioncontrol.LicenseType;
import pl.agh.jtp.lab06_home.application.versioncontrol.VersionController;

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
        LicenseType applicationLicenseType = versionController.getVersion();

        String pluginName;
        try(BufferedReader inputStream = new BufferedReader(new FileReader(path))) {
            while((pluginName = inputStream.readLine()) != null) {
                Class<? extends Plugin> pluginClass = (Class<? extends Plugin>) Class.forName(pluginName);

                if(isPluginAnnotatedWithLicense(pluginClass, applicationLicenseType)) {
                    Plugin plugin = wrapInStatisticProxy(loadPluginByClassLiteral(pluginClass));
                    plugins.add(plugin);
                    LOGGER.log(Level.INFO, "Plugin: " + plugin.getClass().getSimpleName() + " loaded");
                }
            }
        } catch (ClassCastException e) {
            LOGGER.log(Level.SEVERE, "Not a Plugin class in" + path + " file!!!", e);
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

    private boolean isPluginAnnotatedWithLicense(Class<?> classLiteral, LicenseType applicationLicenseType) {
        if(classLiteral.isAnnotationPresent(License.class)) {
            License license = classLiteral.getAnnotation(License.class);
            for(LicenseType licenseType : license.licenseType()) {
                if(licenseType.equals(applicationLicenseType)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Plugin wrapInStatisticProxy(Plugin plugin) {
        return new CommandStatisticsProxy(plugin);
    }

    private Plugin loadPluginByClassLiteral(Class<? extends Plugin> pluginClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        return pluginClass.getConstructor(IO.class)
                .newInstance(io);
    }
}
