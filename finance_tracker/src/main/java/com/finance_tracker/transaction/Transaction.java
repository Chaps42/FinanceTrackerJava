package com.finance_tracker.transaction;

import java.util.Date;
import java.util.ArrayList;

import com.finance_tracker.account.Account;

public class Transaction {

    // Mandatory attributes
    private String name;
    private TransactionEnum transactionEnum;
    private double value;
    private Date date;
    private Account transactionAccount;
    // Optional attributes
    private TransactionFrequencyEnum frequency;
    private CategoryEnum category;

    public Transaction(TransactionBuilder builder) {
        this.name = builder.getName();
        this.transactionEnum = builder.getTransactionEnum();
        this.value = builder.getValue();
        this.date = builder.getDate();
        this.transactionAccount = builder.getTransactionAccount();
        this.frequency = builder.getFrequency();
        this.category = builder.getCategory();
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
