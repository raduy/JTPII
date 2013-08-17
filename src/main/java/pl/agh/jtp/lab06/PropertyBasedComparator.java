package pl.agh.jtp.lab06;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

/**
 * Compare objects by the property.
 * Object has a property when have a method getProperty()
 * E.g.: when there are a method getName() property is name.
 *
 * @param <T>
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PropertyBasedComparator<T> implements Comparator<T> {
    private String propertyMethodName;

    public PropertyBasedComparator(String property) {
        createPropertyMethodName(property);
    }

    private void createPropertyMethodName(String property) {
        propertyMethodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchcked") //is safe because before casting call there are an if control statement.
    public int compare(T t1, T t2) {


        Object property1;
        Object property2;
        try {
            property1 = loadProperty(t1);
            property2 = loadProperty(t2);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("There are no such method with given property.");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("The underlying method thrown an exception.");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Can't get access to property method.");
        }
        if (!(property1 instanceof Comparable)) {
            throw new IllegalArgumentException("Can't compare given objects - they don't implements Comparable interface");
        }
        return ((Comparable) property1).compareTo(property2);
    }

    private Object loadProperty(T object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return object.getClass().getMethod(propertyMethodName).invoke(object);
    }
}
