package com.mitskevich.task2.validator;

import com.mitskevich.task2.exception.CustomParserXmlException;
import com.mitskevich.task2.handler.MedicineErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class BaseValidator {
    private static final Logger logger = LogManager.getLogger();
    private static String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private SchemaFactory factory;

    public BaseValidator() {
        factory = SchemaFactory.newInstance(language);
    }

    public boolean isXmlValidXsd(String fileName, String schemaName) throws CustomParserXmlException {
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(fileName));
            validator.setErrorHandler(new MedicineErrorHandler());
            validator.validate(source);
            logger.info("XML file is valid XSD schema");
            return true;
        } catch (SAXException saxException) {
            logger.warn(saxException.getMessage());
            return false;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        String fileName = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml";
        String schemaName = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xsd";
        BaseValidator baseValidator = new BaseValidator();
        try {
            baseValidator.isXmlValidXsd(fileName, schemaName);
        } catch (CustomParserXmlException e) {
            e.printStackTrace();
        }
    }
}