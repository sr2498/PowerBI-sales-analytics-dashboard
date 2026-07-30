package com.example.bidashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.bidashboard.dto.CategoryPerformanceDto;
import com.example.bidashboard.dto.KpiSummaryDto;
import com.example.bidashboard.dto.RevenueTrendDto;
import com.example.bidashboard.dto.SalesRecordDto;
import com.example.bidashboard.dto.SegmentPerformanceDto;
import com.example.bidashboard.service.DashboardService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/kpis")
    public KpiSummaryDto getKpis(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return dashboardService.getKpis(startDate, endDate);
    }

    @GetMapping("/revenue-trend")
    public List<RevenueTrendDto> getRevenueTrend(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return dashboardService.getRevenueTrend(startDate, endDate);
    }

    @GetMapping("/category-performance")
    public List<CategoryPerformanceDto> getCategoryPerformance(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return dashboardService.getCategoryPerformance(startDate, endDate);
    }

    @GetMapping("/segment-performance")
    public List<SegmentPerformanceDto> getSegmentPerformance(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return dashboardService.getSegmentPerformance(startDate, endDate);
    }

    @GetMapping("/sales-records")
    public List<SalesRecordDto> getSalesRecords(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return dashboardService.getSalesRecords(startDate, endDate);
    }
}

