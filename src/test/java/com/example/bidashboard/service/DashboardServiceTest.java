package com.example.bidashboard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.bidashboard.dto.KpiSummaryDto;
import com.example.bidashboard.repository.SalesOrderRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private SalesOrderRepository salesOrderRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void getKpisCalculatesAverageOrderValueAndProfitMargin() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Object[] summary = {
                new BigDecimal("201300.00"),
                new BigDecimal("106100.00"),
                126L,
                20L
        };

        when(salesOrderRepository.summarizeKpis(startDate, endDate)).thenReturn(summary);

        KpiSummaryDto result = dashboardService.getKpis(startDate, endDate);

        assertEquals(new BigDecimal("201300.00"), result.getTotalRevenue());
        assertEquals(new BigDecimal("106100.00"), result.getTotalProfit());
        assertEquals(126L, result.getUnitsSold());
        assertEquals(20L, result.getOrderCount());
        assertEquals(new BigDecimal("10065.00"), result.getAverageOrderValue());
        assertEquals(new BigDecimal("52.71"), result.getProfitMarginPercent());
        verify(salesOrderRepository).summarizeKpis(startDate, endDate);
    }

    @Test
    void getKpisUsesDefaultDateRangeWhenQueryDatesAreMissing() {
        Object[] emptySummary = {null, null, null, 0L};

        when(salesOrderRepository.summarizeKpis(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)))
                .thenReturn(emptySummary);

        KpiSummaryDto result = dashboardService.getKpis(null, null);

        assertEquals(new BigDecimal("0.00"), result.getTotalRevenue());
        assertEquals(new BigDecimal("0.00"), result.getTotalProfit());
        assertEquals(0L, result.getUnitsSold());
        assertEquals(0L, result.getOrderCount());
        assertEquals(new BigDecimal("0.00"), result.getAverageOrderValue());
        assertEquals(new BigDecimal("0.00"), result.getProfitMarginPercent());
    }

    @Test
    void getKpisRejectsInvalidDateRange() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> dashboardService.getKpis(LocalDate.of(2024, 12, 31), LocalDate.of(2024, 1, 1))
        );

        assertEquals("startDate must be on or before endDate", exception.getMessage());
        verifyNoInteractions(salesOrderRepository);
    }
}
