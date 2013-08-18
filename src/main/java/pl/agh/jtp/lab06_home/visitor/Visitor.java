package pl.agh.jtp.lab06_home.visitor;

import pl.agh.jtp.lab06_home.structure.people.Developer;
import pl.agh.jtp.lab06_home.structure.people.GroupManager;
import pl.agh.jtp.lab06_home.structure.people.Tester;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Visitor {

    void visit(GroupManager groupManager);

    void visit(Developer developer);

    void visit(Tester tester);
}
