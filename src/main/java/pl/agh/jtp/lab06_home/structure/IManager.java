package pl.agh.jtp.lab06_home.structure;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface IManager extends IEmployee, Iterable<IEmployee> {
    /**
     * @param employee to be hired
     */
    boolean hire(IEmployee employee);

    /**
     * @param employee to be fired
     */
    boolean fire(IEmployee employee);

    /**
     * @return  if Manager can hire
     */
    boolean canHire(IEmployee employee);
}
