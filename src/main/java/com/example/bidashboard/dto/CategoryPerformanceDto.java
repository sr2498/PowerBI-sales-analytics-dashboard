package com.example.bidashboard.dto;

import java.math.BigDecimal;

public class CategoryPerformanceDto {

    private final String category;
    private final BigDecimal revenue;
    private final BigDecimal profit;
    private final Long unitsSold;

    public CategoryPerformanceDto(String category, BigDecimal revenue, BigDecimal profit, Long unitsSold) {
        this.category = category;
        this.revenue = revenue;
        this.profit = profit;
        this.unitsSold = unitsSold;
    }

    public String getCategory() {
        return category;
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

