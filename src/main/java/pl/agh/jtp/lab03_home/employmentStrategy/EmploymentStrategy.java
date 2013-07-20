package pl.agh.jtp.lab03_home.employmentStrategy;

import pl.agh.jtp.lab03_home.Company;
import pl.agh.jtp.lab03_home.IEmployee;
import pl.agh.jtp.lab03_home.IManager;

/**
 * Interface for strategy of employment in the Company
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface EmploymentStrategy {

    /**
     *
     * @param company
     * @param employee
     * @return Company's manger which can hire employee.
     */
    IManager chooseManger(Company company, IEmployee employee);
}
