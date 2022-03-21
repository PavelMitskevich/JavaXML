package com.mitskevich.task2.handler;

import com.mitskevich.task2.entity.*;
import com.mitskevich.task2.exception.CustomParserXmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.*;

public class MedicineHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();

    private final Set<AbstractMedicine> medicines;
    private MedicineXmlTag currentXmlTag;
    private final EnumSet<MedicineXmlTag> withText;
    private AbstractMedicine currentMedicine;
    private Antibiotic currentAntibiotic;
    private Antiviral currentAntiviral;
    private Painkiller currentPainkiller;
    private Vitamin currentVitamin;
    private List<Version> currentVersions;
    private Version currentVersion;
    private Certificate currentCertificate;
    private PackageOfMedicine currentPackageOfMedicine;
    private Dosage currentDosage;
    private ExpirationDate currentExpirationDate;

    private static final String ELEMENT_ANTIBIOTIC = MedicineXmlTag.ANTIBIOTIC.getValue();
    private static final String ELEMENT_ANTIVIRAL = MedicineXmlTag.ANTIVIRAL.getValue();
    private static final String ELEMENT_PAINKILLER = MedicineXmlTag.PAINKILLER.getValue();
    private static final String ELEMENT_VITAMIN = MedicineXmlTag.VITAMIN.getValue();
    private static final String ELEMENT_VERSIONS = MedicineXmlTag.VERSIONS.getValue();
    private static final String ELEMENT_VERSION = MedicineXmlTag.VERSION.getValue();
    private static final String ELEMENT_CERTIFICATE = MedicineXmlTag.CERTIFICATE.getValue();
    private static final String ELEMENT_PACKAGE_OF_MEDICINE = MedicineXmlTag.PACKAGE_OF_MEDICINE.getValue();
    private static final String ELEMENT_DOSAGE = MedicineXmlTag.DOSAGE.getValue();
    private static final String ELEMENT_EXPIRATION_DATE = MedicineXmlTag.EXPIRATION_DATE.getValue();

    public MedicineHandler() {
        medicines = new HashSet<>();
        withText = EnumSet.range(MedicineXmlTag.PHARM, MedicineXmlTag.TASTE);
    }

    public Set<AbstractMedicine> getMedicines() {
        return medicines;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (ELEMENT_ANTIBIOTIC.equals(qName)) {
            currentAntibiotic = new Antibiotic();
            currentAntibiotic.setName(attrs.getValue(0));
            currentAntibiotic.setPrescription(Boolean.parseBoolean(attrs.getValue(1)));
            currentMedicine = currentAntibiotic;
        } else if (ELEMENT_ANTIVIRAL.equals(qName)) {
            currentAntiviral = new Antiviral();
            currentAntiviral.setName(attrs.getValue(0));
            currentAntiviral.setAntiviralGroup(attrs.getValue(1));
            currentMedicine = currentAntiviral;
        } else if (ELEMENT_PAINKILLER.equals(qName)) {
            currentPainkiller = new Painkiller();
            currentPainkiller.setName(attrs.getValue(0));
            currentPainkiller.setPower(attrs.getValue(1));
            currentMedicine = currentPainkiller;
        } else if (ELEMENT_VITAMIN.equals(qName)) {
            currentVitamin = new Vitamin();
            currentVitamin.setName(attrs.getValue(0));
            currentVitamin.setTaste(attrs.getValue(1));
            currentMedicine = currentVitamin;
        } else if (ELEMENT_VERSIONS.equals(qName)) {
            currentVersions = new ArrayList<>();
        } else if (ELEMENT_VERSION.equals(qName)) {
            currentVersion = new Version();
        } else if (ELEMENT_CERTIFICATE.equals(qName)) {
            currentCertificate = new Certificate();
        } else if (ELEMENT_PACKAGE_OF_MEDICINE.equals(qName)) {
            currentPackageOfMedicine = new PackageOfMedicine();
        } else if (ELEMENT_DOSAGE.equals(qName)) {
            currentDosage = new Dosage();
        } else if (ELEMENT_EXPIRATION_DATE.equals(qName)) {
            currentExpirationDate = new ExpirationDate();
        } else {
            try {
                MedicineXmlTag temp = MedicineXmlTag.getMedicineXmlTag(qName);
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
            } catch (CustomParserXmlException e) {
                logger.warn("Unknown start element '" + qName + "'.", e);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_ANTIBIOTIC.equals(qName)) {
            medicines.add(currentAntibiotic);
        } else if (ELEMENT_ANTIVIRAL.equals(qName)) {
            medicines.add(currentAntiviral);
        } else if (ELEMENT_PAINKILLER.equals(qName)) {
            medicines.add(currentPainkiller);
        } else if (ELEMENT_VITAMIN.equals(qName)) {
            medicines.add(currentVitamin);
        } else if (ELEMENT_VERSIONS.equals(qName)) {
            currentMedicine.setVersions(currentVersions);
        } else if (ELEMENT_VERSION.equals(qName)) {
            currentVersions.add(currentVersion);
        } else if (ELEMENT_CERTIFICATE.equals(qName)) {
            currentVersion.setCertificate(currentCertificate);
        } else if (ELEMENT_PACKAGE_OF_MEDICINE.equals(qName)) {
            currentVersion.setPackageOfMedicine(currentPackageOfMedicine);
        } else if (ELEMENT_DOSAGE.equals(qName)) {
            currentVersion.setDosage(currentDosage);
        } else if (ELEMENT_EXPIRATION_DATE.equals(qName)) {
            currentCertificate.setExpirationDate(currentExpirationDate);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> currentMedicine.setName(data);
                case PHARM -> currentMedicine.setPharm(data);
                case ANALOGS -> currentMedicine.setAnalogs((Arrays.stream(data.split(" ")).toList()));
                case EXECUTION -> currentVersion.setExecution(data);
                case REGISTRATION_NUMBER -> currentCertificate.setRegistrationNumber(Integer.parseInt(data));
                case REGISTERING_ORGANIZATION -> currentCertificate.setRegisteringOrganization(data);
                case START_DATE -> currentExpirationDate.setStartDate(YearMonth.parse(data));
                case END_DATE -> currentExpirationDate.setEndDate(YearMonth.parse(data));
                case TYPE -> currentPackageOfMedicine.setType(data);
                case COUNT -> currentPackageOfMedicine.setCount(Integer.parseInt(data));
                case PRICE -> currentPackageOfMedicine.setPrice(Double.parseDouble(data));
                case VALUE_OF_DOSAGE -> currentDosage.setValueOfDosage(Double.parseDouble(data));
                case FREQUENCY_OF_ADMISSION -> currentDosage.setFrequencyOfAdmission(Integer.parseInt(data));
                case EXPIRATION_DATE_OF_MEDICINE -> currentMedicine.setExpirationDateOfMedicine(YearMonth.parse(data));
                default -> {
                    logger.error("Unknown tag " + currentXmlTag.name());
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
                }
            }
        }
        currentXmlTag = null;
    }
}
