package com.finance_tracker.plot;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.math.CategoryMath;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


public class PiePlotter {


    /**
     * Constructor for the PiePlotter class.
     */
    public PiePlotter() {}


    /**
     * @return JFreeChart
     *
     * This method returns a pie chart of Transaction Category spending.
     *
     * Help from:
     * https://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm
     * @throws IOException
     */
    public JFreeChart plotTransactions() throws IOException {

        Mapper databaseMapper = Mapper.getInstance();
        HashMap<String, Transaction> transactions =
            databaseMapper.getTransactions();
        CategoryMath categoryMath = new CategoryMath();
        EnumMap<CategoryEnum, Double> categoryMap =
            categoryMath.calculateCategoryAmounts(transactions.values());

        DefaultPieDataset dataset = new DefaultPieDataset();

        // Add data from category HashMap
        for (CategoryEnum c: categoryMap.keySet()) {
            String name = String.valueOf(c);
            Double amount = categoryMap.get(c);

            dataset.setValue(name, amount);
        }

        // Example of using a factory pattern
        // But a builder would be better here
        // Because it isn't clear what each argument maps to
        JFreeChart chart = ChartFactory.createPieChart(
            "Transaction Categories",
            dataset,
            true,
            true,
            false);

        
        File pieChart = new File( "PieChart.jpeg" ); 
        ChartUtilities.saveChartAsJPEG( pieChart, chart, 640, 480);
        return chart;
    }
}
