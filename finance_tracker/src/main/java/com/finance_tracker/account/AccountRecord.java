package com.finance_tracker.account;

import java.util.Date;


// We created the class AccountRecord to map Account Dates to values.
public class AccountRecord {
    private Date date;
    private Double amount;


    /**
     * @param date Date
     * @param amount double
     *
     * Constructor for an AccountRecord with a Date and value.
     */
    public AccountRecord(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }


    // Setters
    /**
     * @param date Date
     *
     * Sets the Date for the AccountRecord.
     */
    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * @param amount Double
     *
     * Sets the AccountRecord value.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }


    // Getters
    /**
     * @return Date
     *
     * Returns the AccountRecord's Date.
     */
    public Date getDate() {
        return date;
    }


    /**
     * @return Double
     *
     * Returns the AccountRecord value.
     */
    public Double getAmount() {
        return amount;
    }
}
