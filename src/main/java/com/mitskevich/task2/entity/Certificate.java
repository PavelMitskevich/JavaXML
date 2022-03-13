package com.mitskevich.task2.entity;

public class Certificate {
    private int registrationNumber;
    private String registeringOrganization;
    private ExpirationDate expirationDate;

    public Certificate(int registrationNumber, ExpirationDate expirationDate, String registeringOrganization) {
        this.registrationNumber = registrationNumber;
        this.expirationDate = expirationDate;
        this.registeringOrganization = registeringOrganization;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
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
        sb.append("regNumber='").append(registrationNumber).append('\'');
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", registeringOrganization='").append(registeringOrganization).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
