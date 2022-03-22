package com.mitskevich.task2.entity;

import com.mitskevich.task2.util.IdGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public abstract class AbstractMedicine implements Serializable {
    private int id;
    private String name;
    private String pharm;
    private List<String> analogs;
    private List<Version> versions;
    private YearMonth expirationDateOfMedicine;

    public AbstractMedicine() {
        id = IdGenerator.generateId();
    }

    public AbstractMedicine(String name, String pharm, List<String> analogs, List<Version> versions, YearMonth expirationDateOfMedicine) {
        id = IdGenerator.generateId();
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractMedicine that = (AbstractMedicine) o;
        if (id != that.id) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (pharm != null ? !pharm.equals(that.pharm) : that.pharm != null) {
            return false;
        }
        if (analogs != null ? !analogs.equals(that.analogs) : that.analogs != null) {
            return false;
        }
        if (versions != null ? !versions.equals(that.versions) : that.versions != null) {
            return false;
        }
        return expirationDateOfMedicine != null ? expirationDateOfMedicine.equals(that.expirationDateOfMedicine) : that.expirationDateOfMedicine == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pharm != null ? pharm.hashCode() : 0);
        result = 31 * result + (analogs != null ? analogs.hashCode() : 0);
        result = 31 * result + (versions != null ? versions.hashCode() : 0);
        result = 31 * result + (expirationDateOfMedicine != null ? expirationDateOfMedicine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{name='").append(name).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", pharm='").append(pharm).append('\'');
        sb.append(", analogs=").append(analogs);
        sb.append(", versions=").append(versions);
        sb.append(", expirationDateOfMedicine=").append(expirationDateOfMedicine);
        return sb.toString();
    }
}