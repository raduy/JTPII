package pl.agh.jtp.lab10;

/**
 * Loads Configuration from XML file.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface ConfigurationLoader {
    /**
     * Loads Configuration object from XML file.
     * Throws IllegalArgumentException when given file doesn't match a schema.
     * @param path Path to XML file.
     * @return Configuration object.
     */
    Configuration loadConfigurationFromFile(String path);
}
