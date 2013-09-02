package pl.agh.jtp.lab10.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pl.agh.jtp.lab10.Configuration;
import pl.agh.jtp.lab10.Figure;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Save given Configuration object into XML file.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class DOMConfigurationWriter {

    private static final Logger LOGGER = Logger.getLogger(DOMConfigurationWriter.class.getName());

    /**
     * Creates and writes XML document based on Configuration object.
     *
     * @param configuration Configuration object to write.
     * @param pathForXmlFile  Creates XML file on this path.
     * @return True if method finish with success and file was saved, false when operation failed.
     */
    public boolean writeConfiguration(Configuration configuration, String pathForXmlFile) {

        Document document;
        try {
            document = DocumentBuilderFactory.newInstance()
                                             .newDocumentBuilder()
                                             .newDocument();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, "Problem during Document object instantiating!", e);
            return false;
        }

        Element configurationElement = document.createElement("configuration");
        document.appendChild(configurationElement); //root element

        Element figuresElement = document.createElement("figures");
        configurationElement.appendChild(figuresElement);

        for(Figure figure : configuration.getFigures()) {
            figuresElement.appendChild(createFigureElement(document, figure));
        }

        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance()
                                            .newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File(pathForXmlFile));

            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            LOGGER.log(Level.SEVERE, "Problem during Transformer object instantiating", e);
            return false;
        }

        return true;
    }

    private Element createFigureElement(Document document, Figure figure) {
        Element figureElement = document.createElement("figure");
        figureElement.setAttribute("id", figure.getId());

        figureElement.appendChild(createPropertyElement("name", figure.getName(), document));
        figureElement.appendChild(createPropertyElement("class", figure.getClazz(), document));
        figureElement.appendChild(createPropertyElement("icon", figure.getIcon(), document));
        figureElement.appendChild(createPropertyElement("hint", figure.getHint(), document));

        return figureElement;
    }

    private Element createPropertyElement(String propertyName, String propertyValue, Document document) {
        Element propertyElement = document.createElement(propertyName);
        propertyElement.appendChild(document.createTextNode(propertyValue));
        return propertyElement;
    }
}
