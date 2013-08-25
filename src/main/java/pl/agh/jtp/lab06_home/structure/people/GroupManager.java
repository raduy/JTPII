package pl.agh.jtp.lab06_home.structure.people;

import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.structure.IManager;
import pl.agh.jtp.lab06_home.hireStrategy.HireStrategy;
import pl.agh.jtp.lab06_home.structure.EmployeeIterator;
import pl.agh.jtp.lab06_home.visitor.Visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class GroupManager extends AbstractEmployee implements IManager, Iterable<IEmployee> {

    private final List<IEmployee> employees = new ArrayList<IEmployee>();
    private HireStrategy hireStrategy;

    /**
     *
     * @param name
     * @param role
     * @param hireStrategy
     */
    public GroupManager(String name, String role, HireStrategy hireStrategy) {
        super(name, role);
        this.hireStrategy = hireStrategy;
        setSalary(BigDecimal.valueOf(10000));
    }

    public GroupManager(String name, String role, HireStrategy hireStrategy, BigDecimal salary) {
        super(name, role);
        this.hireStrategy = hireStrategy;
        setSalary(salary);
    }

    /**
     * @param employee to be hired
     * @return
     */
    @Override
    public boolean hire(IEmployee employee) {
        if(! canHire(employee)) {
            return false;
        }
//        if(employees.contains(employee)) {
//            return false;
//        }

        employees.add(employee);
        employee.setSupervisor(this);
        return true;
    }

    /**
     * @param employee to be fired
     * @return
     */
    @Override
    public boolean fire(IEmployee employee) {
        employee.setSupervisor(null);
        return employees.remove(employee);
    }

    /**
     * @return if manager canHire another employee
     */
    @Override
    public boolean canHire(IEmployee employee) {
        return hireStrategy.canHire(this);
    }

    /**
     * @return work specification of the manager team
     */
    @Override
    public String work() {
        if (employees.size() == 0) {
            return getName() + " has nobody to manage. He works alone";
        }
        StringBuilder work = new StringBuilder();
        work.append(getName() + " is working with his team:");
        StringBuilder subordinatesWork = new StringBuilder();
        for (IEmployee subordinate : employees) {
            subordinatesWork.append("\n|---")
                            .append(subordinate.work());
        }
        work.append(subordinatesWork.toString().replaceAll("\n(^|)", "\n|---"));
        return work.toString();
    }

    @Override
    public String getDescription() {
        return "[" + getName() + ", " + getRole() + ", " + employees.size() + ", ID: " + getID() + "]";
    }

    @Override
    public Iterator<IEmployee> iterator() {
        return new EmployeeIterator(employees);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for(IEmployee employee : employees) {
            employee.accept(visitor);
        }
    }
}
