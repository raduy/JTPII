package lab10Tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.agh.jtp.lab10.Configuration;
import pl.agh.jtp.lab10.DOM.DOMConfigurationWriter;
import pl.agh.jtp.lab10.Figure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class DOMConfigurationWriterTest {
    private DOMConfigurationWriter instance;

    @Mock
    Figure figure1;

    @Mock
    Figure figure2;

    @Mock
    Configuration configuration;

    @Before
    public void setUp() throws Exception {
        instance = new DOMConfigurationWriter();
    }

    @Test
    public void writerShouldCreateCorrectXMLFile() throws Exception {
        //given
        when(figure1.getId()).thenReturn("triangle");
        when(figure1.getName()).thenReturn("Trojkat");
        when(figure1.getClazz()).thenReturn("pl.agh.jtp.fotoshop.figures.Triangle");
        when(figure1.getIcon()).thenReturn("img/triangle.png");
        when(figure1.getHint()).thenReturn("Rysuje trojkat");

        when(configuration.getFigures()).thenReturn(Arrays.asList(figure1, figure1));  //too lazy to mock figure2 :-) add figure1 twice.

        //when
        instance.writeConfiguration(configuration, "domwritertest.xml");
        BufferedReader in = new BufferedReader(new FileReader("domwritertest.xml"));
        StringBuilder createdXMLFile = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            createdXMLFile.append(line);
        }

        //then
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
                "<configuration>" +
                    "<figures>" +
                        "<figure id=\"triangle\">" +
                            "<name>Trojkat</name>" +
                            "<class>pl.agh.jtp.fotoshop.figures.Triangle</class>" +
                            "<icon>img/triangle.png</icon>" +
                            "<hint>Rysuje trojkat</hint>" +
                        "</figure>" +
                        "<figure id=\"triangle\">" +
                            "<name>Trojkat</name>" +
                            "<class>pl.agh.jtp.fotoshop.figures.Triangle</class>" +
                            "<icon>img/triangle.png</icon>" +
                            "<hint>Rysuje trojkat</hint>" +
                        "</figure>" +
                    "</figures>" +
                "</configuration>", createdXMLFile.toString());
    }
}
