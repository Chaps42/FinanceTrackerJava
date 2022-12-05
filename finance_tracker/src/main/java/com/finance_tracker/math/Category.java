package com.finance_tracker.math;

import java.util.Collection;
import java.util.EnumMap;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;

public class Category {


    private EnumMap<CategoryEnum, Double> getCategoryAmounts(Collection<Transaction> transactions) {
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

    private double getTransactionTotal(Collection<Transaction> transactions) {
        double totalTransactionAmount = 0;
        for (Transaction t: transactions) {
            double transactionValue = t.getValue();
            totalTransactionAmount += transactionValue;
        }
        return totalTransactionAmount;
    }

    private EnumMap<CategoryEnum, Double> getCategoryPercents(
        double totalTransactionAmount,
        EnumMap<CategoryEnum, Double>  categoryMap) {   
            EnumMap<CategoryEnum, Double> categoryPercentMap = new EnumMap<>(CategoryEnum.class);
            for (CategoryEnum c: categoryMap.keySet()) {
                double categoryPercent = categoryMap.get(c) / totalTransactionAmount * 100;
                categoryPercentMap.put(c, categoryPercent);
            }
            return categoryPercentMap;
        }
    
    public void calcCategories() {
        Mapper databaseMapper = Mapper.getInstance();
        Collection<Transaction>  transactions = databaseMapper.getTransactions().values();
        EnumMap<CategoryEnum, Double> categoryMap = getCategoryAmounts(transactions);

        double transactionTotal = getTransactionTotal(transactions);
        EnumMap<CategoryEnum, Double> categoryPercentMap = getCategoryPercents(transactionTotal, categoryMap);

        // pass info to a Plotter
    }
}

// need to make a category object that has categoryEnum, amount, and Percentage attributes
//with getters for the Pie plotter to grab (plotter should be agnostic - not depend on categories but work when something else passes the categories into it)
// change this to calculate "last month only" option?
