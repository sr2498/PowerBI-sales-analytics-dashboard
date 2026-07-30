# Dashboard Layout

This report layout is designed for a single executive summary page in Power BI Desktop.

## Canvas

- Page size: 16:9
- Theme: light background, dark text, restrained blue/teal accents
- Slicers: date range, region, channel, customer segment, product category

## Top KPI Row

Create four card visuals:

- Total Revenue
- Total Profit
- Profit Margin %
- Average Order Value

Format currency values with zero decimal places and show profit margin as a percentage with one decimal place.

## Middle Row

Add a line chart:

- Axis: `Date[Month]` or `SalesRecords[monthStart]`
- Values: `Total Revenue`, `Total Profit`
- Purpose: monthly trend analysis

Add a clustered bar chart:

- Axis: `SalesRecords[category]`
- Values: `Total Revenue`, `Total Profit`
- Sort: descending by `Total Revenue`

## Bottom Row

Add a matrix:

- Rows: `SalesRecords[segment]`
- Columns: `SalesRecords[region]`
- Values: `Total Revenue`, `Total Profit`, `Total Orders`

Add a bar or doughnut chart:

- Legend: `SalesRecords[channel]`
- Values: `Total Revenue`

## Suggested Data Model

- `SalesRecords` as the fact table
- `Date` calendar table related to `SalesRecords[orderDate]`
- Optional dimensions from the CSV model:
  - `Customers`
  - `Products`

Example calendar table:

```DAX
Date =
ADDCOLUMNS (
    CALENDAR ( DATE ( 2024, 1, 1 ), DATE ( 2024, 12, 31 ) ),
    "Year", YEAR ( [Date] ),
    "Month Number", MONTH ( [Date] ),
    "Month", FORMAT ( [Date], "MMM yyyy" )
)
```

