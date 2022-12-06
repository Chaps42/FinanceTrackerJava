package com.finance_tracker.account;

import java.util.ArrayList;
import java.util.Date;


// The Builder pattern was a good candidate for our Accounts because
// It allows the user to input a plethora of arguments with clarity.
// There is no longer a long stream of keyword arguments without anything
// Meaningful to communicate to the user what is input first, second, etc.
public class AccountBuilder {

    // Mandatory attributes
    private String name;
    private ArrayList<AccountRecord> accountRecords;
    // Optional attributes
    private AccountEnum accountEnum;
    private double interestRate;
    private InterestEnum interestEnum;
    private InterestPeriodEnum interestPeriodEnum;
    private Date lastInterestDate;


    /**
     * @param name String
     * @param accountRecords ArrayList<AccountRecord>
     *
     * Constructor for AccountBuilder has all mandatory attributes:
     * - name and 
     * - records
     */
    public AccountBuilder(String name,
        ArrayList<AccountRecord> accountRecords) {
            this.name = name;
            this.accountRecords = accountRecords;
        }


    /**
     * @param accountEnum AccountEnum
     * @return AccountBuilder
     *
     * Optional attribute to add an AccountEnum (i.e. CHECKING) to the Account.
     * See class: AccountEnum.
     */
    public AccountBuilder setAccountEnum(AccountEnum accountEnum) {
        this.accountEnum = accountEnum;
        return this;
    }


    /**
     * @param interestRate double
     * @return AccountBuilder
     *
     * Optional attribute to add an interest rate to the Account.
     */
    public AccountBuilder setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }


    /**
     * @param interstEnum InterstEnum
     * @return AccountBuilder
     *
     * Optional attribute to add an InterstEnum (i.e. SIMPLE) to the Account.
     * See class: InterestEnum.
     */
    public AccountBuilder setInterestEnum(InterestEnum interestEnum) {
        this.interestEnum = interestEnum;
        return this;
    }


    /**
     * @param interestPeriodEnum InterestPeriodEnum
     * @return AccountBuilder
     *
     * Optional attribute to add an InterestPeriodEnum (i.e. DAILY)
     * to the Account.
     * See class: InterestPeriodEnum.
     */
    public AccountBuilder setInterestPeriodEnum(
        InterestPeriodEnum interestPeriodEnum) {
            this.interestPeriodEnum = interestPeriodEnum;
            return this;
    }


    /**
     * @param lastInterestDate) Date
     * @return AccountBuilder
     *
     * Optional attribute to add the date interest was last collected
     * to the Account.
     */
    public AccountBuilder setLastInterestDate(Date lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
        return this;
    }

    /**
     * Using the Builder pattern to create Accounts.
     */
    public Account buildAccount() {
        return new Account(this);
    }


    // All Getters
    /**
     * @return String
     *
     * Returns the target Account name.
     */
    public String getName() {
        return name;
    }


    /**
     * @return ArrayList<AccountRecord>
     *
     * Returns the target AccountRecords.
     * See class: AccountRecord.
     */
    public ArrayList<AccountRecord> getAccountRecords() {
        return accountRecords;
    }


    /**
     * @return AccountEnum
     *
     * Returns the target AccountEnum (CHECKING, SAVING, etc).
     * See class: AccountEnum.
     */
    public AccountEnum getAccountEnum() {
        return accountEnum;
    }


    /**
     * @return double
     *
     * Returns the target Account interest rate.
     */
    public double getInterestRate() {
        return interestRate;
    }


    /**
     * @return InterstEnum
     *
     * Returns the target Account InterestEnum (SIMPLE, COMPOUND, CONTINUOUS).
     * See class: InterestEnum
     */
    public InterestEnum getInterestEnum() {
        return interestEnum;
    }


    /**
     * @return InterestPeriodEnum
     *
     * Returns the target Account InterestPeriodEnum (i.e. DAILY).
     * See class: InterstPeriodEnum.
     */
    public InterestPeriodEnum getInterestPeriodEnum() {
        return interestPeriodEnum;
    }


    /**
     * @return Date
     *
     * Returns the Account's last interest collection Date.
     */
    public Date getLastInterestDate() {
        return lastInterestDate;
    }
}