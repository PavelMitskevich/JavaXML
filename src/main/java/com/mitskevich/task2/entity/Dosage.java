package com.mitskevich.task2.entity;

public class Dosage {
    private double valueOfDosage;
    private int frequencyOfAdmission;

    public Dosage(double valueOfDosage, int frequencyOfAdmission) {
        this.valueOfDosage = valueOfDosage;
        this.frequencyOfAdmission = frequencyOfAdmission;
    }

    public double getValueOfDosage() {
        return valueOfDosage;
    }

    public void setValueOfDosage(double valueOfDosage) {
        this.valueOfDosage = valueOfDosage;
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
        sb.append("medicineDosage=").append(valueOfDosage);
        sb.append(", frequencyOfAdmission=").append(frequencyOfAdmission);
        sb.append('}');
        return sb.toString();
    }
}
