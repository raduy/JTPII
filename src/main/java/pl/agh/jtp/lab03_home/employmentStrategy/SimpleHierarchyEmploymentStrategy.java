package pl.agh.jtp.lab03_home.employmentStrategy;

import pl.agh.jtp.lab03_home.Company;
import pl.agh.jtp.lab03_home.IEmployee;
import pl.agh.jtp.lab03_home.IManager;

import java.util.Iterator;

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

    @Override
    public IManager chooseManger(Company company, IEmployee employee) {
        if(employee instanceof IManager) {
            IManager manager = (IManager) employee;
            if(company.getCEO().canHire(manager)) {
                return company.getCEO();
            }
            return null;
        }

        Iterator<IEmployee> employeeIterator = company.getCEO().iterator();
        while(employeeIterator.hasNext()) {
            IEmployee empl = employeeIterator.next();
            if(empl instanceof IManager) {
                IManager manager = (IManager) empl;
                if(manager.canHire(employee)) {
                    return manager;
                }
            }
        }
        return null;
    }
}
