package pl.agh.jtp.lab03_home.visitor;

import pl.agh.jtp.lab03_home.IEmployee;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Visitor {
    /**
     *
     * @param employee
     */
    void visit(IEmployee employee);

    /**
     * Tell visitor to go one level down the company tree.
     */
    void goLevelDown();

    /**
     * Tell visitor to go one level up the company tree.
     */
    void goLevelUp();
}
