package com.mitskevich.task2.entity;

import java.time.YearMonth;
import java.util.List;

public class Antiviral extends AbstractMedicine {
    private Group group = Group.ANTIVIRAL;
    private String antiviralGroup;

    public Antiviral() {
    }

    public Antiviral(String name, String pharm, List<String> analogs, List<Version> versions, YearMonth expirationDateOfMedicine, String antiviralGroup) {
        super(name, pharm, analogs, versions, expirationDateOfMedicine);
        this.antiviralGroup = antiviralGroup;
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


    public String getAntiviralGroup() {
        return antiviralGroup;
    }

    public void setAntiviralGroup(String antiviralGroup) {
        this.antiviralGroup = antiviralGroup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf(getGroup()));
        sb.append(super.toString());
        sb.append(", antiviral group=").append(antiviralGroup);
        sb.append('}');
        return sb.toString();
    }
}
