package com.rock.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JULDemo {

    public void log(){
        Logger logger = Logger.getLogger("JULDemo");
        logger.log(Level.INFO,"test......");
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("JULDemo");
        logger.log(Level.INFO,"test......");
    }
}
