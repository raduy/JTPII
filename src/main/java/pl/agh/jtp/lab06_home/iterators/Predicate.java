package pl.agh.jtp.lab06_home.iterators;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Predicate<T> {

    boolean apply(T element);
}
