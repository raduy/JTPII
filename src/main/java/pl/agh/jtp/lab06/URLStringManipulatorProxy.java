package pl.agh.jtp.lab06;

import pl.agh.jtp.sm.IStringManipulator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Implementation of Proxy structural pattern for class StringManipulator.
 * Creates object on demand (when manipulate method is call)
 * StringManipulator class must be under URL address:
 * http://student.agh.edu.pl/~mpartyka/sm-impl-1.0.jar
 * in package:
 * pl.agh.jtp.sm.StringManipulator
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class URLStringManipulatorProxy implements IStringManipulator {
    private Class<?> stringManipulatorType;
    private final String textForConstructor;
    private URLClassLoader urlClassLoader;
    private IStringManipulator stringManipulator;

    public URLStringManipulatorProxy(String text) {
        textForConstructor = text;
    }

    @Override
    public String manipulate() {
        if(stringManipulator == null) {
            loadClassFromURL();
        }
        return stringManipulator.manipulate();
    }

    private void loadClassFromURL() {
        try {
            createLoader();
            stringManipulatorType = urlClassLoader.loadClass("pl.agh.jtp.sm.StringManipulator");
            Constructor constructor = stringManipulatorType.getConstructor(String.class);
            stringManipulator = (IStringManipulator) constructor.newInstance(textForConstructor);

        } catch (MalformedURLException | InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Someone corrupt names, or URL is dead :/");
        }
    }

    private void createLoader() throws MalformedURLException {
        URL url = new URL("http://student.agh.edu.pl/~mpartyka/sm-impl-1.0.jar");
        URL[] urls = new URL[]{url};

        urlClassLoader = new URLClassLoader(urls);
    }
}
