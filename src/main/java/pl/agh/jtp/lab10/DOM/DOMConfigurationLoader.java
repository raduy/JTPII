package pl.agh.jtp.lab10.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.agh.jtp.lab10.Configuration;
import pl.agh.jtp.lab10.ConfigurationLoader;
import pl.agh.jtp.lab10.Figure;
import pl.agh.jtp.lab10.SchemaValidator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class DOMConfigurationLoader implements ConfigurationLoader {

    private static final Logger LOGGER = Logger.getLogger(DOMConfigurationLoader.class.getName());

    /**
     * {@inheritDoc}
     */
    public Configuration loadConfigurationFromFile(String path) {
        if(!SchemaValidator.validate(path)) {
            LOGGER.log(Level.SEVERE, "File " + path + " hasn't got a valid schema!");
            throw new IllegalArgumentException("Given file doesn't match a schema !");
        }

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

        try {
            Document document = builderFactory.newDocumentBuilder()
                                        .parse(new File(path));

            return createConfigurationBasedOnDomDocument(document);

        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, "DocumentBuilder instantiation problem", e);
        } catch (SAXException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "I/O problem during file parsing", e);
        }
        return null;
    }

    private Configuration createConfigurationBasedOnDomDocument(Document document) {
        NodeList root = document.getChildNodes();

        Node configurationNode = getNode("configuration", root);

        Node figuresNode = getNode("figures", configurationNode.getChildNodes());
        NodeList figuresNodeChildNodes = figuresNode.getChildNodes();

        Configuration result = new Configuration();
        Node n;
        for(int i = 0; i < figuresNodeChildNodes.getLength(); i++) {
            n = figuresNodeChildNodes.item(i);
            if(!"figure".equals(n.getNodeName())) {
                continue;
            }

            Node figureNode = n;

            Figure figure = new Figure();
            figure.setId(getNodeAttributes("id", figureNode));

            NodeList figureNodeFields = figureNode.getChildNodes();

            figure.setClazz(getNodeValue("class", figureNodeFields));
            figure.setHint(getNodeValue("hint", figureNodeFields));
            figure.setName(getNodeValue("name", figureNodeFields));
            figure.setIcon(getNodeValue("icon", figureNodeFields));

            result.addFigure(figure);
        }

        return result;
    }

    protected Node getNode(String tagName, NodeList nodes) {
        for(int x = 0; x < nodes.getLength(); x++ ) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }

    protected String getNodeValue(Node node) {
        NodeList childNodes = node.getChildNodes();
        for(int x = 0; x < childNodes.getLength(); x++ ) {
            Node data = childNodes.item(x);
            if ( data.getNodeType() == Node.TEXT_NODE )
                return data.getNodeValue();
        }

        return "";
    }

    protected String getNodeValue(String tagName, NodeList nodes) {
        for(int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node data = childNodes.item(y);
                    if (data.getNodeType() == Node.TEXT_NODE)
                        return data.getNodeValue();
                }
            }
        }

        return "";
    }

    protected String getNodeAttributes(String attrName, Node node) {
        NamedNodeMap attributes = node.getAttributes();
        for (int y = 0; y < attributes.getLength(); y++) {
            Node attr = attributes.item(y);
            if (attr.getNodeName().equalsIgnoreCase(attrName)) {
                return attr.getNodeValue();
            }
        }

        return "";
    }

    protected String getNodeAttributes(String tagName, String attrName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node data = childNodes.item(y);
                    if (data.getNodeType() == Node.ATTRIBUTE_NODE) {
                        if ( data.getNodeName().equalsIgnoreCase(attrName) )
                            return data.getNodeValue();
                    }
                }
            }
        }

        return "";
    }
}