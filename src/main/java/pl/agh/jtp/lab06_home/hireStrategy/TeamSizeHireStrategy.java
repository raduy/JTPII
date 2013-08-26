package pl.agh.jtp.lab06_home.hireStrategy;

import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.structure.IManager;

import java.util.Iterator;

/**
 * Strategy based only on number of direct manager subordinates.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class TeamSizeHireStrategy implements HireStrategy {
    private final static long serialVersionUID = 1L;
    private final int maxNumberOfSubordinates;

    /**
     * Parameter defines how many close subordinates manager can have.
     * @param maxNumberOfSubordinates
     */
    public TeamSizeHireStrategy(int maxNumberOfSubordinates) {
        if(maxNumberOfSubordinates < 0) {
            throw new IllegalArgumentException("Number of subordinates can't be a negative number");
        }
        this.maxNumberOfSubordinates = maxNumberOfSubordinates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canHire(IManager manager) {
        int numberOfSubordinates = 0;
        for(Iterator<IEmployee> it = manager.iterator(); it.hasNext(); it.next()) {
            numberOfSubordinates++;
        }
        return numberOfSubordinates < maxNumberOfSubordinates;
    }
}
