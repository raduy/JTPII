package pl.agh.jtp.lab03_home.visitor;

import pl.agh.jtp.lab03_home.IEmployee;

/**
 * Visitor print company structure in  tree style.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CompanyStructurePrintVisitor implements Visitor {
    private int level;
    private StringBuffer sb = new StringBuffer();

    @Override
    public void visit(IEmployee employee) {
        for(int i = 0; i < level; i++) {
            sb.append("|---");
        }
        sb.append(employee.getDescription())
          .append("\n");
    }

    @Override
    public void goLevelDown() {
        level++;
    }

    @Override
    public void goLevelUp() {
        level--;
    }

    public String stringToPrint() {
        return sb.toString();
    }
}
