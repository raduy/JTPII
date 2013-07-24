package pl.agh.jtp.lab03_home;

import pl.agh.jtp.lab03_home.employmentStrategy.EmploymentStrategy;
import pl.agh.jtp.lab03_home.visitor.EmployeeCountVisitor;
import pl.agh.jtp.lab03_home.visitor.Visitable;
import pl.agh.jtp.lab03_home.visitor.Visitor;

import java.util.*;

/**
 * Class represents simple Company structure
 *
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Company extends AbstractCollection<IEmployee> implements Collection<IEmployee>, Visitable {
    private IManager ceo;
    private final EmploymentStrategy employmentStrategy;

    public Company(IManager ceo, EmploymentStrategy employmentStrategy) {
        this.employmentStrategy = employmentStrategy;
        this.ceo = ceo;
    }

    void hireCEO(IManager manager) {
        if(ceo == null) {
            ceo = manager;
        } else  {
            throw new CEOAlreadyHiredException("CEO is already hired!");
        }
    }

    void fireCEO() {
        if(ceo == null) {
            throw new RuntimeException("No CEO to fire!");
        } else {
            ceo = null;
        }
    }

    public IManager getCEO() {
        return ceo;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(!(o instanceof Company)) {
            return false;
        }

        Company company = (Company) o;

        if(!getCEO().equals((company.getCEO()))) {
            return false;
        }
        if(!employmentStrategy.equals(company.employmentStrategy)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = ceo.hashCode();
        result = result + 31 * employmentStrategy.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
               "CEO=" + ceo +
               '}';
    }

    @Override
    public int size() {
        EmployeeCountVisitor visitor = new EmployeeCountVisitor();
        ceo.accept(visitor);

        return visitor.getCountOfEmployee();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     *
     * @return Iterator of employees in DFS order.
     */
    @Override
    public Iterator<IEmployee> iterator() {
        return new CompanyIterator(this);
    }

    @Override
    public boolean add(IEmployee employee) {
        IManager manager = employmentStrategy.chooseManger(this, employee);
        if(manager != null) {
            manager.hire(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Use fire() method to fire employees");
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        throw new UnsupportedOperationException("Use fire() method to fire employees");
    }

    @Override
    public void accept(Visitor visitor) {
        getCEO().accept(visitor);
    }
}
