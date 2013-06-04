package pl.agh.jtp.lab02_home;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Developer extends AbstractEmployee {

    /**
     *
     * @param name
     * @param role
     */
    public Developer(String name, String role) {
        super(name, role);
    }

    @Override
    public String work() {
        return "Developer: " + getName() + " is creating new code...";
    }

    @Override
    public String toString() {
        return "Developer of the name: " + getName() + " and role: " + getRole();
    }

}
