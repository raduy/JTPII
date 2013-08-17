package lab06Tests;

import org.junit.Before;
import org.junit.Test;
import pl.agh.jtp.sm.IStringManipulator;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.*;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class URLClassLoaderTest {
    private URLClassLoader loaderInstance1;
    private URLClassLoader loaderInstance2;

    @Before
    public void setUp() {
        URL[] urls = new URL[1];
        URL url;
        try {
            url = new URL("http://student.agh.edu.pl/~mpartyka/sm-impl-1.0.jar");
            urls[0] = url;
        } catch (MalformedURLException e) {
            fail("Bad URL in setUp method!!!");
        }

        loaderInstance1 = new URLClassLoader(urls);
        loaderInstance2 = new URLClassLoader(urls);
    }

    @Test
    public void classesShouldBeEqualsWhenTestedWithEqualsMethod() {
        //given
        Class<?> loadedClass1 = null;
        Class<?> loadedClass2 = null;

        //when
        try {
            loadedClass1 = loaderInstance1.loadClass("pl.agh.jtp.sm.StringManipulator");
            loadedClass2 = loaderInstance2.loadClass("pl.agh.jtp.sm.StringManipulator");
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }

        //then
        assertTrue(loadedClass1.equals(loadedClass2));
    }

    @Test
    public void classesShouldBeEqualsWhenTestedWithDoubleEqualsSign() {
        //given
        Class<?> loadedClass1 = null;
        Class<?> loadedClass2 = null;

        //when
        try {
            loadedClass1 = loaderInstance1.loadClass("pl.agh.jtp.sm.StringManipulator");
            loadedClass2 = loaderInstance2.loadClass("pl.agh.jtp.sm.StringManipulator");
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }

        //then
        assertTrue(loadedClass1 == loadedClass2);
    }

    @Test
    public void shouldBePossibleToInstantiateStringManipulator() {
        //given
        IStringManipulator stringManipulator = null;

        Class<?> type = null;
        try {
            type = loaderInstance1.loadClass("pl.agh.jtp.sm.StringManipulator");
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }

        //when
        try {
            stringManipulator = (IStringManipulator) type.getConstructor(String.class).newInstance("Sample text");
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            fail(e.getMessage());
        }

        //then
        //try to invoke some method
        assertEquals("txet elpmaS", stringManipulator.manipulate());
    }
}
