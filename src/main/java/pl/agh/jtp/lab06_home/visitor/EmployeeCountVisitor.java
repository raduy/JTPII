package pl.agh.jtp.lab06_home.visitor;

import pl.agh.jtp.lab06_home.domain.people.Developer;
import pl.agh.jtp.lab06_home.domain.people.GroupManager;
import pl.agh.jtp.lab06_home.domain.people.Tester;

/**
 * Very simple implementation of Visitor that count employees in company.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class EmployeeCountVisitor implements Visitor {
    private int developersCounter;
    private int testersCounter;
    private int groupManagersCounter;

    @Override
    public void visit(Developer developer) {
        developersCounter++;
    }

    @Override
    public void visit(GroupManager groupManager) {
        groupManagersCounter++;
    }

    @Override
    public void visit(Tester tester) {
        testersCounter++;
    }

    public int getCountOfEmployee() {
        return developersCounter + testersCounter + groupManagersCounter;
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
