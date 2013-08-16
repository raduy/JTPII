package lab06Tests;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.lab06.URLStringManipulatorProxy;
import pl.agh.jtp.sm.IStringManipulator;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class URLStringManipulatorProxyTest {
    private URLStringManipulatorProxy instance;

    @Before
    public void setUp() throws Exception {
        instance = new URLStringManipulatorProxy("Sample text");
    }

    @Test
    public void proxyShouldCreateWorkingObject() throws Exception {
        //given
        IStringManipulator stringManipulator = instance;

        //when
        String manipulatedString = stringManipulator.manipulate();

        //then
        assertEquals("txet elpmaS", manipulatedString);
    }

    @Test
    public void proxyShouldHaveALazyLoading() throws Exception {
        //given
        IStringManipulator stringManipulator = instance;

        //when (before manipulate method call)
        Field stringManipulatorField = instance.getClass().getDeclaredField("stringManipulator");
        if(!stringManipulatorField.isAccessible()) {
            stringManipulatorField.setAccessible(true);
        }

        //then
        assertNull(stringManipulatorField.get(instance));
        //when (after manipulate method call)
        stringManipulator.manipulate();
        //then
        assertNotNull(stringManipulatorField.get(instance));
    }
}
