package pl.agh.jtp.lab03_home.visitor;

import pl.agh.jtp.lab03_home.Developer;
import pl.agh.jtp.lab03_home.GroupManager;
import pl.agh.jtp.lab03_home.Tester;

/**
 * Very simple implementation of Visitor that count employees in company.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class EmployeeCountVisitor implements Visitor {
    private int counter;

    @Override
    public void visit(Developer developer) {
        counter++;
    }

    @Override
    public void visit(GroupManager groupManager) {
        counter++;
    }

    @Override
    public void visit(Tester tester) {
        counter++;
    }

    public int getCountOfEmployee() {
        return counter;
    }
}
