package com.sa001;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Performs various analysis operations on a list of SaleRecord objects.
 * Includes CSV loading, total revenue calculation, grouping, aggregation,
 * and identifying peak sales.
 */
public class SalesAnalyzer {

    /**
     * Loads sales from a CSV file.
     * CSV format expected:
     * date, region, product, quantity, price
     *
     * @param filePath path to CSV file
     * @return list of SaleRecord objects
     * @throws Exception when file cannot be read or data cannot be parsed
     */
    public List<SaleRecord> loadSales(String filePath) throws Exception {
        List<SaleRecord> records = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {
                // Skip malformed rows
                if (line.length < 5) continue;
                
                records.add(new SaleRecord(
                        line[0],
                        line[1],
                        line[2],
                        Integer.parseInt(line[3]),
                        Double.parseDouble(line[4])
                ));
            }
        }

        return records;
    }

    /**
     * Calculates total revenue across all sales.
     *
     * @param sales list of sale records
     * @return sum of revenue
     */
    public double totalRevenue(List<SaleRecord> sales) {
        return sales.stream()
                .mapToDouble(SaleRecord::getTotal)
                .sum();
    }

    /**
     * Groups revenue by region.
     *
     * @param sales sale records
     * @return map of region -> total revenue
     */
    public Map<String, Double> revenueByRegion(List<SaleRecord> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        SaleRecord::getRegion,
                        Collectors.summingDouble(SaleRecord::getTotal)
                ));
    }

    /**
     * Counts how many times each product appears in the dataset.
     *
     * @param sales sale records
     * @return map of product -> count
     */
    public Map<String, Long> countByProduct(List<SaleRecord> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        SaleRecord::getProduct,
                        Collectors.counting()
                ));
    }

    /**
     * Finds the single highest-value sale in the list.
     *
     * @param sales sale records
     * @return optional containing the top sale or empty if none
     */
    public Optional<SaleRecord> topSale(List<SaleRecord> sales) {
        return sales.stream()
                .max(Comparator.comparingDouble(SaleRecord::getTotal));
    }
}
