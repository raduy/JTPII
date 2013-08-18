package pl.agh.jtp.lab06_home.employmentStrategy;

import pl.agh.jtp.lab06_home.Company;
import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.structure.IManager;

import java.io.Serializable;

/**
 * Interface for strategy of employment in the Company.
 * Defines a strategy of employment for new employees.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface EmploymentStrategy extends Serializable {

    /**
     * Method return first manager which can hire new employee or null when there are no free vacant positions.
     * @param company
     * @param employee
     * @return Company's manger which can hire employee or null when there are any vacant.
     */
    IManager chooseManger(Company company, IEmployee employee);
}
