package pl.agh.jtp.lab06_home.structure;

import pl.agh.jtp.lab06_home.visitor.Visitable;

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
     * Warning! Method only set Supervisor, don't add employee to manager's employee list.
     * @param manager  Manger which should be set as supervisor
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

    /**
     * @param newSalary Salary to be set.
     */
    void setSalary(BigDecimal newSalary);

    /**
     * @return employee ID
     */
    public int getID();

 }
