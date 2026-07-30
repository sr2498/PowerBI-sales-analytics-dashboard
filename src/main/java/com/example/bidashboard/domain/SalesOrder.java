package com.example.bidashboard.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales_orders")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String channel;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal revenue;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal profit;

    protected SalesOrder() {
    }

    public SalesOrder(String orderNumber, LocalDate orderDate, Customer customer, Product product,
                      String region, String channel, Integer quantity,
                      BigDecimal revenue, BigDecimal cost, BigDecimal profit) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customer = customer;
        this.product = product;
        this.region = region;
        this.channel = channel;
        this.quantity = quantity;
        this.revenue = revenue;
        this.cost = cost;
        this.profit = profit;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
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

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}

