package pl.agh.jtp.lab06_home.domain.people;

import pl.agh.jtp.lab06_home.domain.IEmployee;
import pl.agh.jtp.lab06_home.domain.IManager;
import pl.agh.jtp.lab06_home.encryption.Cryptor;
import pl.agh.jtp.lab06_home.encryption.EncodedObject;
import pl.agh.jtp.lab06_home.encryption.ICryptor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Skeleton implementation of IEmployee interface.
 * @author Łukasz Raduj <raduj.lukasz@gmail.com>
 */
public abstract class AbstractEmployee implements IEmployee, Serializable {
    private final static long serialVersionUID = 1L;
    private transient String name;
    private final String role;
    private transient BigDecimal salary;
    private static int IDCounter = 1;
    private int ID = IDCounter++;
    private IManager supervisor;

    protected AbstractEmployee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
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

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public IManager getSupervisor() {
        return supervisor;
    }

    @Override
    public void setSupervisor(IManager manager) {
        this.supervisor = manager;
    }

    @Override
    public String getResponsibilityChain() {
        StringBuilder responsibilityChain =  new StringBuilder();
        responsibilityChain.append(getName()).append("[").append(getRole()).append("]");
        if(getSupervisor() != null) {
            responsibilityChain.append(" - reports to ");
            responsibilityChain.append(getSupervisor().getResponsibilityChain());
        }

        return responsibilityChain.toString();
    }

    @Override
    public String getDescription() {
        return "[" + getName() + ", " + getRole() + ", " + 0 + ", ID: " + getID() + "]";
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
        result = result + 31 * supervisor.hashCode();

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
        System.out.println(employee);
        System.out.println(getSupervisor());
        if(!getSupervisor().equals(employee.getSupervisor())) {
            return false;
        }
        if(!getSalary().equals(employee.getSalary())) {
            return false;
        }

        return true;
    }

    public int getID() {
        return ID;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();

        ICryptor cryptor = new Cryptor();
        stream.writeObject(cryptor.encrypt(name));
        stream.writeObject(cryptor.encrypt(salary));
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        ICryptor cryptor = new Cryptor();
        name = (String) cryptor.decrypt((EncodedObject) stream.readObject());
        salary = (BigDecimal) cryptor.decrypt((EncodedObject) stream.readObject());
    }
}

