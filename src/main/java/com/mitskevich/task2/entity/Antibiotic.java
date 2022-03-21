package com.mitskevich.task2.entity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Antibiotic extends AbstractMedicine {
    private final Group group = Group.ANTIBIOTIC;
    private boolean prescription;

    public Antibiotic() {
    }

    public Antibiotic(String name, String pharm, List<String> analogs, List<Version> versions, YearMonth expirationDateOfMedicine, boolean prescription) {
        super(name, pharm, analogs, versions, expirationDateOfMedicine);
        this.prescription = prescription;
    }

    @Override
    public YearMonth getExpirationDateOfMedicine() {
        return super.getExpirationDateOfMedicine();
    }

    @Override
    public void setExpirationDateOfMedicine(YearMonth expirationDateOfMedicine) {
        super.setExpirationDateOfMedicine(expirationDateOfMedicine);
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getPharm() {
        return super.getPharm();
    }

    @Override
    public void setPharm(String pharm) {
        super.setPharm(pharm);
    }

    @Override
    public List<String> getAnalogs() {
        return super.getAnalogs();
    }

    @Override
    public void setAnalogs(List<String> analogs) {
        super.setAnalogs(analogs);
    }

    @Override
    public List<Version> getVersions() {
        return super.getVersions();
    }

    @Override
    public void setVersions(List<Version> versions) {
        super.setVersions(versions);
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf(getGroup()));
        sb.append(super.toString());
        sb.append(", prescription=").append(prescription);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        Antibiotic antibiotic = new Antibiotic();
        ExpirationDate expirationDate = new ExpirationDate(YearMonth.of(2021,3),YearMonth.of(2023,3));
        Certificate certificate = new Certificate(1234, expirationDate, "Company");
        PackageOfMedicine packageOfMedicine = new PackageOfMedicine("bottle", 100, 15.96);
        Dosage dosage = new Dosage(0.5, 3);
        Version version = new Version("drops", certificate, packageOfMedicine, dosage);
        antibiotic.setVersions(new ArrayList<>(Collections.singleton(version)));
        System.out.println(antibiotic);
    }
}
