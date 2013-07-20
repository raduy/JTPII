package pl.agh.jtp.lab03_home;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Iterator return employees in Deep First Search order.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CompanyIterator implements Iterator<IEmployee> {
    private final Company company;
    private final LinkedList<Iterator<IEmployee>> stack = new LinkedList<Iterator<IEmployee>>();
    private Iterator<IEmployee> current;

    public CompanyIterator(Company company) {
        this.company = company;
        current = company.getCEO().iterator();
    }

    @Override
    public boolean hasNext() {
        return (stack.isEmpty()) && (!current.hasNext());
    }

    @Override
    public IEmployee next() {
        if(current.hasNext()) {
            IEmployee employee = current.next();
            if(employee instanceof IManager) {
                IManager manager = (IManager) employee;
                if(current.hasNext()) {     //if something last in current iterator put it on the stack, now there are no empty lists on the stack
                    push(current);
                }
                current = manager.iterator();
                return manager;
            }
            return employee;
        } else {
            current = pop();
            next();     //assuming that before every call of next() method hasNext() was call, on the stack there are no empty lists.
        }

        return null;
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
