package pl.agh.jtp.lab06_home.hireStrategy;

import pl.agh.jtp.lab06_home.structure.IManager;

import java.io.Serializable;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface HireStrategy extends Serializable {

    /**
     * Defines whether given manager can hire another employee.
     * @param manager
     * @return whether manager can hire another employee
     */
    boolean canHire(IManager manager);
}
