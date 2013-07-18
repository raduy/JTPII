package pl.agh.jtp.lab03_home;

import java.math.BigDecimal;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Tester extends AbstractEmployee {

    /**
     *
     * @param name
     * @param role
     */
    public Tester(String name, String role) {
        super(name, role);
        setSalary(BigDecimal.valueOf(4000));
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
}
