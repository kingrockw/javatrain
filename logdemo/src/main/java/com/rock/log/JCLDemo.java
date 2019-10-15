package com.rock.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JCLDemo {
   static Log logger = LogFactory.getLog(JCLDemo.class);

    public static void main(String[] args) {
        logger.trace("Trace Message!");
        logger.debug("Debug Message!");
        logger.info("Info Message!");
        logger.warn("Warn Message!");
        logger.error("Error nihao 你好!");
        logger.fatal("Fatal Message!");
        logger.info("log4j test");
    }
}
