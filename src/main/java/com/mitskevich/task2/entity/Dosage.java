package com.mitskevich.task2.entity;

public class Dosage {
    private double medicineDosage;
    private int frequencyOfAdmission;

    public Dosage(double medicineDosage, int frequencyOfAdmission) {
        this.medicineDosage = medicineDosage;
        this.frequencyOfAdmission = frequencyOfAdmission;
    }

    public double getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(double medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public int getFrequencyOfAdmission() {
        return frequencyOfAdmission;
    }

    public void setFrequencyOfAdmission(int frequencyOfAdmission) {
        this.frequencyOfAdmission = frequencyOfAdmission;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dosage{");
        sb.append("medicineDosage=").append(medicineDosage);
        sb.append(", frequencyOfAdmission=").append(frequencyOfAdmission);
        sb.append('}');
        return sb.toString();
    }
}
