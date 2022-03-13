package com.mitskevich.task2.entity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMedicine {
    private String name;
    private String pharm;
    private List<String> analogs;
    private List<Version> versions;
    private YearMonth expirationDateOfMedicine;

    public AbstractMedicine() {
    }

    public AbstractMedicine(String name, String pharm, List<String> analogs, List<Version> versions, YearMonth expirationDateOfMedicine) {
        this.name = name;
        this.pharm = pharm;
        this.analogs = analogs;
        this.versions = versions;
        this.expirationDateOfMedicine = expirationDateOfMedicine;
    }

    public YearMonth getExpirationDateOfMedicine() {
        return expirationDateOfMedicine;
    }

    public void setExpirationDateOfMedicine(YearMonth expirationDateOfMedicine) {
        this.expirationDateOfMedicine = expirationDateOfMedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public List<String> getAnalogs() {
        return new ArrayList<>(analogs);
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs != null ? new ArrayList<>(analogs) : new ArrayList<>();
    }

    public List<Version> getVersions() {
        return new ArrayList<>(versions);
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions != null ? new ArrayList<>(versions) : new ArrayList<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{name='").append(name).append('\'');
        sb.append(", pharm='").append(pharm).append('\'');
        sb.append(", analogs=").append(analogs);
        sb.append(", versions=").append(versions);
        sb.append(", expirationDateOfMedicine=").append(expirationDateOfMedicine);
        return sb.toString();
    }
}
