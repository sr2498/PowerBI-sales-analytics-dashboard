package com.example.bidashboard.controller;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.bidashboard.dto.KpiSummaryDto;
import com.example.bidashboard.service.DashboardService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DashboardController.class)
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardService dashboardService;

    @Test
    void getKpisReturnsSummary() throws Exception {
        KpiSummaryDto summary = new KpiSummaryDto(
                new BigDecimal("201300.00"),
                new BigDecimal("106100.00"),
                126L,
                20L,
                new BigDecimal("10065.00"),
                new BigDecimal("52.71")
        );

        when(dashboardService.getKpis(null, null)).thenReturn(summary);

        mockMvc.perform(get("/api/dashboard/kpis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalRevenue").value(201300.00))
                .andExpect(jsonPath("$.totalProfit").value(106100.00))
                .andExpect(jsonPath("$.unitsSold").value(126))
                .andExpect(jsonPath("$.orderCount").value(20))
                .andExpect(jsonPath("$.averageOrderValue").value(10065.00))
                .andExpect(jsonPath("$.profitMarginPercent").value(52.71));

        verify(dashboardService).getKpis(isNull(), isNull());
    }

    @Test
    void getKpisReturnsBadRequestForInvalidDateRange() throws Exception {
        LocalDate startDate = LocalDate.of(2024, 12, 31);
        LocalDate endDate = LocalDate.of(2024, 1, 1);

        when(dashboardService.getKpis(startDate, endDate))
                .thenThrow(new IllegalArgumentException("startDate must be on or before endDate"));

        mockMvc.perform(get("/api/dashboard/kpis")
                        .param("startDate", "2024-12-31")
                        .param("endDate", "2024-01-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("startDate must be on or before endDate"));
    }

    @Test
    void getKpisReturnsBadRequestForMalformedDate() throws Exception {
        mockMvc.perform(get("/api/dashboard/kpis")
                        .param("startDate", "01-01-2024"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Invalid value for parameter 'startDate'. Use yyyy-MM-dd dates."));
    }
}
