package com.finance_tracker.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;


public class CategoryMath {
    Mapper databaseMapper = Mapper.getInstance();


    /**
     * Constructor for the CategoryMath utility class.
     */
    public CategoryMath() {}


    /**
     * @param transactions Collection<Transaction>
     * @return EnumMap<CategoryEnum, Double>
     *
     * This method calculates the total transactions within each Category.
     */
    public EnumMap<CategoryEnum, Double> calculateCategoryAmounts(
        Collection<Transaction> transactions) {
            EnumMap<CategoryEnum, Double> categoryMap =
                new EnumMap<>(CategoryEnum.class);
            CategoryEnum[] categories = CategoryEnum.class.getEnumConstants();
            for (CategoryEnum c: categories) {
                ArrayList<Transaction> transactionsOfCategory =
                    databaseMapper.getAllTransactionsOfCategory(c);
                Double categoryTotal = 0.0;
                for (Transaction t: transactionsOfCategory) {
                    Double value = t.getValue();
                    categoryTotal += value;
                }
                categoryMap.put(c, categoryTotal);
            }
            return categoryMap;
    }


    /**
     * @param transactions Collection<Transaction>
     * @return double
     *
     * Calculates the total transaction spending.
     */
    private double calculateTransactionTotalAmount(
        Collection<Transaction> transactions) {
            double totalTransactionAmount = 0;
            for (Transaction t: transactions) {
                double transactionValue = t.getValue();
                totalTransactionAmount += transactionValue;
            }
            return totalTransactionAmount;
    }


    // Unused, pie plot does this on its own
    // Could be useful for printing percent values
    /**
     * @param transactions Collection<Transaction> 
     * @return EnumMap<CategoryEnum, Double>
     *
     * Calculates the percentage of transaction spending attributed to each
     * category.
     */
    public EnumMap<CategoryEnum, Double> calculateCategoryPercents(
        Collection<Transaction> transactions) {  
            double totalTransactionAmount =
                calculateTransactionTotalAmount(transactions); 
            EnumMap<CategoryEnum, Double> categoryMap =
                calculateCategoryAmounts(transactions);

            EnumMap<CategoryEnum, Double> categoryPercentMap =
                new EnumMap<>(CategoryEnum.class);
            for (CategoryEnum c: categoryMap.keySet()) {
                double categoryPercent =
                    categoryMap.get(c) / totalTransactionAmount * 100;
                categoryPercentMap.put(c, categoryPercent);
            }
            return categoryPercentMap;
        }
}
