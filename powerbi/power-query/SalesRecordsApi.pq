let
    StartDate = "2024-01-01",
    EndDate = "2024-12-31",
    Source = Json.Document(
        Web.Contents(
            "http://localhost:8080",
            [
                RelativePath = "api/dashboard/sales-records",
                Query = [startDate = StartDate, endDate = EndDate]
            ]
        )
    ),
    SalesTable = Table.FromRecords(Source),
    TypedColumns = Table.TransformColumnTypes(
        SalesTable,
        {
            {"orderId", Int64.Type},
            {"orderNumber", type text},
            {"orderDate", type date},
            {"customerName", type text},
            {"segment", type text},
            {"productName", type text},
            {"category", type text},
            {"region", type text},
            {"channel", type text},
            {"quantity", Int64.Type},
            {"revenue", Currency.Type},
            {"profit", Currency.Type}
        }
    ),
    AddedProfitMargin = Table.AddColumn(
        TypedColumns,
        "profitMargin",
        each if [revenue] = 0 then 0 else [profit] / [revenue],
        Percentage.Type
    ),
    AddedMonthStart = Table.AddColumn(
        AddedProfitMargin,
        "monthStart",
        each Date.StartOfMonth([orderDate]),
        type date
    )
in
    AddedMonthStart

