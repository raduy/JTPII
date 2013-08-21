package pl.agh.jtp.lab06_home.plugins;

import pl.agh.jtp.lab06_home.Context;
import pl.agh.jtp.lab06_home.visitor.CompanyStructurePrintVisitor;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class ShowStructurePlugin extends AbstractPlugin {
    private static final String commandRegexForm = "^show[ ]*";

    /**
     * {@inheritDoc}
     */
    public ShowStructurePlugin() {
        super(new String[]{"show", "help show"});
    }

    @Override
    public String help(String command) {
        return "show []";
    }

    @Override
    public Context execute(String command, Context context) {

        if(!checkWhetherCommandMatchesPattern(command, commandRegexForm)) {
            return context;
        }

        if(context == null) {
            System.out.println("No company to show!");
            return context;
        }
        CompanyStructurePrintVisitor visitor = new CompanyStructurePrintVisitor();
        context.getCompany().accept(visitor);
        System.out.println(visitor.stringToPrint());
        return context;
    }
}
