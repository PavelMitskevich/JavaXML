package com.mitskevich.task2.parser;

import com.mitskevich.task2.entity.AbstractMedicine;
import com.mitskevich.task2.exception.CustomParserXmlException;
import com.mitskevich.task2.handler.MedicineHandler;
import com.mitskevich.task2.handler.MedicineErrorHandler;
import com.mitskevich.task2.util.ResourcePathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MedicineSaxBuilder {
    private static final Logger logger = LogManager.getLogger();

    private Set<AbstractMedicine> medicines = new HashSet<>();
    private XMLReader reader;
    private final MedicineHandler medicineHandler = new MedicineHandler();
    private final MedicineErrorHandler errorHandler = new MedicineErrorHandler();

    public MedicineSaxBuilder() throws CustomParserXmlException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
//            reader.setContentHandler(medicineHandler);
//            reader.setErrorHandler(errorHandler);
        } catch (ParserConfigurationException | SAXException e) {
            logger.error(e.getMessage());
        }
        reader.setContentHandler(medicineHandler);
    }

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String fileName) throws CustomParserXmlException {
//        String schemaFileName = ResourcePathUtil.getResourcePath("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xsd");
        try {
            reader.parse(new InputSource(new FileInputStream(fileName)));
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        medicines = medicineHandler.getMedicines();
        logger.info("Set of medicines build. " + medicineHandler);
    }


    public static void main(String[] args) {
//        try {
//            MedicineSaxBuilder saxMain = new MedicineSaxBuilder();
//            saxMain.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml");
//        } catch (CustomParserXmlException e) {
//            e.printStackTrace();
//        }
        MedicineSaxBuilder saxBuilder = null;
        try {
            saxBuilder = new MedicineSaxBuilder();
        } catch (CustomParserXmlException e) {
            e.printStackTrace();
        }
        try {
            saxBuilder.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml");
        } catch (CustomParserXmlException e) {
            e.printStackTrace();
        }
        System.out.println(saxBuilder.getMedicines());
    }
}
