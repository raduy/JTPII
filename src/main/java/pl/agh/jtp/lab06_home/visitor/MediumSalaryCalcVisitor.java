package pl.agh.jtp.lab06_home.visitor;

import pl.agh.jtp.lab06_home.structure.IEmployee;
import pl.agh.jtp.lab06_home.structure.people.Developer;
import pl.agh.jtp.lab06_home.structure.people.GroupManager;
import pl.agh.jtp.lab06_home.structure.people.Tester;

import java.math.BigDecimal;

/**
 * Visitor calculate medium salary of employees with <b>specified role</b> (in constructor).
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
        updateMediumSalary(manager);
    }

    @Override
    public void visit(Developer developer) {
        updateMediumSalary(developer);
    }

    @Override
    public void visit(Tester tester) {
        updateMediumSalary(tester);
    }

    private void updateMediumSalary(IEmployee employee) {
        if(employee.getRole().equals(role)) {
            numberOfMatchesEmployees++;
            summarySalary = summarySalary.add(employee.getSalary());
        }
    }

    public BigDecimal getMediumSalaryOfSpecifiedRole() {
        if(numberOfMatchesEmployees == 0) {
            return BigDecimal.ZERO;
        }
        return summarySalary.divide(BigDecimal.valueOf(numberOfMatchesEmployees));
    }
}
