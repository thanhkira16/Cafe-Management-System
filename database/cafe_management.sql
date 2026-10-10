-- Fresh MySQL schema and demo data for Cafe Management System.
-- Import with: mysql -u root -p < database/cafe_management.sql
CREATE DATABASE IF NOT EXISTS cms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cms;

CREATE TABLE IF NOT EXISTS `user` (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  email VARCHAR(200) NOT NULL UNIQUE,
  mobileNumber VARCHAR(20),
  address VARCHAR(200),
  password VARCHAR(200) NOT NULL,
  securityQuestion VARCHAR(200),
  answer VARCHAR(200),
  status VARCHAR(20) NOT NULL DEFAULT 'false'
);

CREATE TABLE IF NOT EXISTS category (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS product (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  category VARCHAR(200) NOT NULL,
  price VARCHAR(200) NOT NULL,
  INDEX idx_product_category (category)
);

CREATE TABLE IF NOT EXISTS bill (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200),
  email VARCHAR(200),
  mobileNumber VARCHAR(20),
  date VARCHAR(50),
  total VARCHAR(200),
  createdBy VARCHAR(200)
);

-- Demo login: admin@cafe.local / admin123 (password stored as MD5 as required by the legacy app).
INSERT INTO `user` (name, email, mobileNumber, address, password, securityQuestion, answer, status)
VALUES ('Cafe Admin', 'admin@cafe.local', '0900000000', 'Ho Chi Minh City', '0192023a7bbd73250516f069df18b500', 'What is your favorite drink?', 'coffee', 'true')
ON DUPLICATE KEY UPDATE name=VALUES(name), status=VALUES(status);

INSERT IGNORE INTO category (name) VALUES
('Coffee'), ('Tea'), ('Freeze'), ('Milk'), ('Other');

INSERT INTO product (name, category, price)
SELECT sample.name, sample.category, sample.price
FROM (
  SELECT 'Espresso' AS name, 'Coffee' AS category, '30000' AS price UNION ALL
  SELECT 'Americano', 'Coffee', '35000' UNION ALL
  SELECT 'Cappuccino', 'Coffee', '42000' UNION ALL
  SELECT 'Latte', 'Coffee', '45000' UNION ALL
  SELECT 'Vietnamese Iced Coffee', 'Coffee', '35000' UNION ALL
  SELECT 'Peach Tea', 'Tea', '40000' UNION ALL
  SELECT 'Lemon Tea', 'Tea', '30000' UNION ALL
  SELECT 'Matcha Latte', 'Tea', '45000' UNION ALL
  SELECT 'Chocolate Freeze', 'Freeze', '50000' UNION ALL
  SELECT 'Cookies & Cream Freeze', 'Freeze', '55000' UNION ALL
  SELECT 'Caramel Macchiato', 'Coffee', '52000' UNION ALL
  SELECT 'Mocha', 'Coffee', '48000' UNION ALL
  SELECT 'Salted Cream Coffee', 'Coffee', '49000' UNION ALL
  SELECT 'Coconut Coffee', 'Coffee', '50000' UNION ALL
  SELECT 'Jasmine Green Tea', 'Tea', '35000' UNION ALL
  SELECT 'Lychee Tea', 'Tea', '42000' UNION ALL
  SELECT 'Passion Fruit Tea', 'Tea', '42000' UNION ALL
  SELECT 'Strawberry Yogurt Freeze', 'Freeze', '52000' UNION ALL
  SELECT 'Mango Smoothie', 'Freeze', '50000' UNION ALL
  SELECT 'Brown Sugar Milk Tea', 'Milk', '45000' UNION ALL
  SELECT 'Fresh Orange Juice', 'Other', '42000' UNION ALL
  SELECT 'Pineapple Juice', 'Other', '40000' UNION ALL
  SELECT 'Mineral Water', 'Other', '15000' UNION ALL
  SELECT 'Fresh Milk', 'Milk', '30000' UNION ALL
  SELECT 'Orange Juice', 'Other', '40000'
) AS sample
WHERE NOT EXISTS (SELECT 1 FROM product existing WHERE existing.name = sample.name AND existing.category = sample.category);

-- Example completed orders; the legacy bill table stores invoice totals, not individual line items.
INSERT INTO bill (name, email, mobileNumber, date, total, createdBy)
SELECT sample.name, sample.email, sample.mobileNumber, sample.date, sample.total, sample.createdBy
FROM (
  SELECT 'Nguyen Minh Anh' AS name, 'minhanh@example.com' AS email, '0901000001' AS mobileNumber, '05-10-2026' AS date, '125000' AS total, 'admin@cafe.local' AS createdBy UNION ALL
  SELECT 'Tran Gia Bao', 'giabao@example.com', '0901000002', '06-10-2026', '87000', 'admin@cafe.local' UNION ALL
  SELECT 'Le Thao Vy', 'thaovy@example.com', '0901000003', '07-10-2026', '142000', 'admin@cafe.local' UNION ALL
  SELECT 'Pham Quoc Huy', 'quochuy@example.com', '0901000004', '08-10-2026', '96000', 'admin@cafe.local' UNION ALL
  SELECT 'Vo Ngoc Han', 'ngochan@example.com', '0901000005', '09-10-2026', '184000', 'admin@cafe.local' UNION ALL
  SELECT 'Dang Thanh Tung', 'thanhtung@example.com', '0901000006', '10-10-2026', '118000', 'admin@cafe.local'
) AS sample
WHERE NOT EXISTS (
  SELECT 1 FROM bill existing
  WHERE existing.email = sample.email AND existing.date = sample.date
    AND existing.total = sample.total AND existing.createdBy = sample.createdBy
);
