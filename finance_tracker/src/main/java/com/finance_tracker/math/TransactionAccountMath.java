package com.finance_tracker.math;

import java.util.Date;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.Transaction;


public class TransactionAccountMath {
    Mapper databaseMapper = Mapper.getInstance();


    /**
     * Constructor for the TransactionAccountMath utility class.
     */
    public TransactionAccountMath() {}


    /**
     * @param transaction Transaction
     *
     * This method edits the target Account by the Transaction value.
     */
    public void editAccountAccording2Transaction(Transaction transaction) {
        Date date = transaction.getDate();
        Double transactionValue = transaction.getValue();
        Account account = transaction.getTransactionAccount();

        Double accountValue = account.getValue();
        Double newValue = accountValue + transactionValue;

        AccountRecord newRecord = new AccountRecord(date, newValue);
        account.addRecord(newRecord);
    }
}
