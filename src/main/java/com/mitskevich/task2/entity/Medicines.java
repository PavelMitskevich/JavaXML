package com.mitskevich.task2.entity;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;

import java.util.HashSet;
import java.util.Set;

public class Medicines {
    Set<AbstractMedicine> abstractMedicines;

    @XmlElementWrapper
    @XmlElements({
            @XmlElement(type = Antibiotic.class, name = "antibiotic"),
            @XmlElement(type = Antiviral.class, name = "antiviral"),
            @XmlElement(type = Painkiller.class, name = "painkiller"),
            @XmlElement(type = Vitamin.class, name = "vitamin")
    })

    public Set<AbstractMedicine> getAbstractMedicines() {
        return abstractMedicines;
    }

    public void setAbstractMedicines(Set<AbstractMedicine> abstractMedicines) {
        this.abstractMedicines = abstractMedicines;
    }

    public void add(AbstractMedicine product) {
        if (this.abstractMedicines == null) {
            this.abstractMedicines = new HashSet<>();
        }
        this.abstractMedicines.add(product);
    }
}