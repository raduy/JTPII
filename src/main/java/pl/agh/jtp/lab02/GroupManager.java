package pl.agh.jtp.lab02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class GroupManager extends AbstractEmployee implements IManager {

    private final List<IEmployee> employees = new ArrayList<IEmployee>();
    private final int maxNumberOfEmployee;

    /**
     * @param name
     * @param role
     * @param maxNumberOfEmployee
     */
    public GroupManager(String name, String role, int maxNumberOfEmployee) {
        super(name, role);
        this.maxNumberOfEmployee = maxNumberOfEmployee;
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
        if(employees.contains(employee)) {
            return false;
        }

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
        return employees.remove(employee);
    }

    /**
     * @return if manager canHire another employee
     */
    @Override
    public boolean canHire(IEmployee employee) {
        return employees.size() < maxNumberOfEmployee;
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
        return "[" + getName() + ", " + getRole() + ", " + employees.size() + "]";
    }

    @Override
    public Iterator<IEmployee> iterator() {
        return new EmployeeIterator(employees);
    }
}
