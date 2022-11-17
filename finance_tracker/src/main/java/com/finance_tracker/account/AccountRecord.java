package com.finance_tracker.account;

import java.util.Date;

public class AccountRecord {
    private Date date;
    private Double amount;


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
     * @return float
     */
    public Double getAmount() {
        return amount;
    }


    /**
     * @param amount float
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
