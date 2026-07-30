package com.example.bidashboard.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import com.example.bidashboard.dto.CategoryPerformanceDto;
import com.example.bidashboard.dto.KpiSummaryDto;
import com.example.bidashboard.dto.RevenueTrendDto;
import com.example.bidashboard.dto.SalesRecordDto;
import com.example.bidashboard.dto.SegmentPerformanceDto;
import com.example.bidashboard.repository.SalesOrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.of(2024, 1, 1);
    private static final LocalDate DEFAULT_END_DATE = LocalDate.of(2024, 12, 31);

    private final SalesOrderRepository salesOrderRepository;

    public DashboardService(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    public KpiSummaryDto getKpis(LocalDate startDate, LocalDate endDate) {
        LocalDate[] range = normalizeRange(startDate, endDate);
        Object[] row = salesOrderRepository.summarizeKpis(range[0], range[1]);

        BigDecimal revenue = money(row, 0);
        BigDecimal profit = money(row, 1);
        Long unitsSold = number(row, 2);
        Long orderCount = number(row, 3);

        BigDecimal averageOrderValue = orderCount > 0
                ? revenue.divide(BigDecimal.valueOf(orderCount), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO.setScale(2);
        BigDecimal profitMarginPercent = revenue.compareTo(BigDecimal.ZERO) > 0
                ? profit.divide(revenue, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2)
                : BigDecimal.ZERO.setScale(2);

        return new KpiSummaryDto(revenue, profit, unitsSold, orderCount, averageOrderValue, profitMarginPercent);
    }

    public List<RevenueTrendDto> getRevenueTrend(LocalDate startDate, LocalDate endDate) {
        LocalDate[] range = normalizeRange(startDate, endDate);
        return salesOrderRepository.findRevenueTrendBetween(range[0], range[1]);
    }

    public List<CategoryPerformanceDto> getCategoryPerformance(LocalDate startDate, LocalDate endDate) {
        LocalDate[] range = normalizeRange(startDate, endDate);
        return salesOrderRepository.findCategoryPerformanceBetween(range[0], range[1]);
    }

    public List<SegmentPerformanceDto> getSegmentPerformance(LocalDate startDate, LocalDate endDate) {
        LocalDate[] range = normalizeRange(startDate, endDate);
        return salesOrderRepository.findSegmentPerformanceBetween(range[0], range[1]);
    }

    public List<SalesRecordDto> getSalesRecords(LocalDate startDate, LocalDate endDate) {
        LocalDate[] range = normalizeRange(startDate, endDate);
        return salesOrderRepository.findSalesRecordsBetween(range[0], range[1]);
    }

    private LocalDate[] normalizeRange(LocalDate startDate, LocalDate endDate) {
        LocalDate resolvedStart = startDate == null ? DEFAULT_START_DATE : startDate;
        LocalDate resolvedEnd = endDate == null ? DEFAULT_END_DATE : endDate;

        if (resolvedStart.isAfter(resolvedEnd)) {
            throw new IllegalArgumentException("startDate must be on or before endDate");
        }

        return new LocalDate[] {resolvedStart, resolvedEnd};
    }

    private BigDecimal money(Object[] row, int index) {
        if (row == null || row.length <= index || row[index] == null) {
            return BigDecimal.ZERO.setScale(2);
        }
        Object value = row[index];
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).setScale(2, RoundingMode.HALF_UP);
        }
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue()).setScale(2, RoundingMode.HALF_UP);
        }
        throw new IllegalArgumentException("Unexpected currency value: " + value);
    }

    private Long number(Object[] row, int index) {
        if (row == null || row.length <= index || row[index] == null) {
            return 0L;
        }
        Object value = row[index];
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        throw new IllegalArgumentException("Unexpected numeric value: " + value);
    }
}

