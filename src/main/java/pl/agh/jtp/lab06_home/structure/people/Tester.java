package pl.agh.jtp.lab06_home.structure.people;

import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.visitor.Visitor;

import java.math.BigDecimal;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Tester extends AbstractEmployee implements IEmployee {

    /**
     *
     * @param name
     * @param role
     */
    public Tester(String name, String role) {
        super(name, role);
        setSalary(BigDecimal.valueOf(5000));
    }

    public Tester(String name, String role, BigDecimal salary) {
        super(name, role);
        setSalary(salary);
    }

    /**
     *
     * @return tester's work
     */
    @Override
    public String work() {
        return "Tester: " + getName() + " is testing code...";
    }

    @Override
    public String getDescription() {
        return "[" + getName() + ", " + getRole() + ", " + 0 + "]";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
