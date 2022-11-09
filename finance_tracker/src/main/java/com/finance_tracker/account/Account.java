package com.finance_tracker.dataTypes.account;

import java.util.ArrayList;

import com.finance_tracker.dataTypes.Date;
//import AccountRecord;

public class Account {

    // All attributes final in builder pattern
    private final String name;
    private final ArrayList<AccountRecord> accountRecords;
    private final String tag;
    private final double interestPercent;
    private final InterestEnum interestEnum; // Simple, Compound, Accrued, Fixed Flat, Declining Balance, Declining Balance (Equal Installments)

    enum InterestEnum {
        SIMPLE,
        COMPOUND,
        ACCRUED;
    }

    public Account(AccountBuilder builder) {
        this.name = builder.name;
        this.accountRecords = builder.accountRecords;
        this.tag = builder.tag;
        this.interestPercent = builder.interestPercent;
        this.interestEnum = builder.interestEnum;
    }


    /**
     * @param record AccountRecord
     * For adding a new record.
     */
    void addRecord(AccountRecord record) {
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
    void removeRecord(AccountRecord record) {
        accountRecords.remove(record);
    }


    // All getters and no setters in Builder pattern

    public String getName() {
        return name;
    }


    public ArrayList<AccountRecord> getAccountRecords() {
        return accountRecords;
    }


    public String getTag() {
        return tag;
    }


    public double getInterestPercent() {
        return interestPercent;
    }


    public Enum getInterestEnum() {
        return interestEnum;
    }
}
