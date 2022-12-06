package com.finance_tracker.transaction;

import java.util.Date;

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


    /**
     * @param builder TransactionBuilder
     *
     * This method constructs a Transaction from its Builder.
     * Relies on the Builder Pattern.
     * See class: TransactionBuilder
     */
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
    /**
     * @return String
     *
     * Returns the Transaction name.
     */
    public String getName() {
        return name;
    }


    /**
     * @return TransactionEnum
     *
     * Returns the TransactionEnum (ONE_TIME, RECURRING).
     * See class: TransactionEnum.
     */
    public TransactionEnum getTransactionEnum() {
        return transactionEnum;
    }


    /**
     * @return double
     *
     * Returns the Transaction value.
     */
    public double getValue() {
        return value;
    }


    /**
     * @return Date
     *
     * Returns the Transaction Date.
     */
    public Date getDate() {
        return date;
    }


    /**
     * @return Account
     *
     * Returns the Account the Transaction deducts from or adds to.
     */
    public Account getTransactionAccount() {
        return transactionAccount;
    }


    /**
     * @return TransactionFrequencyEnum
     *
     * Returns the TransactionFrequencyEnum (WEEKLY, MONTHLY, ANNUALLY).
     * See class: TransactionFrequencyEnum.
     */
    public TransactionFrequencyEnum getFrequency() {
        return frequency;
    }


    /**
     * @return CategoryEnum
     *
     * Returns the CategoryEnum associated with the account (e.g. FOOD, BILLS).
     * See class: CategoryEnum.
     */
    public CategoryEnum getCategory() {
        return category;
    }
}
