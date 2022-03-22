package com.mitskevich.task2.builder;

import com.mitskevich.task2.entity.*;
import com.mitskevich.task2.handler.MedicineXmlTag;
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
import java.io.File;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
            logger.error("MedicineDomBuilder is not created. ", e);
        }
    }

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String fileName) {
        Document document;
        try {
            document = documentBuilder.parse(new File(fileName));
            Element root = document.getDocumentElement();
            NodeList antibioticList = root.getElementsByTagName(MedicineXmlTag.ANTIBIOTIC.getValue());
            NodeList antiviralList = root.getElementsByTagName(MedicineXmlTag.ANTIVIRAL.getValue());
            NodeList painkillerList = root.getElementsByTagName(MedicineXmlTag.PAINKILLER.getValue());
            NodeList vitaminList = root.getElementsByTagName(MedicineXmlTag.VITAMIN.getValue());
            for (int i = 0; i < antibioticList.getLength(); i++) {
                Element antibioticElement = (Element) antibioticList.item(i);
                AbstractMedicine medicine = buildAntibiotic(antibioticElement);
                medicines.add(medicine);
            }
            for (int i = 0; i < antiviralList.getLength(); i++) {
                Element antiviralElement = (Element) antiviralList.item(i);
                AbstractMedicine medicine = buildAntiviral(antiviralElement);
                medicines.add(medicine);
            }
            for (int i = 0; i < painkillerList.getLength(); i++) {
                Element painkillerElement = (Element) antibioticList.item(i);
                AbstractMedicine medicine = buildPainkiller(painkillerElement);
                medicines.add(medicine);
            }
            for (int i = 0; i < vitaminList.getLength(); i++) {
                Element vitaminElement = (Element) vitaminList.item(i);
                AbstractMedicine medicine = buildVitamin(vitaminElement);
                medicines.add(medicine);
            }
        } catch (SAXException | IOException e) {
            logger.error("Build set medicines is failed. ", e);
        }
    }

    private AbstractMedicine buildAntibiotic(Element antibioticElement) {
        Antibiotic antibiotic = new Antibiotic();
        buildMedicine(antibiotic, antibioticElement);
        antibiotic.setName(antibioticElement.getAttribute(MedicineXmlTag.NAME.getValue()));
        antibiotic.setPrescription(Boolean.parseBoolean(antibioticElement.getAttribute(MedicineXmlTag.PRESCRIPTION.getValue())));
        return antibiotic;
    }

    private AbstractMedicine buildAntiviral(Element antiviralElement) {
        Antiviral antiviral = new Antiviral();
        buildMedicine(antiviral, antiviralElement);
        antiviral.setName(antiviralElement.getAttribute(MedicineXmlTag.NAME.getValue()));
        antiviral.setAntiviralGroup(antiviralElement.getAttribute(MedicineXmlTag.ANTIVIRAL_GROUP.getValue()));
        return antiviral;
    }

    private AbstractMedicine buildPainkiller(Element painkillerElement) {
        Painkiller painkiller = new Painkiller();
        buildMedicine(painkiller, painkillerElement);
        painkiller.setName(painkillerElement.getAttribute(MedicineXmlTag.NAME.getValue()));
        painkiller.setPower(painkillerElement.getAttribute(MedicineXmlTag.POWER.getValue()));
        return painkiller;
    }

    private AbstractMedicine buildVitamin(Element vitaminElement) {
        Vitamin vitamin = new Vitamin();
        buildMedicine(vitamin, vitaminElement);
        vitamin.setName(vitaminElement.getAttribute(MedicineXmlTag.NAME.getValue()));
        vitamin.setTaste(vitaminElement.getAttribute(MedicineXmlTag.TASTE.getValue()));
        return vitamin;
    }


    private void buildMedicine(AbstractMedicine abstractMedicine, Element medicineElement) {
        abstractMedicine.setPharm(getElementTextContent(medicineElement, MedicineXmlTag.PHARM.getValue()));
        abstractMedicine.setAnalogs(getAnalogsContent(medicineElement, MedicineXmlTag.ANALOGS.getValue()));
        abstractMedicine.setVersions(buildVersionsList(medicineElement));
        abstractMedicine.setExpirationDateOfMedicine(getElementYearMonthContent(medicineElement, MedicineXmlTag.EXPIRATION_DATE_OF_MEDICINE.getValue()));
    }


    private List<String> getAnalogsContent(Element element, String value) {
        NodeList nodeList = element.getElementsByTagName(value);
        Element analogsNode = (Element) nodeList.item(0);
        List<String> analogsList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            analogsList.add(analogsNode.getTextContent());
        }
        return analogsList;
    }

    private List<Version> buildVersionsList(Element element) {
        NodeList nodeList = element.getElementsByTagName(MedicineXmlTag.VERSIONS.getValue());
        Element versionsNode = (Element) nodeList.item(0);
        NodeList versionNodeList = versionsNode.getElementsByTagName(MedicineXmlTag.VERSION.getValue());
        List<Version> versions = new ArrayList<>();
        for (int i = 0; i < versionNodeList.getLength(); i++) {
            versions.add(buildVersion(versionNodeList.item(i)));
        }
        return versions;
    }

    private Version buildVersion(Node item) {
        Version version = new Version();
        Element versionElement = (Element) item;
        version.setExecution(getElementTextContent(versionElement, MedicineXmlTag.EXECUTION.getValue()));
        version.setCertificate(buildCertificate(versionElement));
        version.setPackageOfMedicine(buildPackageOfMedicine(versionElement));
        version.setDosage(buildDosage(versionElement));
        return version;
    }

    private Certificate buildCertificate(Element element) {
        NodeList certificateList = element.getElementsByTagName(MedicineXmlTag.CERTIFICATE.getValue());
        Element certificateElement = (Element) certificateList.item(0);
        Certificate certificate = new Certificate();
        certificate.setRegistrationNumber(getElementIntContent(certificateElement, MedicineXmlTag.REGISTRATION_NUMBER.getValue()));
        certificate.setRegisteringOrganization(getElementTextContent(certificateElement, MedicineXmlTag.REGISTERING_ORGANIZATION.getValue()));
        certificate.setExpirationDate(buildExpirationDate(certificateElement));
        return certificate;
    }

    private ExpirationDate buildExpirationDate(Element element) {
        ExpirationDate expirationDate = new ExpirationDate();
        expirationDate.setStartDate(getElementYearMonthContent(element, MedicineXmlTag.START_DATE.getValue()));
        expirationDate.setEndDate(getElementYearMonthContent(element, MedicineXmlTag.END_DATE.getValue()));
        return expirationDate;
    }

    private PackageOfMedicine buildPackageOfMedicine(Element element) {
        PackageOfMedicine packageOfMedicine = new PackageOfMedicine();
        packageOfMedicine.setType(getElementTextContent(element, MedicineXmlTag.TYPE.getValue()));
        packageOfMedicine.setCount(getElementIntContent(element, MedicineXmlTag.COUNT.getValue()));
        packageOfMedicine.setPrice(getElementDoubleContent(element, MedicineXmlTag.PRICE.getValue()));
        return packageOfMedicine;
    }

    private Dosage buildDosage(Element element) {
        Dosage dosage = new Dosage();
        dosage.setValueOfDosage(getElementDoubleContent(element, MedicineXmlTag.VALUE_OF_DOSAGE.getValue()));
        dosage.setFrequencyOfAdmission(getElementIntContent(element, MedicineXmlTag.FREQUENCY_OF_ADMISSION.getValue()));
        return dosage;
    }

    private YearMonth getElementYearMonthContent(Element element, String elementName) {
        String yearMonthString = getElementTextContent(element, elementName);
        return YearMonth.parse(yearMonthString);
    }

    private int getElementIntContent(Element element, String elementName) {
        String stringInt = getElementTextContent(element, elementName);
        return Integer.parseInt(stringInt);
    }

    private double getElementDoubleContent(Element element, String elementName) {
        String stringDouble = getElementTextContent(element, elementName);
        return Double.parseDouble((stringDouble));
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    public static void main(String[] args) {
        MedicineDomBuilder domBuilder = new MedicineDomBuilder();
        domBuilder.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml");
        domBuilder.getMedicines()
                .forEach(System.out::println);
    }
}