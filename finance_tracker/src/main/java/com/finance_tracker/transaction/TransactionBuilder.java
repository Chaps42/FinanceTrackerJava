package com.finance_tracker.transaction;

import java.util.Date;

import com.finance_tracker.account.Account;

public class TransactionBuilder{

    // Mandatory attributes
    private String name;
    private TransactionEnum transactionEnum;
    private double value;
    private Date date;
    private Account transactionAccount;
    // Optional attributes
    private TransactionFrequencyEnum frequency;
    private CategoryEnum category;


    public TransactionBuilder(String name,
        TransactionEnum transactionEnum,
        double value,
        Date date,
        Account transactionAccount) {
            this.name = name;
            this.transactionEnum = transactionEnum;
            this.value = value;
            this.date = date;
            this.transactionAccount = transactionAccount;
        }

    public TransactionBuilder setFrequency(TransactionFrequencyEnum frequency) {
        this.frequency = frequency;
        return this;
    }


    public TransactionBuilder setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }


    /**
     * Using the Builder pattern to create Transactions.
     */
    public Transaction buildTransaction() {
        return new Transaction(this);
    }


    // All getters
    public String getName() {
        return name;
    }

    public TransactionEnum getTransactionEnum() {
        return transactionEnum;
    }

    public double getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public Account getTransactionAccount() {
        return transactionAccount;
    }

    public TransactionFrequencyEnum getFrequency() {
        return frequency;
    }

    public CategoryEnum getCategory() {
        return category;
    }
}
