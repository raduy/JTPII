package pl.agh.jtp.lab06;

import pl.agh.jtp.sm.IStringManipulator;
import pl.agh.jtp.sm.StringManipulator;

/**
 * <pre>
 * Implementation of Proxy structural pattern for class StringManipulator.
 * Creates object on demand (when manipulate method is call).
 * StringManipulator class must be on CLASSPATH.
 * </pre>
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class ClassPathStringManipulatorProxy implements IStringManipulator {

    private StringManipulator stringManipulator;
    private final String stringForConstructor;


    public ClassPathStringManipulatorProxy(String stringForConstructor) {
        this.stringForConstructor = stringForConstructor;
    }

    /**
     * Method returns manipulated string given in Proxy constructor.
     *
     * @return Manipulated string
     */
    @Override
    public String manipulate() {
        if (stringManipulator == null) {
            stringManipulator = new StringManipulator(stringForConstructor);
        }
        return stringManipulator.manipulate();
    }
}
