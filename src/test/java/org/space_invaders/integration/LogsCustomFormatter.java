package org.space_invaders.integration;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogsCustomFormatter extends Formatter{
    @Override
    public String format(LogRecord record) {
        return String.format(
                "[%1$tF %1$tT] [%2$-7s]\n%3$s%n",
                new java.util.Date(record.getMillis()),
                record.getLevel(),
                record.getMessage()
        );
    }
}
