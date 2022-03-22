package com.mitskevich.task2.builder;

import com.mitskevich.task2.entity.*;
import com.mitskevich.task2.exception.CustomParserXmlException;
import com.mitskevich.task2.handler.MedicineXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;

public class MedicineStaxBuilder {
    private static final Logger logger = LogManager.getLogger();

    private Set<AbstractMedicine> medicines;
    private XMLInputFactory inputFactory;

    public MedicineStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        medicines = new HashSet<>();
    }

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String fileName) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(MedicineXmlTag.ANTIBIOTIC.getValue())) {
                        AbstractMedicine abstractMedicine = buildMedicine(new Antibiotic(), reader);
                        medicines.add(abstractMedicine);
                    } else if (name.equals(MedicineXmlTag.ANTIVIRAL.getValue())) {
                        AbstractMedicine abstractMedicine = buildMedicine(new Antiviral(), reader);
                        medicines.add(abstractMedicine);
                    } else if (name.equals(MedicineXmlTag.PAINKILLER.getValue())) {
                        AbstractMedicine abstractMedicine = buildMedicine(new Painkiller(), reader);
                        medicines.add(abstractMedicine);
                    } else if (name.equals(MedicineXmlTag.VITAMIN.getValue())) {
                        AbstractMedicine abstractMedicine = buildMedicine(new Vitamin(), reader);
                        medicines.add(abstractMedicine);
                    }
                }
            }
        } catch (XMLStreamException | IOException | CustomParserXmlException e) {
            logger.error("Failed building " + e);
        }
    }

    private AbstractMedicine buildMedicine(AbstractMedicine medicine, XMLStreamReader reader) throws XMLStreamException, CustomParserXmlException {
        medicine.setName(reader.getAttributeValue(null, MedicineXmlTag.NAME.getValue()));
        String prescriptionValue = reader.getAttributeValue(null, MedicineXmlTag.PRESCRIPTION.getValue());
        if (prescriptionValue != null) {
            Antibiotic antibiotic = (Antibiotic) medicine;
            antibiotic.setPrescription(Boolean.parseBoolean(prescriptionValue));
        }
        String antiviralGroupValue = reader.getAttributeValue(null, MedicineXmlTag.ANTIVIRAL_GROUP.getValue());
        if (antiviralGroupValue != null) {
            Antiviral antiviral = (Antiviral) medicine;
            antiviral.setAntiviralGroup(antiviralGroupValue);
        }
        String powerValue = reader.getAttributeValue(null, MedicineXmlTag.POWER.getValue());
        if (powerValue != null) {
            Painkiller painkiller = (Painkiller) medicine;
            painkiller.setPower(powerValue);
        }
        String tasteValue = reader.getAttributeValue(null, MedicineXmlTag.TASTE.getValue());
        if (tasteValue != null) {
            Vitamin vitamin = (Vitamin) medicine;
            vitamin.setTaste(tasteValue);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    switch (MedicineXmlTag.getMedicineXmlTag(name)) {
                        case PHARM -> medicine.setPharm(getXMLText(reader));
                        case ANALOGS -> medicine.setAnalogs(Arrays.stream(getXMLText(reader).split(" ")).toList());
                        case VERSIONS -> medicine.setVersions(getXMLVersions(reader));
                        case EXPIRATION_DATE_OF_MEDICINE -> medicine.setExpirationDateOfMedicine(YearMonth.parse(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXmlTag tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (MedicineXmlTag.ANTIBIOTIC == tag || MedicineXmlTag.ANTIVIRAL == tag || MedicineXmlTag.PAINKILLER == tag || MedicineXmlTag.VITAMIN == tag) {
                        return medicine;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag" + medicine.getClass().getSimpleName() + " is not found");
    }


    private List<Version> getXMLVersions(XMLStreamReader reader) throws XMLStreamException, CustomParserXmlException {
        List<Version> versions = new ArrayList<>();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            MedicineXmlTag tag;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (tag == MedicineXmlTag.VERSION) {
                        Version version = getXMLVersion(reader);
                        versions.add(version);
                    }
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (tag == MedicineXmlTag.VERSIONS) {
                        return versions;
                    }
            }
        }
        throw new XMLStreamException("End tag" + versions.getClass().getSimpleName() + " is not found");
    }

    private Version getXMLVersion(XMLStreamReader reader) throws XMLStreamException, CustomParserXmlException {
        Version version = new Version();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            MedicineXmlTag tag;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    switch (tag) {
                        case EXECUTION -> version.setExecution(getXMLText(reader));
                        case CERTIFICATE -> version.setCertificate(getXMLCertificate(reader));
                        case PACKAGE_OF_MEDICINE -> version.setPackageOfMedicine(getXMLPackageOfMedicine(reader));
                        case DOSAGE -> version.setDosage(getXMLDosage(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (tag == MedicineXmlTag.VERSION) {
                        return version;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag" + version.getClass().getSimpleName() + " is not found");
    }

    private Certificate getXMLCertificate(XMLStreamReader reader) throws XMLStreamException, CustomParserXmlException {
        Certificate certificate = new Certificate();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            MedicineXmlTag tag;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    switch (tag) {
                        case REGISTRATION_NUMBER -> certificate.setRegistrationNumber(Integer.parseInt(getXMLText(reader)));
                        case REGISTERING_ORGANIZATION -> certificate.setRegisteringOrganization(getXMLText(reader));
                        case EXPIRATION_DATE -> certificate.setExpirationDate(getXMLExpirationDate(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (tag == MedicineXmlTag.CERTIFICATE) {
                        return certificate;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag" + certificate.getClass().getSimpleName() + " is not found");
    }

    private ExpirationDate getXMLExpirationDate(XMLStreamReader reader) throws XMLStreamException, CustomParserXmlException {
        ExpirationDate expirationDate = new ExpirationDate();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            MedicineXmlTag tag;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    switch (tag) {
                        case START_DATE -> expirationDate.setStartDate(YearMonth.parse((getXMLText(reader))));
                        case END_DATE -> expirationDate.setEndDate(YearMonth.parse(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (tag == MedicineXmlTag.EXPIRATION_DATE) {
                        return expirationDate;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag" + expirationDate.getClass().getSimpleName() + " is not found");
    }

    private PackageOfMedicine getXMLPackageOfMedicine(XMLStreamReader reader) throws XMLStreamException, CustomParserXmlException {
        PackageOfMedicine packageOfMedicine = new PackageOfMedicine();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            MedicineXmlTag tag;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    switch (tag) {
                        case TYPE -> packageOfMedicine.setType((getXMLText(reader)));
                        case COUNT -> packageOfMedicine.setCount(Integer.parseInt((getXMLText(reader))));
                        case PRICE -> packageOfMedicine.setPrice(Double.parseDouble((getXMLText(reader))));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (tag == MedicineXmlTag.PACKAGE_OF_MEDICINE) {
                        return packageOfMedicine;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag" + packageOfMedicine.getClass().getSimpleName() + " is not found");
    }

    private Dosage getXMLDosage(XMLStreamReader reader) throws XMLStreamException, CustomParserXmlException {
        Dosage dosage = new Dosage();
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            MedicineXmlTag tag;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    switch (tag) {
                        case VALUE_OF_DOSAGE -> dosage.setValueOfDosage(Double.parseDouble((getXMLText(reader))));
                        case FREQUENCY_OF_ADMISSION -> dosage.setFrequencyOfAdmission(Integer.parseInt((getXMLText(reader))));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    tag = MedicineXmlTag.getMedicineXmlTag(name);
                    if (tag == MedicineXmlTag.DOSAGE) {
                        return dosage;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag" + dosage.getClass().getSimpleName() + " is not found");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    public static void main(String[] args) {
        MedicineStaxBuilder staxBuilder = new MedicineStaxBuilder();
        staxBuilder.buildSetMedicines("C:\\Users\\Иван\\IdeaProjects\\JavaXML\\src\\main\\resources\\medicines.xml");
        staxBuilder.getMedicines()
                .forEach(System.out::println);
    }
}