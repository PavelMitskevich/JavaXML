package com.mitskevich.task2.builder;

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
    public static void main(String[] args) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml";
        String schemaName = "C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(new File(fileName));
            validator.setErrorHandler(new MedicineErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            System.err.println(fileName + " is not correct or valid");
        }
    }
}
