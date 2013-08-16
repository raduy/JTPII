package pl.agh.jtp.lab06;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Compare objects by the property field.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PropertyBasedComparator implements Comparator {
    private final String property;

    /**
     * Constructor
     * @param property Name of property field inside object.
     */
    public PropertyBasedComparator(String property) {
        this.property = property;
    }

    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException - if arguments have different class
     * or there are no such filed in arguments objects or property field have different type
     * or it isn't possible to get access to field.
     */
    @Override
    public int compare(Object o1, Object o2) {
        if(!(o1.getClass().equals(o2.getClass()))) {
            throw new IllegalArgumentException("Your args should be the same class.");
        }

        try {
            Field propertyFieldO1 = o1.getClass().getDeclaredField(property);
            Field propertyFieldO2 = o2.getClass().getDeclaredField(property);

            if(!(propertyFieldO1.getType().equals(propertyFieldO2.getType()))) {
                throw new IllegalArgumentException("Your args properties should be the same type.");
            }

            if(!propertyFieldO1.isAccessible()) {
                propertyFieldO1.setAccessible(true);
                propertyFieldO2.setAccessible(true);
            }

            Object property1 = propertyFieldO1.get(o1);
            Object property2 = propertyFieldO2.get(o2);

            return ((Comparable) property1).compareTo(property2);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("You args don't have such property");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Can't get access to given property");
        }
    }
}
