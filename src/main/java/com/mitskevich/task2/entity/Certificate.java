package com.mitskevich.task2.entity;

public class Certificate {
    private String regNumber;
    private ExpirationDate expirationDate;
    private String registeringOrganization;

    public Certificate(String regNumber, ExpirationDate expirationDate, String registeringOrganization) {
        this.regNumber = regNumber;
        this.expirationDate = expirationDate;
        this.registeringOrganization = registeringOrganization;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
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
