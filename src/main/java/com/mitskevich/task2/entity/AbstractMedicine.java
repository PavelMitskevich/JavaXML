package com.mitskevich.task2.entity;

import java.util.List;

public abstract class AbstractMedicine {
    private String name;
    private String pharm;
    private List<String> analogs;
    private List<Version> versions;
    private Group group;

    public AbstractMedicine() {
    }

    public AbstractMedicine(String name, String pharm, List<String> analogs, List<Version> versions, Group group) {
        this.name = name;
        this.pharm = pharm;
        this.analogs = analogs;
        this.versions = versions;
        this.group = group;
    }

    public Group getGroup() {
        return group;
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
        return analogs;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }
}
