package com.mitskevich.task2.entity;

public class Dosage {
    private double valueOfDosage;
    private int frequencyOfAdmission;

    public Dosage() {
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dosage dosage = (Dosage) o;
        if (Double.compare(dosage.valueOfDosage, valueOfDosage) != 0) {
            return false;
        }
        return frequencyOfAdmission == dosage.frequencyOfAdmission;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(valueOfDosage);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + frequencyOfAdmission;
        return result;
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