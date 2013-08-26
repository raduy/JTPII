package pl.agh.jtp.lab06_home.stats;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This structural object stores information about command usage in application.
 * It can stores absolute number of command application usage (new session stats are added to data in file)
 * or single session statistics.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Statistics {
    private final static Logger LOGGER = Logger.getLogger(Statistics.class.getName());
    private final static Map<String, Integer> singleSessionStats = new HashMap<>();
    private final static Map<String, Integer> totalStats = new HashMap<>(); //TODO

    public void incrementCommandInvocationCounterOrCreateANewEntry(String command) {
        if(singleSessionStats.containsKey(command)) {
            int commandInvocationCounter = singleSessionStats.get(command);
            commandInvocationCounter++;
            singleSessionStats.put(command, commandInvocationCounter);
        } else {
            singleSessionStats.put(command, 1);
        }
    }

    public void logSingleSessionStatistics() {
        LOGGER.log(Level.INFO, String.valueOf(singleSessionStats));
    }

    public void logTotalStatistics() {
        LOGGER.log(Level.INFO, String.valueOf(totalStats));
    }
}
