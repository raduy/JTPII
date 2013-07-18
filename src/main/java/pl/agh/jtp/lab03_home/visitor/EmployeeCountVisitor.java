package pl.agh.jtp.lab03_home.visitor;

import pl.agh.jtp.lab03_home.IEmployee;

/**
 * Very simple implementation of Visitor that count employees in company.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class EmployeeCountVisitor implements Visitor {
    private int counter;

    @Override
    public void visit(IEmployee employee) {
        counter++;
    }

    @Override
    public void goLevelDown() {
    }

    @Override
    public void goLevelUp() {
    }

    public int getCountOfEmployee() {
        return counter;
    }
}
