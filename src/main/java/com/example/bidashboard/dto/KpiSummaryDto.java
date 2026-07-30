package com.example.bidashboard.dto;

import java.math.BigDecimal;

public class KpiSummaryDto {

    private final BigDecimal totalRevenue;
    private final BigDecimal totalProfit;
    private final Long unitsSold;
    private final Long orderCount;
    private final BigDecimal averageOrderValue;
    private final BigDecimal profitMarginPercent;

    public KpiSummaryDto(BigDecimal totalRevenue, BigDecimal totalProfit, Long unitsSold, Long orderCount,
                         BigDecimal averageOrderValue, BigDecimal profitMarginPercent) {
        this.totalRevenue = totalRevenue;
        this.totalProfit = totalProfit;
        this.unitsSold = unitsSold;
        this.orderCount = orderCount;
        this.averageOrderValue = averageOrderValue;
        this.profitMarginPercent = profitMarginPercent;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public Long getUnitsSold() {
        return unitsSold;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public BigDecimal getAverageOrderValue() {
        return averageOrderValue;
    }

    public BigDecimal getProfitMarginPercent() {
        return profitMarginPercent;
    }
}

