package com.mitskevich.task2.entity;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "group")
@XmlEnum
public enum Group {
    @XmlEnumValue("Antibiotic")
    ANTIBIOTIC("Antibiotic"),
    @XmlEnumValue("Painkiller")
    PAINKILLER("Painkiller"),
    @XmlEnumValue("Vitamin")
    VITAMIN("Vitamin"),
    @XmlEnumValue("Antiviral")
    ANTIVIRAL("Antiviral");

    private String name;

    Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Group getProduction(String name) {
        for (Group production : Group.values()) {
            if (name.equals(production.getName())) {
                return production;
            }
        }
        return null;
    }
}