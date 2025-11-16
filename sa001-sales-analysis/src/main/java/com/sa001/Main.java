package com.sa001;

/**
 * Entry point for the Sales Analysis application.
 * Loads sales data from a CSV file and performs several analytical operations
 * such as total revenue, revenue by region, product counts, and top sale.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // Create analyzer instance
        SalesAnalyzer analyzer = new SalesAnalyzer();

        // Load sales records from CSV file
        var sales = analyzer.loadSales("src/main/resources/sales.csv");

        System.out.println("=== SALES ANALYSIS ===");
        System.out.println("Total revenue: " + analyzer.totalRevenue(sales));
        System.out.println("Revenue by region: " + analyzer.revenueByRegion(sales));
        System.out.println("Count by product: " + analyzer.countByProduct(sales));
        System.out.println("Top sale: " + analyzer.topSale(sales).orElse(null));
    }
}
