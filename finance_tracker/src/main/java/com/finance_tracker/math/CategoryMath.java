package com.finance_tracker.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;

public class CategoryMath {
    Mapper databaseMapper = Mapper.getInstance();


    // Constructor
    public CategoryMath() {}


    public EnumMap<CategoryEnum, Double> calculateCategoryAmounts(Collection<Transaction> transactions) {
        // Generate an Enum map
        EnumMap<CategoryEnum, Double> categoryMap = new EnumMap<>(CategoryEnum.class);
        CategoryEnum[] categories = CategoryEnum.class.getEnumConstants();
        for (CategoryEnum c: categories) {
            ArrayList<Transaction> transactionsOfCategory = databaseMapper.getAllTransactionsOfCategory(c);
            Double categoryTotal = 0.0;
            for (Transaction t: transactionsOfCategory) {
                Double value = t.getValue();
                categoryTotal += value;
            }
            categoryMap.put(c, categoryTotal);
        }
        return categoryMap;
    }


    private double calculateTransactionTotalAmount(Collection<Transaction> transactions) {
        double totalTransactionAmount = 0;
        for (Transaction t: transactions) {
            double transactionValue = t.getValue();
            totalTransactionAmount += transactionValue;
        }
        return totalTransactionAmount;
    }

    // Unused, pie plot does this on its own
    // Could be useful for printing percent values
    public EnumMap<CategoryEnum, Double> calculateCategoryPercents(Collection<Transaction> transactions) {  
            double totalTransactionAmount = calculateTransactionTotalAmount(transactions); 
            EnumMap<CategoryEnum, Double> categoryMap = calculateCategoryAmounts(transactions);

            EnumMap<CategoryEnum, Double> categoryPercentMap = new EnumMap<>(CategoryEnum.class);
            for (CategoryEnum c: categoryMap.keySet()) {
                double categoryPercent = categoryMap.get(c) / totalTransactionAmount * 100;
                categoryPercentMap.put(c, categoryPercent);
            }
            return categoryPercentMap;
        }
}
