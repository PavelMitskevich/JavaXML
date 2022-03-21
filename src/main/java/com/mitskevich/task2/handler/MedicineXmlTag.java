package com.mitskevich.task2.handler;

import com.mitskevich.task2.exception.CustomParserXmlException;

public enum MedicineXmlTag {
    MEDICINES("medicines"),
    NAME("name"),
    ANTIBIOTIC("antibiotic"),
    ANTIVIRAL("antiviral"),
    PAINKILLER("painkiller"),
    VITAMIN("vitamin"),
    PHARM("pharm"),
    ANALOGS("analogs"),
    VERSIONS("versions"),
    VERSION("version"),
    EXECUTION("execution"),
    CERTIFICATE("certificate"),
    REGISTRATION_NUMBER("registration-number"),
    REGISTERING_ORGANIZATION("registering-organization"),
    EXPIRATION_DATE("expiration-date"),
    START_DATE("start-date"),
    END_DATE("end-date"),
    PACKAGE_OF_MEDICINE("package-of-medicine"),
    TYPE("type"),
    COUNT("count"),
    PRICE("price"),
    DOSAGE("dosage"),
    VALUE_OF_DOSAGE("value-of-dosage"),
    FREQUENCY_OF_ADMISSION("frequency-of-admission"),
    EXPIRATION_DATE_OF_MEDICINE("expiration-date-of-medicine"),
    PRESCRIPTION("prescription"),
    ANTIVIRAL_GROUP("antiviral-group"),
    POWER("power"),
    TASTE("taste");

    private final String value;

    MedicineXmlTag(String value) {
        this.value = value;
    }

    public static MedicineXmlTag getMedicineXmlTag(String name) throws CustomParserXmlException {
        for (MedicineXmlTag tag : MedicineXmlTag.values()) {
            if (name.equals(tag.getValue())) {
                return tag;
            }
        }
        throw new CustomParserXmlException("Unknown tag <" + name + ">");
    }

    public String getValue() {
        return value;
    }
}
