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
    private int developersCounter;
    private int testersCounter;
    private int groupManagersCounter;

    @Override
    public void visit(Developer developer) {
        developersCounter++;
        counter++;
    }

    @Override
    public void visit(GroupManager groupManager) {
        groupManagersCounter++;
        counter++;
    }

    @Override
    public void visit(Tester tester) {
        testersCounter++;
        counter++;
    }

    public int getCountOfEmployee() {
        return counter;
    }

    public int getCountOfDevelopers() {
        return developersCounter;
    }

    public int getCountOfTesters() {
        return testersCounter;
    }

    public int getCountOfManagers() {
        return groupManagersCounter;
    }
}
