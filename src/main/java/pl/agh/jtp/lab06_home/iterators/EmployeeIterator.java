package pl.agh.jtp.lab06_home.iterators;

import pl.agh.jtp.lab06_home.domain.IEmployee;

import java.util.Iterator;
import java.util.List;

public class EmployeeIterator implements Iterator<IEmployee> {
    private final List<IEmployee> list;
    private final Iterator<IEmployee> iterator;

    public EmployeeIterator(List<IEmployee> list) {
        this.list = list;
        this.iterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public IEmployee next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("You can't remove employees using iterator");
    }
}