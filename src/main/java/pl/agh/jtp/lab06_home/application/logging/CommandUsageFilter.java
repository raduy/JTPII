package pl.agh.jtp.lab06_home.application.logging;

import pl.agh.jtp.lab06_home.application.stats.Statistics;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * This class filter LogRecords and leave only created by {@link Statistics} Logger.
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CommandUsageFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord logRecord) {
        return logRecord.getLoggerName().equals(Statistics.class.getName());
    }
}
