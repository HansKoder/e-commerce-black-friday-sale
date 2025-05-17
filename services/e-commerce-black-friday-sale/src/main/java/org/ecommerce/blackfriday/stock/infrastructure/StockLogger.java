package org.ecommerce.blackfriday.stock.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockLogger {

    private static final Logger log = LoggerFactory.getLogger("stock");

    public static void info(String message, Object... args) {
        log.info(message, args);
    }

    public static void debug(String message, Object... args) {
        log.debug(message, args);
    }

    public static void warn(String message, Object... args) {
        log.warn(message, args);
    }

    public static void error(String message, Object... args) {
        log.error(message, args);
    }
}