package pl.agh.jtp.lab10;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use it to valid your xml file.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SchemaValidator {
    private static final Logger LOGGER = Logger.getLogger(SchemaValidator.class.getName());
    private static File schemaFile = new File("schema.xsd");

    public static boolean validate(String xmlFilePath) {
        Source xmlSource = new StreamSource(new File(xmlFilePath));
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();

            validator.validate(xmlSource);
        } catch (SAXException e) {
            LOGGER.log(Level.SEVERE, "XML file hasn't got valid schema!", e);
            return false;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO Exception while reading file: " + xmlFilePath, e);
            return false;
        }
        return true;
    }

    /**
     * Set schema XSD file which will be use to validate XML file.
     * @param schemaFile Schema file.
     */
    public static void setSchemaFile(File schemaFile) {
        SchemaValidator.schemaFile = schemaFile;
    }

    public static File getSchemaFile() {
        return schemaFile;
    }
}
