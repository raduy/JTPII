package pl.agh.jtp.lab_04;

/**
 * Contain function apply() which defines some condition.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Predicate<T> {

    boolean apply(T element);
}
