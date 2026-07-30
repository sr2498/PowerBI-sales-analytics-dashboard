package com.example.bidashboard.dto;

import java.math.BigDecimal;

public class RevenueTrendDto {

    private final Integer year;
    private final Integer month;
    private final String monthLabel;
    private final BigDecimal revenue;
    private final BigDecimal profit;
    private final Long unitsSold;

    public RevenueTrendDto(Integer year, Integer month, BigDecimal revenue, BigDecimal profit, Long unitsSold) {
        this.year = year;
        this.month = month;
        this.monthLabel = String.format("%04d-%02d", year, month);
        this.revenue = revenue;
        this.profit = profit;
        this.unitsSold = unitsSold;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public String getMonthLabel() {
        return monthLabel;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public Long getUnitsSold() {
        return unitsSold;
    }
}

