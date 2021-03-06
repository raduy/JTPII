package pl.agh.jtp.lab06_home.hirestrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.agh.jtp.lab06_home.domain.IEmployee;
import pl.agh.jtp.lab06_home.domain.IManager;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Hire strategy based on only how big is total salary for all direct manager's subordinates.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class BudgetBasedHireStrategy implements HireStrategy {
    private final static long serialVersionUID = 1L;
    private final BigDecimal maxTotalSalary;
    private final static Logger LOGGER = LoggerFactory.getLogger(BudgetBasedHireStrategy.class);

    /**
     * Parameter defines maximum total salary for all direct manager's subordinates
     * @param maxTotalSalary
     */
    public BudgetBasedHireStrategy(BigDecimal maxTotalSalary) {
        if(maxTotalSalary.signum() < 0) {
            throw new IllegalArgumentException("No pay no work!");
        }
        this.maxTotalSalary = maxTotalSalary;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canHire(IManager manager) {
        BigDecimal totalSalary = BigDecimal.ZERO;
        for(Iterator<IEmployee> it = manager.iterator(); it.hasNext();) {
            totalSalary = totalSalary.add(it.next().getSalary());
        }

        LOGGER.info("totalSalary: " + totalSalary.toString());
        return maxTotalSalary.compareTo(totalSalary) > 0;
    }
}
