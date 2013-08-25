package pl.agh.jtp.lab06_home;

import pl.agh.jtp.lab06_home.IO.IO;

import java.util.logging.Logger;

/**
 * Reads input data and return effect to application output.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Interpreter {
    private static final Logger LOGGER = Logger.getLogger(Interpreter.class.getName());
    private HelpCommandPerformer helpPerformer = new HelpCommandPerformer();
    private final Application application;
    private final static char prompt = '>';
    private final IO io;

    public Interpreter(Application application) {
        this.application = application;
        this.io = application.getIO();
    }

    public void nextCommand() {
        application.printContext();

        io.write(prompt + " ");
        String inputString = io.readLine();

        if("".equals(inputString)) {
            nextCommand();
        }
//        LOGGER.log(Level.INFO, "Command: '" + inputString + "' call");
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
