package pl.agh.jtp.lab06_home.visitor;

import pl.agh.jtp.lab06_home.domain.people.Developer;
import pl.agh.jtp.lab06_home.domain.people.GroupManager;
import pl.agh.jtp.lab06_home.domain.people.Tester;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Visitor {

    void visit(GroupManager groupManager);

    void visit(Developer developer);

    void visit(Tester tester);
}
