package pl.agh.jtp.lab03_home.visitor;

import pl.agh.jtp.lab03_home.Developer;
import pl.agh.jtp.lab03_home.GroupManager;
import pl.agh.jtp.lab03_home.Tester;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public interface Visitor {

    void visit(GroupManager groupManager);

    void visit(Developer developer);

    void visit(Tester tester);
}
