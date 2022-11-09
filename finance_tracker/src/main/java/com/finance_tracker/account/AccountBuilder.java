package com.finance_tracker.dataTypes.account;

import java.util.ArrayList;

import com.finance_tracker.dataTypes.Builder;

public class AccountBuilder extends Builder {

    // Mandatory attributes are final
    // https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
    private final String name;
    private final ArrayList<AccountRecord> accountRecords;
    // Optional attributes
    private String tag;
    private double interestPercent;
    private InterestEnum interestEnum;

    enum InterestEnum {
        SIMPLE,
        COMPOUND,
        ACCRUED;
    }


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
     * @param tag String
     * @return AccountBuilder
     *
     * Optional attributes have their own methods.
     */
    public AccountBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }


    public AccountBuilder setInterestPercent(double interestPercent) {
        this.interestPercent = interestPercent;
        return this;
    }


    public AccountBuilder setInterestEnum(double interestEnum) {
        this.interestEnum = interestEnum;
        return this;
    }


    /**
     * Using the Builder pattern to create Accounts.
     */
    public Account buildAccount() {
        return new Account(this);
    }
}