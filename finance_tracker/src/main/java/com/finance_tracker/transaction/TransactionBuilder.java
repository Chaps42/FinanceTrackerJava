package com.finance_tracker.transaction;

import java.util.ArrayList;
import java.util.Date;

import com.finance_tracker.account.Account;

public class TransactionBuilder{

    // Mandatory attributes
    private String transactionName;
    private TransactionEnum transactionEnum;
    private double transactionValue;
    private Date transactionDate;
    private Account transactionAccount;
    // Optional attributes
    private int transactionFrequency;
    private ArrayList<Date> transactionDates;


    public TransactionBuilder(String transactionName,
        TransactionEnum transactionEnum,
        double transactionValue,
        Date transactionDate,
        Account transactionAccount) {
            this.transactionName = transactionName;
            this.transactionEnum = transactionEnum;
            this.transactionValue = transactionValue;
            this.transactionDate = transactionDate;
            this.transactionAccount = transactionAccount;
        }

    public TransactionBuilder setTransactionFrequency(int transactionFrequency) {
        this.transactionFrequency = transactionFrequency;
        return this;
    }

    public TransactionBuilder setTransactionDates(ArrayList<Date> transactionDates) {
        this.transactionDates = transactionDates;
        return this;
    }

    /**
     * Using the Builder pattern to create Transactions.
     */
    public Transaction buildTransaction() {
        return new Transaction(this);
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
