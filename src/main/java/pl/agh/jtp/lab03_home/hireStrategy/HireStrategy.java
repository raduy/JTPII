package pl.agh.jtp.lab03_home.hireStrategy;

import pl.agh.jtp.lab03_home.IManager;

/**
 * Basic interface of Strategy Pattern
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface HireStrategy {

    /**
     *
     * @param manager
     * @return whether manager can hire another employee
     */
    boolean canHire(IManager manager);
}
