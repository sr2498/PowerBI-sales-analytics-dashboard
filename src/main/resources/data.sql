insert into customers (id, name, segment, home_region) values
  (1, 'Northwind Medical', 'Enterprise', 'North'),
  (2, 'Summit Outdoor Co.', 'Mid-Market', 'West'),
  (3, 'Brightpath Schools', 'Public Sector', 'South'),
  (4, 'Urban Bean Group', 'Small Business', 'East'),
  (5, 'Apex Manufacturing', 'Enterprise', 'West'),
  (6, 'Greenline Logistics', 'Mid-Market', 'North');

insert into products (id, sku, name, category) values
  (1, 'ANL-100', 'Analytics Starter Pack', 'Software'),
  (2, 'DWH-220', 'Data Warehouse Connector', 'Software'),
  (3, 'TRN-310', 'BI Enablement Workshop', 'Services'),
  (4, 'SUP-410', 'Premium Support Plan', 'Support'),
  (5, 'GOV-510', 'Data Governance Assessment', 'Services');

insert into sales_orders
  (id, order_number, order_date, customer_id, product_id, region, channel, quantity, revenue, cost, profit)
values
  (1, 'SO-2024-001', '2024-01-12', 1, 1, 'North', 'Direct', 8, 9600.00, 4200.00, 5400.00),
  (2, 'SO-2024-002', '2024-01-28', 2, 3, 'West', 'Partner', 5, 7500.00, 3500.00, 4000.00),
  (3, 'SO-2024-003', '2024-02-09', 3, 2, 'South', 'Online', 6, 10800.00, 5200.00, 5600.00),
  (4, 'SO-2024-004', '2024-02-21', 4, 4, 'East', 'Online', 3, 2700.00, 1200.00, 1500.00),
  (5, 'SO-2024-005', '2024-03-05', 5, 5, 'West', 'Direct', 4, 11200.00, 5800.00, 5400.00),
  (6, 'SO-2024-006', '2024-03-19', 6, 1, 'North', 'Partner', 7, 8400.00, 3700.00, 4700.00),
  (7, 'SO-2024-007', '2024-04-03', 1, 2, 'North', 'Direct', 9, 16200.00, 7600.00, 8600.00),
  (8, 'SO-2024-008', '2024-04-22', 2, 4, 'West', 'Online', 6, 5400.00, 2400.00, 3000.00),
  (9, 'SO-2024-009', '2024-05-10', 3, 3, 'South', 'Direct', 8, 12000.00, 5600.00, 6400.00),
  (10, 'SO-2024-010', '2024-05-24', 4, 1, 'East', 'Online', 5, 6000.00, 2600.00, 3400.00),
  (11, 'SO-2024-011', '2024-06-07', 5, 2, 'West', 'Partner', 10, 18000.00, 8600.00, 9400.00),
  (12, 'SO-2024-012', '2024-06-26', 6, 5, 'North', 'Direct', 3, 8400.00, 4300.00, 4100.00),
  (13, 'SO-2024-013', '2024-07-11', 1, 4, 'North', 'Online', 8, 7200.00, 3200.00, 4000.00),
  (14, 'SO-2024-014', '2024-07-30', 2, 1, 'West', 'Direct', 6, 7200.00, 3100.00, 4100.00),
  (15, 'SO-2024-015', '2024-08-14', 3, 5, 'South', 'Partner', 5, 14000.00, 7200.00, 6800.00),
  (16, 'SO-2024-016', '2024-08-29', 4, 3, 'East', 'Online', 4, 6000.00, 2800.00, 3200.00),
  (17, 'SO-2024-017', '2024-09-16', 5, 1, 'West', 'Direct', 9, 10800.00, 4700.00, 6100.00),
  (18, 'SO-2024-018', '2024-10-08', 6, 2, 'North', 'Partner', 7, 12600.00, 5900.00, 6700.00),
  (19, 'SO-2024-019', '2024-11-12', 1, 5, 'North', 'Direct', 6, 16800.00, 8700.00, 8100.00),
  (20, 'SO-2024-020', '2024-12-05', 2, 3, 'West', 'Online', 7, 10500.00, 4900.00, 5600.00);

