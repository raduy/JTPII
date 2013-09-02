package pl.agh.jtp.lab10.SAX;

import org.xml.sax.SAXException;
import pl.agh.jtp.lab10.Configuration;
import pl.agh.jtp.lab10.ConfigurationLoader;
import pl.agh.jtp.lab10.SchemaValidator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Loads Configuration from XML file.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SAXConfigurationLoader implements ConfigurationLoader {

    private static final Logger LOGGER = Logger.getLogger(SAXConfigurationLoader.class.getName());

    /**
     * {@inheritDoc}
     */
    public Configuration loadConfigurationFromFile(String path) {
        if(!SchemaValidator.validate(path)) {
            LOGGER.log(Level.SEVERE, "File " + path + " hasn't got a valid schema!");
            throw new IllegalArgumentException("Given file doesn't match a schema !");
        }

        try {
            SAXParser saxParser = SAXParserFactory.newInstance()
                                                  .newSAXParser();
            SAXHandler handler = new SAXHandler();
            saxParser.parse(new File(path), handler);
            return handler.getConfiguration();

        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, "Configuration problem during parser creating", e);
        } catch (SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage() , e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "I/O problem during file parsing", e);
        }

        return null;
    }
}
