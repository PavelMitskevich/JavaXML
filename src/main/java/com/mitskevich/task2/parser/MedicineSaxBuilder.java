package com.mitskevich.task2.parser;

import com.mitskevich.task2.entity.AbstractMedicine;
import com.mitskevich.task2.exception.CustomParserXmlException;
import com.mitskevich.task2.handler.ConsoleMedicineHandler;
import com.mitskevich.task2.handler.MedicineErrorHandler;
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
import java.util.Set;

public class MedicineSaxBuilder {
    private static final Logger logger = LogManager.getLogger();
    private Set<AbstractMedicine> medicines;
    private XMLReader reader;
    private final ConsoleMedicineHandler medicineHandler = new ConsoleMedicineHandler();
    private final MedicineErrorHandler errorHandler = new MedicineErrorHandler();

    public MedicineSaxBuilder() throws CustomParserXmlException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(medicineHandler);
            reader.setErrorHandler(errorHandler);
        } catch (ParserConfigurationException | SAXException e) {
            logger.error(e.getMessage());
        }
    }

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String fileName) {
        try {
            reader.parse(new InputSource(new FileInputStream(fileName)));
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        medicines = medicineHandler.getMedicines();
    }


    public static void main(String[] args) {
        try {
            MedicineSaxBuilder saxMain = new MedicineSaxBuilder();
            saxMain.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml");
        } catch (CustomParserXmlException e) {
            e.printStackTrace();
        }
    }
}
