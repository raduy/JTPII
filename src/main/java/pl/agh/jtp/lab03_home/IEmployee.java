package pl.agh.jtp.lab03_home;

import pl.agh.jtp.lab03_home.visitor.Visitable;

import java.math.BigDecimal;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface IEmployee extends Visitable {
    /**
     * @return employee's name
     */
    String getName();

    /**
     * @return employee's role
     */
    String getRole();

    /**
     * @return employee's responsibility chain
     */
    String getResponsibilityChain();

    /**
     * @return employee's work
     */
    String work();

    /**
     * @param manager
     */
    void setSupervisor(IManager manager);

    /**
     * @return supervisor of employee
     */
    IManager getSupervisor();

    /**
     * @return description of employee in format: [name, role, number of subordinates].
     */
    String getDescription();

    /**
     * @return employee's salary
     */
    BigDecimal getSalary();
}
