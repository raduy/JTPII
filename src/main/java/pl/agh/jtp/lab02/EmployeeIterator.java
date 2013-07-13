package pl.agh.jtp.lab02;

import java.util.Iterator;
import java.util.List;

class EmployeeIterator implements Iterator<IEmployee> {
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