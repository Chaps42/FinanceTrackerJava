package com.finance_tracker.dataTypes.account;

import com.finance_tracker.dataTypes.Date;

public class AccountRecord {
    private Date date;
    private double amount;


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
    public double getAmount() {
        return amount;
    }


    /**
     * @param amount float
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }
}
