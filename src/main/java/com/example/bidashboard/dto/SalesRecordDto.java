package com.example.bidashboard.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesRecordDto {

    private final Long orderId;
    private final String orderNumber;
    private final LocalDate orderDate;
    private final String customerName;
    private final String segment;
    private final String productName;
    private final String category;
    private final String region;
    private final String channel;
    private final Integer quantity;
    private final BigDecimal revenue;
    private final BigDecimal profit;

    public SalesRecordDto(Long orderId, String orderNumber, LocalDate orderDate, String customerName, String segment,
                          String productName, String category, String region, String channel, Integer quantity,
                          BigDecimal revenue, BigDecimal profit) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.segment = segment;
        this.productName = productName;
        this.category = category;
        this.region = region;
        this.channel = channel;
        this.quantity = quantity;
        this.revenue = revenue;
        this.profit = profit;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSegment() {
        return segment;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getRegion() {
        return region;
    }

    public String getChannel() {
        return channel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}

