package pl.agh.jtp.lab06_home.logging;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CommandUsageFileHandler extends FileHandler {
    public CommandUsageFileHandler() throws IOException, SecurityException {
    }

    public CommandUsageFileHandler(String s) throws IOException, SecurityException {
        super(s);
    }

    public CommandUsageFileHandler(String s, boolean b) throws IOException, SecurityException {
        super(s, b);
    }

    public CommandUsageFileHandler(String s, int i, int i2) throws IOException, SecurityException {
        super(s, i, i2);
    }

    public CommandUsageFileHandler(String s, int i, int i2, boolean b) throws IOException, SecurityException {
        super(s, i, i2, b);
    }
}
