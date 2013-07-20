package pl.agh.jtp.lab03_home;

import java.math.BigDecimal;

/**
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
abstract class AbstractEmployee implements IEmployee {
    private final String name;
    private final String role;
    private BigDecimal salary = BigDecimal.valueOf(5000);
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
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + getName() + " with role: " + getRole();
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = result + 31 * role.hashCode();
        result = result + 31 * salary.hashCode();
        result = result + 31 * manager.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || this.getClass() != o.getClass()) {
            return false;
        }

        AbstractEmployee employee = (AbstractEmployee) o;

        if(!getName().equals(employee.getName())) {
            return false;
        }
        if(!getRole().equals(employee.getRole())) {
            return false;
        }
        if(!getSupervisor().equals(employee.getSupervisor())) {
            return false;
        }
        if(!getSalary().equals(employee.getSalary())) {
            return false;
        }

        return true;
    }

}

