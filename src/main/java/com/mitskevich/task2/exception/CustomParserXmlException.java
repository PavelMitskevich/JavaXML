package com.mitskevich.task2.exception;

public class CustomParserXmlException extends Exception{

    public CustomParserXmlException() {
        super();
    }

    public CustomParserXmlException(String message) {
        super(message);
    }

    public CustomParserXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomParserXmlException(Throwable cause) {
        super(cause);
    }
}
