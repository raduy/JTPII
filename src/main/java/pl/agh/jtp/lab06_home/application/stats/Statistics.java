package pl.agh.jtp.lab06_home.application.stats;

import java.util.*;
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
        LOGGER.log(Level.INFO, formSomeNiceStatisticsString());
    }

    public void logTotalStatistics() {
        LOGGER.log(Level.INFO, String.valueOf(totalStats));
    }

    private String formSomeNiceStatisticsString() {
        StringBuilder stringBuilder = new StringBuilder("Commands usage stats: \n");
        stringBuilder.append(String.format("%10s\t|\t%s:", "COMMAND", "USAGE")).append("\n");

        Set<Map.Entry<String, Integer>> usages = sortMapByValue(singleSessionStats).entrySet();

        for(Map.Entry<String, Integer> entry : usages) {
            stringBuilder.append(String.format("%10s\t|\t%d\n", entry.getKey(), entry.getValue()));
        }

        return stringBuilder.toString();
    }

    private static <K, V extends Comparable<V>> Map<K, V> sortMapByValue(Map<K, V> unsortedMap) {

        List<Map.Entry<K, V>> entries = new LinkedList<>(unsortedMap.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> entry1, Map.Entry<K, V> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        Map<K, V> sortedMap = new LinkedHashMap<>();
        for(Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
