package com.rudy.ryanto.Util;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

public class LoggingUtil {

    public static Logger logger(Class obj){
        Logger logger = ESAPI.getLogger(obj);
        return logger;
    }

    public static void logInfo(Logger logger, Logger.EventType event, String message){
        if(logger.isInfoEnabled())
            logger.info(event,message);
    }

    public static void logError(Logger logger, Logger.EventType event, String message,Throwable t){
        if(logger.isErrorEnabled()){
            if(null!=t)
                logger.error(event,message,t);
            else
                logger.error(event,message);
        }
    }

    public void logDebug(Logger logger, Logger.EventType event, String message,Throwable t){
        if(logger.isDebugEnabled()){
            if(null!=t)
                logger.debug(event,message,t);
            else
                logger.debug(event,message);
        }
    }
}
