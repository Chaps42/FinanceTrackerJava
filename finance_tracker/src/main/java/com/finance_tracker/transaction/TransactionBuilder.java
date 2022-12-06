package com.finance_tracker.transaction;

import java.util.Date;

import com.finance_tracker.account.Account;


// The Buidler pattern was a good candidate for our Transactions because
// It allows the user to input a plethora of arguments with clarity.
// There is no longer a long stream of keyword arguments without anything
// Meaningful to communicate to the user what is input first, second, etc.
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


    /**
     * @param name String
     * @param transactionEnum TransactionEnum
     * @param value double
     * @param date Date
     * @param transactionAccount Account
     *
     * Constructor for the Transaction Builder with all mandatory attributes:
     * - name
     * - enum (ONE_TIME or RECURRING)
     * - value
     * - date
     * - Account affected
     */
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


    /**
     * @param frequency TransactionFrequencyEnum
     * @return Transaction Builder
     *
     * Optional method to add a recurrance frequency to the Transaction.
     * See class: TransactionFrequencyEnum.
     */
    public TransactionBuilder setFrequency(
        TransactionFrequencyEnum frequency) {
            this.frequency = frequency;
            return this;
    }


    /**
     * @param category CategoryEnum
     * @return Transaction Builder
     *
     * Optional method to add a category to the Transaction.
     * See class: CategoryEnum.
     */
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
    /**
     * @return String
     *
     * Returns the target Transaction name.
     */
    public String getName() {
        return name;
    }


    /**
     * @return TransactionEnum
     *
     * Returns the target Transaction Enum (ONE_TIME or RECURRING).
     * See class: TransactionEnum.
     */
    public TransactionEnum getTransactionEnum() {
        return transactionEnum;
    }


    /**
     * @return double
     *
     * Returns the target Transaction value.
     */
    public double getValue() {
        return value;
    }


    /**
     * @return Date
     *
     * Returns the target Transaction Date.
     */
    public Date getDate() {
        return date;
    }


    /**
     * @return Account
     *
     * Returns the target Transaction Account.
     */
    public Account getTransactionAccount() {
        return transactionAccount;
    }


    /**
     * @return TransactionFrequencyEnum
     *
     * Returns the Transaction frequency.
     * See class: TransactionFrequencyEnum.
     */
    public TransactionFrequencyEnum getFrequency() {
        return frequency;
    }


    /**
     * @return CategoryEnum
     *
     * Returns the Transaction category.
     * See class: CategoryEnum.
     */
    public CategoryEnum getCategory() {
        return category;
    }
}
