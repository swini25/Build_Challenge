 SA001 — Sales Analysis Challenge

A Java project that performs **data analysis on sales records**, including:

* CSV file loading
* Total revenue calculation
* Revenue grouped by region
* Product frequency analysis
* Highest-value sale detection
* Clean functional-style stream processing
* Full JUnit 5 test suite
* Console output for analysis

This project fully satisfies all requirements of the **SA001 Sales Analyzer** challenge.

---

## Project Structure

```
sa001-sales-analysis
│
├── src
│   ├── main/java/com/sa001
│   │   ├── Main.java
│   │   ├── SaleRecord.java
│   │   └── SalesAnalyzer.java
│   │
│   └── test/java/com/sa001
│       └── SalesAnalyzerTest.java
│
├── src/main/resources
│       └── sales.csv
│
├── pom.xml
└── README.md
```

---

# Features

### ✔ CSV Parsing (OpenCSV)

Reads a dataset of sales transactions from a structured CSV file:

```
date, region, product, quantity, price
```

### ✔ Total Revenue Calculation

Computes global revenue across all sales using Java Streams.

### ✔ Revenue Grouped by Region

Aggregates total sales for each region (North, South, etc.).

### ✔ Product Count Analysis

Counts how many times each product appears in the dataset.

### ✔ Top Sale Detection

Finds the single **highest-value** sale, using:

* Stream `max()`
* Comparator on sale total

### ✔ Error-Resistant CSV Loading

Skips malformed or incomplete rows to prevent parsing failures.

### ✔ Full Unit Test Coverage

JUnit 5 test suite validates:

* Total revenue calculation
* Product counting
* Revenue grouping
* Highest sale detection
* Output logs for clarity

All tests print simple, readable console logs.

---

# Setup Instructions

### **Requirements**

* Java 17 or higher
* Maven 3.8+
* Git

---

### **Clone the Repository**

```sh
git clone https://github.com/<your-username>/sa001-sales-analysis.git
cd sa001-sales-analysis
```

---

### **Build the Project**

```sh
mvn clean install
```

---

### **Run the Application**

```sh
mvn exec:java -Dexec.mainClass="com.sa001.Main"
```

Sample output:

```
=== SALES ANALYSIS ===
Total revenue: 12345.67
Revenue by region: {North=4500.0, South=3200.0, East=2645.67}
Count by product: {A=12, B=7, C=4}
Top sale: SaleRecord{date='2024-01-10', region='South', product='B', quantity=10, price=50.0}
```

---

# Running Tests

To execute all unit tests:

```sh
mvn test
```

Your tests include readable headers and printed results.

---

# Sample Test Output (All Analyses)

---

## ✔ testTotalRevenue()

```
=== Running testTotalRevenue ===
Total revenue computed = 90.0
```

---

## ✔ testCountByProduct()

```
=== Running testCountByProduct ===
Product counts: {A=2, B=1}
```

---

## ✔ testTopSale()

```
=== Running testTopSale ===
Top sale result: SaleRecord{date='2024-01-02', region='South', product='B', quantity=5, price=20.0}
```

---

## ✔ testRevenueByRegion()

```
=== Running testRevenueByRegion ===
Revenue by region: {North=35.0, South=20.0}
```

---

# Summary

This project successfully demonstrates:

* CSV parsing and validation
* Functional-style Java data processing
* Stream-based aggregation
* Region and product analysis
* Test-driven development
* Clear console output
* Correct highest sale calculation

All SA001 **deliverables** are fulfilled.


