-- KPI summary used by /api/dashboard/kpis
select
    sum(revenue) as total_revenue,
    sum(profit) as total_profit,
    sum(quantity) as units_sold,
    count(*) as order_count,
    sum(revenue) / nullif(count(*), 0) as average_order_value,
    (sum(profit) / nullif(sum(revenue), 0)) * 100 as profit_margin_percent
from sales_orders
where order_date between date '2024-01-01' and date '2024-12-31';

-- Monthly trend used by /api/dashboard/revenue-trend
select
    extract(year from order_date) as order_year,
    extract(month from order_date) as order_month,
    sum(revenue) as revenue,
    sum(profit) as profit,
    sum(quantity) as units_sold
from sales_orders
where order_date between date '2024-01-01' and date '2024-12-31'
group by extract(year from order_date), extract(month from order_date)
order by order_year, order_month;

-- Category performance used by /api/dashboard/category-performance
select
    p.category,
    sum(o.revenue) as revenue,
    sum(o.profit) as profit,
    sum(o.quantity) as units_sold
from sales_orders o
join products p on p.id = o.product_id
where o.order_date between date '2024-01-01' and date '2024-12-31'
group by p.category
order by revenue desc;

-- Segment and region performance used by /api/dashboard/segment-performance
select
    c.segment,
    o.region,
    sum(o.revenue) as revenue,
    sum(o.profit) as profit,
    count(*) as order_count
from sales_orders o
join customers c on c.id = o.customer_id
where o.order_date between date '2024-01-01' and date '2024-12-31'
group by c.segment, o.region
order by c.segment, revenue desc;

