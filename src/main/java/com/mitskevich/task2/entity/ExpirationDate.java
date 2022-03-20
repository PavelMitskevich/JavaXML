package com.mitskevich.task2.entity;

import java.time.YearMonth;

public class ExpirationDate {
    private YearMonth startDate;
    private YearMonth endDate;

    public ExpirationDate() {
    }

    public ExpirationDate(YearMonth startDate, YearMonth endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public YearMonth getStartDate() {
        return startDate;
    }

    public void setStartDate(YearMonth startDate) {
        this.startDate = startDate;
    }

    public YearMonth getEndDate() {
        return endDate;
    }

    public void setEndDate(YearMonth endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExpirationDate{");
        sb.append("startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }
}
