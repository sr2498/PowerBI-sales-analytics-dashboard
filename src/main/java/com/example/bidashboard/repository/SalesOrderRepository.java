package com.example.bidashboard.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.bidashboard.domain.SalesOrder;
import com.example.bidashboard.dto.CategoryPerformanceDto;
import com.example.bidashboard.dto.RevenueTrendDto;
import com.example.bidashboard.dto.SalesRecordDto;
import com.example.bidashboard.dto.SegmentPerformanceDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    @Query("select sum(o.revenue), sum(o.profit), sum(o.quantity), count(o) " +
            "from SalesOrder o " +
            "where o.orderDate between :startDate and :endDate")
    Object[] summarizeKpis(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select new com.example.bidashboard.dto.RevenueTrendDto(" +
            "year(o.orderDate), month(o.orderDate), sum(o.revenue), sum(o.profit), sum(o.quantity)) " +
            "from SalesOrder o " +
            "where o.orderDate between :startDate and :endDate " +
            "group by year(o.orderDate), month(o.orderDate) " +
            "order by year(o.orderDate), month(o.orderDate)")
    List<RevenueTrendDto> findRevenueTrendBetween(@Param("startDate") LocalDate startDate,
                                                   @Param("endDate") LocalDate endDate);

    @Query("select new com.example.bidashboard.dto.CategoryPerformanceDto(" +
            "p.category, sum(o.revenue), sum(o.profit), sum(o.quantity)) " +
            "from SalesOrder o join o.product p " +
            "where o.orderDate between :startDate and :endDate " +
            "group by p.category " +
            "order by sum(o.revenue) desc")
    List<CategoryPerformanceDto> findCategoryPerformanceBetween(@Param("startDate") LocalDate startDate,
                                                                 @Param("endDate") LocalDate endDate);

    @Query("select new com.example.bidashboard.dto.SegmentPerformanceDto(" +
            "c.segment, o.region, sum(o.revenue), sum(o.profit), count(o)) " +
            "from SalesOrder o join o.customer c " +
            "where o.orderDate between :startDate and :endDate " +
            "group by c.segment, o.region " +
            "order by c.segment, sum(o.revenue) desc")
    List<SegmentPerformanceDto> findSegmentPerformanceBetween(@Param("startDate") LocalDate startDate,
                                                               @Param("endDate") LocalDate endDate);

    @Query("select new com.example.bidashboard.dto.SalesRecordDto(" +
            "o.id, o.orderNumber, o.orderDate, c.name, c.segment, p.name, p.category, " +
            "o.region, o.channel, o.quantity, o.revenue, o.profit) " +
            "from SalesOrder o join o.customer c join o.product p " +
            "where o.orderDate between :startDate and :endDate " +
            "order by o.orderDate, o.id")
    List<SalesRecordDto> findSalesRecordsBetween(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);
}
