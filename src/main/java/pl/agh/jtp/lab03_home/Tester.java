package pl.agh.jtp.lab03_home;

import pl.agh.jtp.lab03_home.visitor.Visitor;

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
    public String toString() {
        return "Tester with the name: " + getName() + " and role: " + getRole();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
