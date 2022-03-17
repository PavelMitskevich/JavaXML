package com.mitskevich.task2.exception;

public class CustomParserXmlException extends Exception{

    public CustomParserXmlException() {
        super();
    }

    public CustomParserXmlException(String message) {
        super(message);
    }

    public CustomParserXmlException(String message, Exception exception) {
        super(message, exception);
    }

    public CustomParserXmlException(Exception exception) {
        super(exception);
    }
}
