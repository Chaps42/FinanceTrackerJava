package com.finance_tracker.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.TreeMap;


// This Class holds information for different Accounts.
public class Account {

    private String name;
    private ArrayList<AccountRecord> accountRecords;
    private AccountEnum accountEnum;
    private double interestRate;
    private InterestEnum interestEnum;
    private InterestPeriodEnum interestPeriodEnum;
    private Date lastInterestDate;


    /**
     * @param builder AccountBuilder
     *
     * Constructor for Accounts.
     * Relies on Builder Pattern.
     * See class: AccountBuilder.
     */
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
     * @param date Date
     *
     * Resets the date interest was last calculated.
     */
    public void setLastInterestDate(Date date) {
        lastInterestDate = date;
    }


    /**
     * @param record AccountRecord
     *
     * Adds a new AccountRecord to the Account.
     * See class: AccountRecord.
     */
    public void addRecord(AccountRecord record) {
        accountRecords.add(record);
    }


    /**
     * @return ArrayList<Date>
     *
     * Returns all dates that there was a change to the account.
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
     * Deletes an AccountRecord from the Account.
     */
    public void removeRecord(AccountRecord record) {
        accountRecords.remove(record);
    }


    public TreeMap<Date, Double> sortAccountRecords() {
        // Use a TreeMap to sort Records by Date
        TreeMap<Date, Double> sortedMap = new TreeMap<Date, Double>();
        for (AccountRecord r: accountRecords) {
            Date date = r.getDate();
            Double value = r.getAmount();
            sortedMap.put(date, value);
        }
        return sortedMap;
    }


    /**
     * @return double
     *
     * Returns the most recent Account value.
     */
    public double getValue() {
        TreeMap<Date, Double> sortedMap = sortAccountRecords();
        return sortedMap.lastEntry().getValue();
    }


    /**
     * @return Date
     *
     * Returns the most recent Account date.
     */
    public Date getDate() {
        TreeMap<Date, Double> sortedMap = sortAccountRecords();
        return sortedMap.lastKey();
    }


    /**
     * @return Date
     *
     * Gets the first Account Date
     */
    public Date getPrincipleDate() {
        TreeMap<Date, Double> sortedMap = sortAccountRecords();
        return sortedMap.firstKey();
    }


    /**
     * @param date Date
     * @return AccountRecord
     *
     * Returns the AccountRecord for a specified Date.
     */
    public AccountRecord getRecord(Date date) {
        for (AccountRecord r: accountRecords) {
            if (r.getDate() == date) {
                return r;
            }
        }
        return accountRecords.get(0);
    }


    // All straightforward Getters:
    /**
     * @return String
     *
     * Returns the Account name.
     */
    public String getName() {
        return name;
    }


    /**
     * @return ArrayList<AccountRecord>
     *
     * Returns the Account's ArrayList of AccountRecords.
     */
    public ArrayList<AccountRecord> getAccountRecords() {
        return accountRecords;
    }


    /**
     * @return AccountEnum
     *
     * Returns the Account's AccountEnum (SAVINGS, CHECKING, etc).
     * See class: AccountEnum.
     */
    public AccountEnum getAccountEnum() {
        return accountEnum;
    }


    /**
     * @return double
     *
     * Retuns the Account's interest rate.
     */
    public double getInterestRate() {
        return interestRate;
    }


    /**
     * @return InterstEnum
     *
     * Returns the Account's InterestEnum (Simple, Compound, etc).
     * See class: InterestEnum.
     */
    public InterestEnum getInterestEnum() {
        return interestEnum;
    }


    /**
     * @return InterestPeriodEnum
     *
     * Returns the Account's InterestPeriodEnum (DAILY, MONTHLY, ANNUALLY).
     * See class: InterestPeriodEnum.
     */
    public InterestPeriodEnum getInterestPeriodEnum() {
        return interestPeriodEnum;
    }


    /**
     * @return Date
     *
     * Returns the Date interst was last calculated for the Account.
     */
    public Date getLastInterestDate() {
        return lastInterestDate;
    }


    /**
     * @param ThisDate Date
     * @return Date
     *
     * Returns the Date of an AccountRecord most recently before an input Date.
     */
    public Date getMostRecentDate(Date ThisDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        ArrayList<Date> UniqueDates = this.getUniqueDates();
        Date MostRecent = new Date();
        try{
            MostRecent = formatter.parse("02-Jan-1325");
            }
        catch(ParseException E){}
        for(Date D: UniqueDates){
            if(D.before(ThisDate) && D.after(MostRecent) ){
                MostRecent = D;
            }
            else{}

        }
        return MostRecent;
    }

}
