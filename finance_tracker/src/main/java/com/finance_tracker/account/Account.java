package com.finance_tracker.account;

import java.util.ArrayList;

import java.util.Date;

public class Account {

    // All attributes final in builder pattern
    private String name;
    private ArrayList<AccountRecord> accountRecords;
    private AccountEnum accountEnum;
    private double interestRate;
    private InterestEnum interestEnum;
    private InterestPeriodEnum interestPeriodEnum;
    private Date lastInterestDate;

    public Account(AccountBuilder builder) {
        this.name = builder.getName();
        this.accountRecords = builder.getAccountRecords();
        this.accountEnum = builder.getAccountEnum();
        this.interestRate = builder.getInterestRate();
        this.interestEnum = builder.getInterestEnum();
        this.interestPeriodEnum = builder.getInterestPeriodEnum();
        this.lastInterestDate = builder.getLastInterestDate();
    }


    /**
     * @param record AccountRecord
     * For adding a new record.
     */
    public void addRecord(AccountRecord record) {
        accountRecords.add(record);
    }


    /**
     * @return ArrayList<Date>
     *
     * For getting all dates that there was a change to the account?
     */
    ArrayList<Date> getUniqueDates() {
        ArrayList<Date> dates = new ArrayList<Date>();
        for (AccountRecord record: accountRecords) {
            dates.add(record.getDate());
        }
        return dates;
    }


    /**
     * @param record AccountRecord
     *
     * For deleting an account
     */
    public void removeRecord(AccountRecord record) {
        accountRecords.remove(record);
    }

    public double getValue() {
        Integer endIndex = accountRecords.size();
        AccountRecord lastRecord = accountRecords.get(endIndex);
        return lastRecord.getAmount();
    }



    // All getters and no setters in Builder pattern
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

    public AccountRecord getRecord(Date D){
        for(AccountRecord R:accountRecords){
            if(R.getDate()==D){
                return R;
            }
        }
        return accountRecords.get(0);
    }
}
