package com.example.bidashboard.dto;

import java.math.BigDecimal;

public class SegmentPerformanceDto {

    private final String segment;
    private final String region;
    private final BigDecimal revenue;
    private final BigDecimal profit;
    private final Long orderCount;

    public SegmentPerformanceDto(String segment, String region, BigDecimal revenue, BigDecimal profit, Long orderCount) {
        this.segment = segment;
        this.region = region;
        this.revenue = revenue;
        this.profit = profit;
        this.orderCount = orderCount;
    }

    public String getSegment() {
        return segment;
    }

    public String getRegion() {
        return region;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public Long getOrderCount() {
        return orderCount;
    }
}

