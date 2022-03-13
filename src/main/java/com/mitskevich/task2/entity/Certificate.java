package com.mitskevich.task2.entity;

public class Certificate {
    private int regNumber;
    private String registeringOrganization;
    private ExpirationDate expirationDate;

    public Certificate(int regNumber, ExpirationDate expirationDate, String registeringOrganization) {
        this.regNumber = regNumber;
        this.expirationDate = expirationDate;
        this.registeringOrganization = registeringOrganization;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public ExpirationDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ExpirationDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRegisteringOrganization() {
        return registeringOrganization;
    }

    public void setRegisteringOrganization(String registeringOrganization) {
        this.registeringOrganization = registeringOrganization;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Certificate{");
        sb.append("regNumber='").append(regNumber).append('\'');
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", registeringOrganization='").append(registeringOrganization).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
