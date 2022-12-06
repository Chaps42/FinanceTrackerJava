package com.finance_tracker.backendlogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.finance_tracker.account.*;
import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;
import com.finance_tracker.transaction.TransactionBuilder;


import com.finance_tracker.transaction.*;;


public class DataFacade extends Subject {
    private Mediator CentralRef; // This reference is unused?
    Mapper databaseMapper = Mapper.getInstance();


    public DataFacade(Mediator centralRef) {
        this.registerObserver(centralRef);
        this.CentralRef = centralRef;
    }


    // Does this defeat the purpose of the Builer pattern?
    public void createAccount(String Name, AccountEnum Enum){
        ArrayList<AccountRecord> emptyList = new ArrayList<AccountRecord>();
        AccountBuilder builder = new AccountBuilder(Name, emptyList)
            .setAccountEnum(Enum)
            .setInterestEnum(null)
            .setInterestRate(0)
            .setInterestPeriodEnum(null)
            .setLastInterestDate(null);
        databaseMapper.addAccount(builder.buildAccount());
        notifyObserver("Account Created: "+Name);
        CentralRef.getUI().updateUI();
    }


    // Does this defeat the purpose of the Builer pattern?
    public void createTransaction(String name, TransactionEnum transactionEnum, Double value, Date date, Account wAccount, TransactionFrequencyEnum frequencyEnum, CategoryEnum category){
        TransactionBuilder transactionBuilder = new TransactionBuilder(
                    name,
                    transactionEnum,
                    value,
                    date,
                    wAccount)
                .setFrequency(frequencyEnum)
                .setCategory(category);
        
            Transaction transaction = transactionBuilder.buildTransaction();
        databaseMapper.addTransaction(transaction);
        notifyObserver("Transaction Created: " + name);
    }


    public void addAccount(Account account) {
        databaseMapper.addAccount(account);
        notifyObserver("Account " + account.getName() + "Added");
    }

    public void addTransaction(Transaction transaction) {
        databaseMapper.addTransaction(transaction);
        notifyObserver("Transaction " + transaction.getName() + "Added");
    }

    public void deleteAccount(Account account) {
        databaseMapper.removeAccount(account);
        notifyObserver("Account " + account.getName() + "Removed");
    }

    public void deleteTransaction(Transaction transaction) {
        databaseMapper.removeTransaction(transaction);
        notifyObserver("Transaction " + transaction.getName() + "Removed");
    }

    public HashMap<String, Account> getAllAccounts() {
        notifyObserver("All Accounts Returned");
        return databaseMapper.getAccounts();
    }

    public HashMap<String, Transaction> getAllTransactions() {
        notifyObserver("All Transactions Returned");
        return databaseMapper.getTransactions();
    }
    


    public ArrayList<Account> getAllAccountsOfType(AccountEnum accountEnum) {
        notifyObserver("All Accounts Type: " + accountEnum + " Returned");
        return databaseMapper.getAllAccountsOfType(accountEnum);
    }

    public ArrayList<Transaction> getAllTransactionsOfCategory(CategoryEnum categoryEnum) {
        notifyObserver("All Transactions Category: " + categoryEnum + " Returned");
        return databaseMapper.getAllTransactionsOfCategory(categoryEnum);
    }


    public ArrayList<Transaction> getAllOneTimeTransactions() {
        notifyObserver("All One Time Transactions Returned");
        return databaseMapper.getOneTimeTransactions();
    }

    public ArrayList<Transaction> getAllRecurringTransactions() {
        notifyObserver("All Recurring Transactions Returned");
        return databaseMapper.getRecurringTransactions();
    }
}
