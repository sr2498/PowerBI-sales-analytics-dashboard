let
    SalesOrders = Csv.Document(
        File.Contents("C:\Users\sandh\OneDrive\Documents\Power BI project\data\sales_orders.csv"),
        [Delimiter = ",", Columns = 11, Encoding = 65001, QuoteStyle = QuoteStyle.None]
    ),
    SalesHeaders = Table.PromoteHeaders(SalesOrders, [PromoteAllScalars = true]),
    SalesTyped = Table.TransformColumnTypes(
        SalesHeaders,
        {
            {"order_id", Int64.Type},
            {"order_number", type text},
            {"order_date", type date},
            {"customer_id", Int64.Type},
            {"product_id", Int64.Type},
            {"region", type text},
            {"channel", type text},
            {"quantity", Int64.Type},
            {"revenue", Currency.Type},
            {"cost", Currency.Type},
            {"profit", Currency.Type}
        }
    ),
    SalesWithMonth = Table.AddColumn(SalesTyped, "month_start", each Date.StartOfMonth([order_date]), type date),

    Customers = Csv.Document(
        File.Contents("C:\Users\sandh\OneDrive\Documents\Power BI project\data\customers.csv"),
        [Delimiter = ",", Columns = 4, Encoding = 65001, QuoteStyle = QuoteStyle.None]
    ),
    CustomerHeaders = Table.PromoteHeaders(Customers, [PromoteAllScalars = true]),
    CustomersTyped = Table.TransformColumnTypes(
        CustomerHeaders,
        {
            {"customer_id", Int64.Type},
            {"customer_name", type text},
            {"segment", type text},
            {"home_region", type text}
        }
    ),

    Products = Csv.Document(
        File.Contents("C:\Users\sandh\OneDrive\Documents\Power BI project\data\products.csv"),
        [Delimiter = ",", Columns = 4, Encoding = 65001, QuoteStyle = QuoteStyle.None]
    ),
    ProductHeaders = Table.PromoteHeaders(Products, [PromoteAllScalars = true]),
    ProductsTyped = Table.TransformColumnTypes(
        ProductHeaders,
        {
            {"product_id", Int64.Type},
            {"sku", type text},
            {"product_name", type text},
            {"category", type text}
        }
    ),

    JoinedCustomers = Table.NestedJoin(
        SalesWithMonth,
        {"customer_id"},
        CustomersTyped,
        {"customer_id"},
        "Customer",
        JoinKind.LeftOuter
    ),
    ExpandedCustomers = Table.ExpandTableColumn(
        JoinedCustomers,
        "Customer",
        {"customer_name", "segment", "home_region"}
    ),
    JoinedProducts = Table.NestedJoin(
        ExpandedCustomers,
        {"product_id"},
        ProductsTyped,
        {"product_id"},
        "Product",
        JoinKind.LeftOuter
    ),
    ExpandedProducts = Table.ExpandTableColumn(
        JoinedProducts,
        "Product",
        {"sku", "product_name", "category"}
    )
in
    ExpandedProducts

