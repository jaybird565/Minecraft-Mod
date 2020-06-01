package typicals.alchemicalexpansion.util;

import org.apache.logging.log4j.Logger;
import typicals.alchemicalexpansion.AlchemicalExpansion;

public class LoggerUtil {


    public static Logger logger = AlchemicalExpansion.logger;

    public static boolean verbose = true;

    public static boolean veryVerbose = true;

    public static boolean dev = true;

    public static void v(String message) {
        verbose(message);
    }

    public static void vv(String message) {
        veryVerbose(message);
    }

    public static void vvv(String message) {
        veryVerboseVerbose(message);
    }

    public static void veryVerboseVerbose(String message) {
        if(verbose && veryVerbose) {
            debug("[VVV] " + message);
        }
    }

    public static void veryVerbose(String message) {
        if(veryVerbose) {
            debug("[VV] " + message);
        }
    }

    public static void verbose(String message) {
        if(verbose) {
            debug("[V] " + message);
        }
    }

    public static void dev(String message) {
        if(dev) {
            debug("[DEV] " + message);
        }
    }


    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Exception e) {
        logger.error(message, e);
    }

    public static void warn(String message) {
        logger.warn(message);
    }
}
