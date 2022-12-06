package com.finance_tracker.account;

import java.util.ArrayList;
import java.util.Date;

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
     * Constructor has all mandatory attributes
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
     * Optional attributes have their own methods.
     */
    public AccountBuilder setAccountEnum(AccountEnum accountEnum) {
        this.accountEnum = accountEnum;
        return this;
    }


    public AccountBuilder setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }


    public AccountBuilder setInterestEnum(InterestEnum interestEnum) {
        this.interestEnum = interestEnum;
        return this;
    }

    public AccountBuilder setInterestPeriodEnum(InterestPeriodEnum interestPeriodEnum) {
        this.interestPeriodEnum = interestPeriodEnum;
        return this;
    }

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


    // All getters
    public String getName() {
        return name;
    }

    public ArrayList<AccountRecord> getAccountRecords() {
        return accountRecords;
    }

    public AccountEnum getAccountEnum() {
        return accountEnum;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public InterestEnum getInterestEnum() {
        return interestEnum;
    }

    public InterestPeriodEnum getInterestPeriodEnum() {
        return interestPeriodEnum;
    }

    public Date getLastInterestDate() {
        return lastInterestDate;
    }
}