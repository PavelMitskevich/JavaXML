package com.mitskevich.task2.entity;

import java.time.YearMonth;
import java.util.List;

public class Vitamin extends AbstractMedicine {
    private Group group = Group.VITAMIN;
    private String taste;

    public Vitamin() {
    }

    public Vitamin(String name, String pharm, List<String> analogs, List<Version> versions, YearMonth expirationDateOfMedicine, String taste) {
        super(name, pharm, analogs, versions, expirationDateOfMedicine);
        this.taste = taste;
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

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Vitamin vitamin = (Vitamin) o;
        if (group != vitamin.group) {
            return false;
        }
        return taste != null ? taste.equals(vitamin.taste) : vitamin.taste == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (taste != null ? taste.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.valueOf(getGroup()));
        sb.append(super.toString());
        sb.append(", taste=").append(taste);
        sb.append('}');
        return sb.toString();
    }
}