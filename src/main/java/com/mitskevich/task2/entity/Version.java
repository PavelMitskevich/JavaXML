package com.mitskevich.task2.entity;

public class Version {
    private String execution;
    private Certificate certificate;
    private PackageOfMedicine packageOfMedicine;
    private Dosage dosage;

    public Version() {
    }

    public Version(String execution, Certificate certificate, PackageOfMedicine packageOfMedicine, Dosage dosage) {
        this.execution = execution;
        this.certificate = certificate;
        this.packageOfMedicine = packageOfMedicine;
        this.dosage = dosage;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public PackageOfMedicine getPackageOfMedicine() {
        return packageOfMedicine;
    }

    public void setPackageOfMedicine(PackageOfMedicine packageOfMedicine) {
        this.packageOfMedicine = packageOfMedicine;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Version{");
        sb.append("execution='").append(execution).append('\'');
        sb.append(", certificate=").append(certificate);
        sb.append(", packageOfMedicine=").append(packageOfMedicine);
        sb.append(", dosage=").append(dosage);
        sb.append('}');
        return sb.toString();
    }
}
