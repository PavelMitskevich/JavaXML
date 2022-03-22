package com.mitskevich.task2.builder;

import com.mitskevich.task2.entity.AbstractMedicine;
import com.mitskevich.task2.exception.CustomParserXmlException;
import com.mitskevich.task2.handler.MedicineHandler;
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

    public MedicineSaxBuilder() throws CustomParserXmlException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("MedicineSaxBuilder is failed. ");
        }
        reader.setContentHandler(medicineHandler);
    }

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String fileName) throws CustomParserXmlException {
        try {
            reader.parse(new InputSource(new FileInputStream(fileName)));
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        medicines = medicineHandler.getMedicines();
        logger.info("Set of medicines build. " + medicineHandler);
    }

    public static void main(String[] args) {
        try {
            MedicineSaxBuilder saxBuilder = new MedicineSaxBuilder();
            saxBuilder.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml");
            saxBuilder.getMedicines()
                    .forEach(System.out::println);
        } catch (CustomParserXmlException e) {
            e.printStackTrace();
        }
    }
}
