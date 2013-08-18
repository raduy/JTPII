package pl.agh.jtp.lab06_home;

import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.structure.IManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
* Iterator return employees in Deep First Search order.
* @author Lukasz Raduj <raduj.lukasz@gmail.com>
*/

public class CompanyIterator implements Iterator<IEmployee> {
    private final LinkedList<Iterator<IEmployee>> stack = new LinkedList<Iterator<IEmployee>>();
    private Iterator<IEmployee> current;

    public CompanyIterator(Company company) {
        current = company.getCEO().iterator();
    }

    @Override
    public boolean hasNext() {
        return (!stack.isEmpty()) || (current.hasNext());
    }

    @Override
    public IEmployee next() {
        if(current.hasNext()) {
            IEmployee employee = current.next();
            if(employee instanceof IManager) {
                IManager manager = (IManager) employee;
                if(current.hasNext()) {
                    push(current);
                }
                current = manager.iterator();
                return manager;
            }
            return employee;
        } else {
            if(!stack.isEmpty()) {
                current = pop();
                return next();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Use fire() method to remove employee");
    }

    private void push(Iterator<IEmployee> iterator) {
        stack.add(iterator);
    }

    private Iterator<IEmployee> pop() {
        return stack.pollLast();
    }

}
