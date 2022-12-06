package com.finance_tracker.account;

import java.util.Date;

public class AccountRecord {
    private Date date;
    private Double amount;


    // Constructor
    public AccountRecord(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }


    /**
     * @return Date
     */
    public Date getDate() {
        return date;
    }


    /**
     * @param date Date
     */
    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * @return Double
     */
    public Double getAmount() {
        return amount;
    }


    /**
     * @param amount Double
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
