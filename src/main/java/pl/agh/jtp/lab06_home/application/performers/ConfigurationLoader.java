package pl.agh.jtp.lab06_home.application.performers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Class loads configuration data form file.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class ConfigurationLoader {
    private static final Logger LOGGER = Logger.getLogger(ConfigurationLoader.class.getName());

    public static void loadConfiguration() {
        loadLoggerConfiguration();
        //other configuration loading
    }

    private static void loadLoggerConfiguration() {
        LogManager logManager = LogManager.getLogManager();
        try(InputStream inputStream = new FileInputStream("logging.properties")) {
            logManager.readConfiguration(inputStream);

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Logging properties file not found!", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOE during logging properties loading!", e);
        }
    }
}
