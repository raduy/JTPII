package pl.agh.jtp.lab03_home;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This iterator returns next elements matching the test from Predicate.
 * It's a decorator(using composition) of iterator given in constructor.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class PredicateIterator<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Predicate<T> predicate;
    private T buffer;

    public PredicateIterator(Iterator<T> iterator, Predicate<T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
        updateBuffer();
    }

    @Override
    public boolean hasNext() {
        return buffer != null;
    }

    @Override
    public T next() {
        if(hasNext()) {
            T result = buffer;
            updateBuffer();
            return result;
        }

        throw new NoSuchElementException("No more elements matching!");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("You can't remove employees using iterator");
    }

    private void updateBuffer() {
        buffer = null;
        while (!predicate.apply(buffer) && iterator.hasNext()) {
            buffer = iterator.next();
        }
    }

}
