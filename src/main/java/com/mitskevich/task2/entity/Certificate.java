package com.mitskevich.task2.entity;

public class Certificate {
    private int registrationNumber;
    private String registeringOrganization;
    private ExpirationDate expirationDate;

    public Certificate() {
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Certificate that = (Certificate) o;
        if (registrationNumber != that.registrationNumber) {
            return false;
        }
        if (registeringOrganization != null ? !registeringOrganization.equals(that.registeringOrganization) : that.registeringOrganization != null) {
            return false;
        }
        return expirationDate != null ? expirationDate.equals(that.expirationDate) : that.expirationDate == null;
    }

    @Override
    public int hashCode() {
        int result = registrationNumber;
        result = 31 * result + (registeringOrganization != null ? registeringOrganization.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        return result;
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