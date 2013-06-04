package pl.agh.jtp.lab02_home;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface IEmployee {
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
     * @return employee's salary
     */
    //BigInteger getSalary();
}
