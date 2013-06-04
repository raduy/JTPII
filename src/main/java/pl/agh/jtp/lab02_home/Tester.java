package pl.agh.jtp.lab02_home;

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
    public String toString() {
        return "Tester with the name: " + getName() + " and role: " + getRole();
    }
}
