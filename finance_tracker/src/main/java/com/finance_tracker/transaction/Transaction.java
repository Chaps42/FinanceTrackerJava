package com.finance_tracker.transaction;

import java.util.ArrayList;

import com.finance_tracker.Date;
import com.finance_tracker.account.Account;

public class Transaction {

    // Mandatory attributes
    private String transactionName;
    private TransactionEnum transactionEnum;
    private double transactionValue;
    private Date transactionDate;
    private Account transactionAccount;
    // Optional attributes
    private int transactionFrequency;
    private ArrayList<Date> transactionDates;

    public Transaction(TransactionBuilder builder) {
        this.transactionName = builder.getTransactionName();
        this.transactionEnum = builder.getTransactionEnum();
        this.transactionValue = builder.getTransactionValue();
        this.transactionDate = builder.getTransactionDate();
        this.transactionAccount = builder.getTransactionAccount();
        this.transactionFrequency = builder.getTransactionFrequency();
        this.transactionDates = builder.getTransactionDates();
    }

    // All getters
    public String getTransactionName() {
        return transactionName;
    }

    public TransactionEnum getTransactionEnum() {
        return transactionEnum;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public Account getTransactionAccount() {
        return transactionAccount;
    }

    public int getTransactionFrequency() {
        return transactionFrequency;
    }

    public ArrayList<Date> getTransactionDates() {
        return transactionDates;
    }
}
