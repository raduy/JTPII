package pl.agh.jtp.lab06_home;

import pl.agh.jtp.lab06_home.structure.IEmployee;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Context {
    private final Company company;
    private final IEmployee currentEmployee;

    public Context(Company company, IEmployee employee) {
        this.company = company;
        this.currentEmployee = employee;
    }

    /**
     * @return company that is currently processed by application
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @return Currently selected employee int the company
     */
    public IEmployee getCurrentEmployee() {
        return currentEmployee;
    }

    @Override
    public String toString() {
        return "Context{" +
                "company=" + company +
                ", currentEmployee=" + currentEmployee +
                '}';
    }
}
