package com.mitskevich.task2.parser;

import com.mitskevich.task2.entity.AbstractMedicine;
import com.mitskevich.task2.entity.Antibiotic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MedicineDomBuilder {
    private static final Logger logger = LogManager.getLogger();
    private Set<AbstractMedicine> medicines;
    private DocumentBuilder documentBuilder;

    public MedicineDomBuilder() {
        medicines = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String fileName) {
        Document document;
        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList medicinesList = root.getElementsByTagName("antibiotic");
            for (int i = 0; i < medicinesList.getLength(); i++) {
                Element medicineElement = (Element) medicinesList.item(i);
                AbstractMedicine medicine = buildMedicine(medicineElement);
                medicines.add(medicine);
            }
        } catch (SAXException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private AbstractMedicine buildMedicine(Element medicineElement) {
        AbstractMedicine abstractMedicine = new Antibiotic();
        abstractMedicine.setPharm(medicineElement.getAttribute("pharm"));




        return abstractMedicine;
    }
}

