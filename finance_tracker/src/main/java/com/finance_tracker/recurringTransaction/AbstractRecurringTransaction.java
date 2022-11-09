package com.finance_tracker.dataTypes.recurringTransaction;

public class AbstractRecurringTransaction {
    private String transactionName;
    private Enum recuringTimeTransactionEnum;
    private double transactionValue;
    private int transactionFrequency;
    private ArrayList<Date> transactionDates;
}