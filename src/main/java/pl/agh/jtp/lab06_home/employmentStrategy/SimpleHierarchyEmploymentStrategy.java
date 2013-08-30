package pl.agh.jtp.lab06_home.employmentstrategy;

import pl.agh.jtp.lab06_home.domain.Company;
import pl.agh.jtp.lab06_home.domain.IEmployee;
import pl.agh.jtp.lab06_home.domain.IManager;

/**
 * <pre>
 * This strategy create a simple employee hierarchy:
 * Ceo manage some managers, each manager can have some subordinates or not.
 * Any manager, except CEO, can't have any other manager under himself.
 *| ----------------------- Ceo -----------------------(...)|
 *| -- Manager1 --  |  -- Manager2 -- | -- Manager3 -- (...)|
 *| D1 | D2 | (...) | D1 |  T1  |  D2 | -                   |
 * </pre>
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SimpleHierarchyEmploymentStrategy implements EmploymentStrategy {
    private final static long serialVersionUID = 1L; //should we serialize object which doesn't have a state ?

    @Override
    public IManager chooseManger(Company company, IEmployee employee) {
        if(employee instanceof IManager) {
            IManager manager = (IManager) employee;
            if(company.getCEO().canHire(manager)) {
                return company.getCEO();
            }
            return null;
        }

        for (IEmployee iEmployee : company.getCEO()) {
            if (iEmployee instanceof IManager) {
                IManager manager = (IManager) iEmployee;
                if (manager.canHire(employee)) {
                    return manager;
                }
            }
        }
        return null;
    }
}
