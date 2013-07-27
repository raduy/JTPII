package pl.agh.jtp.lab03_home;

import pl.agh.jtp.lab03_home.visitor.Visitor;

import java.math.BigDecimal;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Developer extends AbstractEmployee implements IEmployee {

    /**
     *
     * @param name
     * @param role
     */
    public Developer(String name, String role, BigDecimal salary) {
        super(name, role);
        setSalary(salary);
    }

    public Developer(String name, String role) {
        super(name, role);
        setSalary(BigDecimal.valueOf(5000));
    }

    @Override
    public String work() {
        return "Developer: " + getName() + " is creating new code...";
    }

    @Override
    public String getDescription() {
        return "[" + getName() + ", " + getRole() + ", " + 0 + "]";
    }

    @Override
    public String toString() {
        return "Developer of the name: " + getName() + " and role: " + getRole();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
