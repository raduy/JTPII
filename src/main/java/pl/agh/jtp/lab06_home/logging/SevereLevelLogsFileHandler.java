package pl.agh.jtp.lab06_home.logging;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SevereLevelLogsFileHandler extends FileHandler {


    public SevereLevelLogsFileHandler() throws IOException, SecurityException {
    }

    public SevereLevelLogsFileHandler(String s) throws IOException, SecurityException {
        super(s);
    }

    public SevereLevelLogsFileHandler(String s, boolean b) throws IOException, SecurityException {
        super(s, b);
    }

    public SevereLevelLogsFileHandler(String s, int i, int i2) throws IOException, SecurityException {
        super(s, i, i2);
    }

    public SevereLevelLogsFileHandler(String s, int i, int i2, boolean b) throws IOException, SecurityException {
        super(s, i, i2, b);
    }
}
