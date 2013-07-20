package pl.agh.jtp.lab03_home.visitor;


import pl.agh.jtp.lab03_home.*;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Visitor print company structure in  tree style.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CompanyStructurePrintVisitor implements Visitor {
    private StringBuffer sb = new StringBuffer();

    private int level;
    private int numberOfEmployeesOfActualManager;
    private final LinkedList<Integer> stack = new LinkedList<Integer>();

    @Override
    public void visit(GroupManager manager) {
        updateLevel();

        for(int i = 0; i < level; i++) {
            sb.append("|---");
        }
        sb.append(manager.getDescription())
                .append("\n");
        goLevelUp();

        stack.add(numberOfEmployeesOfActualManager);
        numberOfEmployeesOfActualManager = 0;

        Iterator<IEmployee> it = manager.iterator();
        while(it.hasNext()) {
            if(!(it.next() instanceof IManager)) {
                numberOfEmployeesOfActualManager++;
            }
        }
    }

    @Override
    public void visit(Developer developer) {
        updateLevel();

        for(int i = 0; i < level; i++) {
            sb.append("|---");
        }
        sb.append(developer.getDescription())
                .append("\n");

        numberOfEmployeesOfActualManager--;
    }

    @Override
    public void visit(Tester tester) {
        updateLevel();

        for(int i = 0; i < level; i++) {
            sb.append("|---");
        }
        sb.append(tester.getDescription())
          .append("\n");

        numberOfEmployeesOfActualManager--;
    }

    private void goLevelDown() {
        level = Math.max(0,--level);
    }

    private void goLevelUp() {
        level++;
    }

    private void updateLevel() {
        if(numberOfEmployeesOfActualManager == 0) {
            goLevelDown();
            if(!stack.isEmpty()) {
                numberOfEmployeesOfActualManager = stack.pollLast();
                updateLevel();
            }
        }
    }

    public String stringToPrint() {
        return sb.toString();
    }
}
