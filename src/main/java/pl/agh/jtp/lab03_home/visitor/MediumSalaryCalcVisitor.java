package pl.agh.jtp.lab03_home.visitor;

import pl.agh.jtp.lab03_home.Developer;
import pl.agh.jtp.lab03_home.GroupManager;
import pl.agh.jtp.lab03_home.Tester;

import java.math.BigDecimal;

/**
 * Visitor calculate medium salary of employees with role specified in constructor.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class MediumSalaryCalcVisitor implements Visitor {
    private final String role;
    private int numberOfMatchesEmployees = 0;
    private BigDecimal summarySalary;

    /**
     * Param is a role for search in tree to find a medium salary.
     * @param role
     */
    public MediumSalaryCalcVisitor(String role) {
        this.role = role;
        summarySalary = BigDecimal.ZERO;
    }

    @Override
    public void visit(GroupManager manager) {
        if(manager.getRole().equals(role)) {
            numberOfMatchesEmployees++;
            summarySalary = summarySalary.add(manager.getSalary());
        }
    }

    @Override
    public void visit(Developer developer) {
        if(developer.getRole().equals(role)) {
            numberOfMatchesEmployees++;
            summarySalary = summarySalary.add(developer.getSalary());
        }
    }

    @Override
    public void visit(Tester tester) {
        if(tester.getRole().equals(role)) {
            numberOfMatchesEmployees++;
            summarySalary = summarySalary.add(tester.getSalary());
        }
    }

    public BigDecimal getMediumSalary() {
        if(numberOfMatchesEmployees == 0) {
            return BigDecimal.ZERO;
        }
        return summarySalary.divide(BigDecimal.valueOf(numberOfMatchesEmployees));
    }
}
