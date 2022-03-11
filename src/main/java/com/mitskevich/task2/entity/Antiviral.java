package com.mitskevich.task2.entity;

import java.util.List;

public class Antiviral extends AbstractMedicine {
    private Group group = Group.ANTIVIRAL;

    public Antiviral() {
        super();
    }

    public Antiviral(String name, String pharm, List<String> analogs, List<Version> versions) {
        super(name, pharm, analogs, versions);
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
}
