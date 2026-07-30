package com.example.bidashboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String segment;

    @Column(name = "home_region", nullable = false)
    private String homeRegion;

    protected Customer() {
    }

    public Customer(String name, String segment, String homeRegion) {
        this.name = name;
        this.segment = segment;
        this.homeRegion = homeRegion;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSegment() {
        return segment;
    }

    public String getHomeRegion() {
        return homeRegion;
    }
}

