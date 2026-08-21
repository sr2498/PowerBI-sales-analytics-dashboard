# Business Intelligence Dashboard - Power BI Project

Portfolio project showing a Java 8 Spring Boot data service that feeds a Power BI business intelligence dashboard. The project includes REST APIs, SQL aggregation examples, sample data, Power Query scripts, DAX measures, validation, exception handling, and JUnit/Mockito tests.

## What This Shows

- Built a Java 8 and Spring Boot data service exposing REST APIs for Power BI reporting and business-performance analytics.
- Implemented persistence with Hibernate/JPA and SQL, using repository and service-layer patterns to retrieve and aggregate dashboard data.
- Integrated REST and CSV data sources, transformed data with Power Query, and created DAX measures for KPI dashboards, trend analysis, and interactive visuals.
- Added request validation, centralized exception handling, and tested Java service components with JUnit and Mockito.

## Architecture

```mermaid
flowchart LR
    CSV["CSV sample data"] --> PQ["Power Query transformations"]
    H2["H2 database seeded by data.sql"] --> JPA["Hibernate/JPA repositories"]
    SQL["SQL examples"] --> JPA
    JPA --> Service["Spring service layer"]
    Service --> REST["REST analytics APIs"]
    REST --> PQ
    PQ --> Model["Power BI data model"]
    DAX["DAX measures"] --> Model
    Model --> Report["KPI dashboard, trend analysis, category and segment visuals"]
```

## Quick Start

Requirements:

- Java 8 or newer
- Maven 3.8 or newer
- Power BI Desktop, optional for building the report

Run the API:

```bash
mvn spring-boot:run
```

Try an endpoint:

```bash
curl "http://localhost:8080/api/dashboard/kpis?startDate=2024-01-01&endDate=2024-12-31"
```

Run tests:

```bash
mvn test
```

## Power BI Report Build

This repository intentionally keeps Power BI assets in source-control friendly formats instead of committing a binary `.pbix` file.

For a quick visual preview of the finished report concept, open [docs/dashboard-preview.html](docs/dashboard-preview.html) in a browser. It includes KPI cards, a monthly trend chart, category performance bars, a channel revenue chart, and a segment/region matrix using the included sample data.

1. Start the Spring Boot API.
2. In Power BI Desktop, use **Get Data > Blank Query > Advanced Editor**.
3. Paste the scripts from [powerbi/power-query](powerbi/power-query).
4. Load the CSV files from [data](data) or use the `SalesRecordsApi.pq` query for the API-backed fact table.
5. Create measures from [powerbi/dax/measures.dax](powerbi/dax/measures.dax).
6. Follow [docs/dashboard-layout.md](docs/dashboard-layout.md) to assemble the report page.

Recommended visuals:

- KPI cards for total revenue, profit margin, total orders, and average order value
- Line chart for monthly revenue and profit trend
- Bar chart for product category performance
- Matrix or stacked bar chart for customer segment by region
- Slicers for date, region, channel, category, and segment

## Example Dashboard Story

The sample data models a small sales organization across three regions and multiple channels. The dashboard helps leadership answer:

- Which product categories generate the most revenue and profit?
- How are sales trending month over month?
- Which customer segments and regions are strongest?
- Are online, partner, and direct channels performing differently?

