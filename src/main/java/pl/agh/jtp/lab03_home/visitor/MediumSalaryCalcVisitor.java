package pl.agh.jtp.lab03_home.visitor;

import pl.agh.jtp.lab03_home.IEmployee;

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
    public void visit(IEmployee employee) {
        if(employee.getRole().equals(role)) {
            numberOfMatchesEmployees++;
            summarySalary = summarySalary.add(employee.getSalary());
        }
    }

    @Override
    public void goLevelDown() {
    }

    @Override
    public void goLevelUp() {
    }

    public BigDecimal getMediumSalary() {
        if(numberOfMatchesEmployees == 0) {
            return BigDecimal.ZERO;
        }
        return summarySalary.divide(BigDecimal.valueOf(numberOfMatchesEmployees));
    }
}
