package pl.agh.jtp.lab06_home;

import pl.agh.jtp.lab06_home.IO.IO;

/**
 * Reads input data and return effect to application output.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Interpreter {
    private HelpCommandPerformer helpPerformer;
    private final Application application;
    private final static char prompt = '>';
    private final IO io;

    public Interpreter(Application application) {
        this.application = application;
        this.io = application.getIO();
        helpPerformer = new HelpCommandPerformer(io);
    }

    public void nextCommand() {
        application.printContext();

        io.write(prompt + " ");
        String inputString = io.readLine();

        if("".equals(inputString)) {
            nextCommand();
        }
        parseCommand(inputString);
    }

    private void parseCommand(String s) {
        boolean commandFound;
        if(s.startsWith("help")) {
            commandFound = helpPerformer.executeHelp(s, application);
        } else if(s.equals("exit")) {
            commandFound = true;
            application.exitApplication();
        } else {
            commandFound = application.getPluginManager().tryExecuteCommandByPlugin(s, application.getSession());
        }
        if(!commandFound) {
            io.writeln("Command not supported :/ List of acceptable commands:");
            application.listAcceptableCommands();
        }
        nextCommand();
    }
}
