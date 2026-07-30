# API Examples

Run the service with `mvn spring-boot:run`, then call these endpoints from a terminal, browser, Postman, or Power BI.

## KPI Summary

```bash
curl "http://localhost:8080/api/dashboard/kpis?startDate=2024-01-01&endDate=2024-12-31"
```

Example response:

```json
{
  "totalRevenue": 201300.00,
  "totalProfit": 106100.00,
  "unitsSold": 126,
  "orderCount": 20,
  "averageOrderValue": 10065.00,
  "profitMarginPercent": 52.71
}
```

## Monthly Trend

```bash
curl "http://localhost:8080/api/dashboard/revenue-trend"
```

Use this response for a line chart with `monthLabel` on the axis and `revenue` plus `profit` as values.

## Category Performance

```bash
curl "http://localhost:8080/api/dashboard/category-performance"
```

Use this response for a sorted bar chart by category.

## Segment Performance

```bash
curl "http://localhost:8080/api/dashboard/segment-performance"
```

Use this response for a matrix with segment rows, region columns, and revenue/profit values.

## Sales Records

```bash
curl "http://localhost:8080/api/dashboard/sales-records"
```

Use this as a Power BI fact table when building a semantic model with DAX measures.
