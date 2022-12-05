package com.finance_tracker.math;

import java.util.Collection;
import java.util.EnumMap;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;

public class CategoryMath {


    public static EnumMap<CategoryEnum, Double> getCategoryAmounts(Collection<Transaction> transactions) {
        // Generate an Enum map
        EnumMap<CategoryEnum, Double> categoryMap = new EnumMap<>(CategoryEnum.class);
        CategoryEnum[] categories = CategoryEnum.class.getEnumConstants();
        for (CategoryEnum c: categories) {
            categoryMap.put(c, 0.0);
        }
        
        // Fill the Enum map with additive values every transaction
        for (Transaction t: transactions) {
            double transactionValue = t.getValue();

            CategoryEnum category = t.getCategory();
            double categoryValue = categoryMap.get(category) + transactionValue;
            categoryMap.replace(category, categoryValue);
        }
        return categoryMap;
    }


    private static double getTransactionTotalAmount(Collection<Transaction> transactions) {
        double totalTransactionAmount = 0;
        for (Transaction t: transactions) {
            double transactionValue = t.getValue();
            totalTransactionAmount += transactionValue;
        }
        return totalTransactionAmount;
    }

    public static EnumMap<CategoryEnum, Double> getCategoryPercents(Collection<Transaction> transactions) {  
            double totalTransactionAmount = getTransactionTotalAmount(transactions); 
            EnumMap<CategoryEnum, Double> categoryMap = getCategoryAmounts(transactions);

            EnumMap<CategoryEnum, Double> categoryPercentMap = new EnumMap<>(CategoryEnum.class);
            for (CategoryEnum c: categoryMap.keySet()) {
                double categoryPercent = categoryMap.get(c) / totalTransactionAmount * 100;
                categoryPercentMap.put(c, categoryPercent);
            }
            return categoryPercentMap;
        }
}
// change this to calculate "last month only" option?
