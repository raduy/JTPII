package lab10Tests;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.lab10.Configuration;
import pl.agh.jtp.lab10.SAX.SAXConfigurationLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SAXConfigurationLoaderTest {
    private SAXConfigurationLoader instance;

    @Before
    public void setUp() {
        instance = new SAXConfigurationLoader();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parserShouldThrowExceptionWhenFileDoesntMatchSchema() {
        //given
        Configuration configuration;
        String xmlFilePath = "badconfiguration.xml";

        //when
        configuration = instance.loadConfigurationFromFile(xmlFilePath);

        //then
        fail();
    }

    @Test
    public void parseShouldReturnsCorrectConfigurationWhenXMLIsGood() {
        //given
        String xmlFilePath = "configuration.xml";
        Configuration configuration;

        //when
        configuration = instance.loadConfigurationFromFile(xmlFilePath);

        //then
        assertEquals("Configuration{" +
                "figures=" +
                "[Figure" +
                "{clazz='pl.agh.jtp.fotoshop.figures.Triangle', " +
                "id='triangle', name='Trojkat', " +
                "icon='img/triangle.png', " +
                "hint='Rysuje trojkat'}, " +
                "Figure{clazz='pl.agh.jtp.fotoshop.figures.Circle', " +
                "id='circle', name='Kolo', " +
                "icon='img/circle.png', " +
                "hint='Rysuje kolo'}]}", configuration.toString());
    }
}
