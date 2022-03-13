package com.mitskevich.task2.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class MedicineErrorHandler implements ErrorHandler {
    private static Logger logger = LogManager.getLogger();

    public void warning(SAXParseException e) {
        e.printStackTrace();
        logger.warn(getLineColumnNumber(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        e.printStackTrace();
        logger.error(getLineColumnNumber(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        e.printStackTrace();
        logger.fatal(getLineColumnNumber(e) + " - " + e.getMessage());
    }

    private String getLineColumnNumber(SAXParseException e) {
        e.printStackTrace();
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
