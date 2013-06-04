package pl.agh.jtp.lab02_home;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
abstract class AbstractEmployee implements IEmployee {
    private final String name;
    private final String role;
    private IManager manager;

    protected AbstractEmployee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getResponsibilityChain() {
        return "Manager should give us responsibilityChain";
    }

}
