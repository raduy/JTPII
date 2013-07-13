package pl.agh.jtp.lab02;

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
        /**
         * {@inheritDoc}
         */
        return name;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public IManager getSupervisor() {
        return manager;
    }

    @Override
    public void setSupervisor(IManager manager) {
        this.manager = manager;
    }

    @Override
    public String getResponsibilityChain() {
        StringBuilder responsibilityChain =  new StringBuilder();
        responsibilityChain.append(getName() + "[" + getRole() + "]");
        if(getSupervisor() != null) {
            responsibilityChain.append(" - reports to ");
            responsibilityChain.append(getSupervisor().getResponsibilityChain());
        }

        return responsibilityChain.toString();
    }

}
