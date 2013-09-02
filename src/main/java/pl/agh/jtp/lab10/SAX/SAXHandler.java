package pl.agh.jtp.lab10.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import pl.agh.jtp.lab10.Configuration;
import pl.agh.jtp.lab10.Figure;

/**
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SAXHandler extends DefaultHandler {
    private Figure figure;
    private StringBuilder temp;
    private Configuration configuration;
    private Locator locator = null;

    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    @Override
    public void characters(char[] buffer, int start, int length) throws SAXException {
        temp.append(buffer, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        temp = new StringBuilder();   //clear buffer

        if(qName.equalsIgnoreCase("configuration")) {
            configuration = new Configuration();
            return;
        }

        if(configuration == null) {
            throw new SAXParseException(" <configuration> marker missing !!", locator);
        }

        if(qName.equalsIgnoreCase("figure")) {
            figure = new Figure();
            figure.setId(attributes.getValue("id"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if("figure".equalsIgnoreCase(qName)) {
            configuration.addFigure(figure);
            return;
        }

        String marker = qName.toLowerCase();
        String value = temp.toString();
        switch (marker) {
            case "name": {
                figure.setName(value);
                break;
            }
            case "class": {
                figure.setClazz(value);
                break;
            }
            case "icon": {
                figure.setIcon(value);
                break;
            }
            case "hint": {
                figure.setHint(value);
                break;
            }
        }
    }

    private String reportPosition() {
        if (locator != null) {
            String publicID = locator.getPublicId();
            String systemID = locator.getSystemId();
            int line = locator.getLineNumber();
            int column = locator.getColumnNumber();

            String name;
            name = (publicID != null ? publicID : systemID);

            //something wrong..(e. g. SAXException)
            return " in " + name + " at line " + line
                    + ", column " + column;
        }
        return "Locator not initialized!";
    }

    public Configuration getConfiguration() {
        if(configuration == null) {
            throw new IllegalStateException("Configuration not loaded yet");
        }

        return configuration;
    }
}
