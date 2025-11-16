package com.sa001;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalesAnalyzerTest {

    @Test
    void testTotalRevenue() {
        System.out.println("\n=== Running testTotalRevenue ==="); 
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SaleRecord> mock = List.of(
                new SaleRecord("2024-01-01", "North", "A", 5, 10),
                new SaleRecord("2024-01-02", "North", "B", 2, 20)
        );

        double result = analyzer.totalRevenue(mock);
        System.out.println("Total revenue computed = " + result);
        assertEquals(5*10 + 2*20, result);
    }

    @Test
    void testCountByProduct() {
        System.out.println("\n=== Running testCountByProduct ===");
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SaleRecord> mock = List.of(
                new SaleRecord("2024-01-01", "North", "A", 1, 10),
                new SaleRecord("2024-01-02", "South", "A", 1, 10),
                new SaleRecord("2024-01-02", "East", "B", 1, 10)
        );

        var result = analyzer.countByProduct(mock);
        System.out.println("Product counts: " + result); 

        assertEquals(2, result.get("A"));
        assertEquals(1, result.get("B"));
    }

    @Test
    void testTopSale() {
        System.out.println("\n=== Running testTopSale ===");
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SaleRecord> mock = List.of(
            new SaleRecord("2024-01-01", "North", "A", 1, 10),
            new SaleRecord("2024-01-02", "South", "B", 5, 20)
        );

        var top = analyzer.topSale(mock).orElseThrow();
        System.out.println("Top sale result: " + top); 

        assertEquals("B", top.getProduct());
        assertEquals(5 * 20, top.getTotal());
    }

    @Test
    void testRevenueByRegion() {
        System.out.println("\n=== testRevenueByRegion ===");
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SaleRecord> mock = List.of(
            new SaleRecord("2024-01-01", "North", "A", 2, 10),
            new SaleRecord("2024-01-02", "North", "B", 3, 5),
            new SaleRecord("2024-01-03", "South", "C", 1, 20)
        );

        var result = analyzer.revenueByRegion(mock);
        System.out.println("Revenue by region: " + result);

        assertEquals(2*10 + 3*5, result.get("North"));
        assertEquals(1*20, result.get("South"));
    }


}
