package com.finance_tracker.database;

import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.transaction.Transaction;

// Singleton Mapper
public class Mapper {
    Database database;
    FileManager fileManager;

    // Create a single object for lazy Singleton pattern
    private static Mapper instance;

    /**
     * Constructor for the Mapper.
     *
     * Making constructor private so that this class cannot be
     * instantiated.
     */
    private Mapper() {}

    /**
     * @return Mapper
     *
     * Gets the only Database Mapper.
     * Lazy Singleton.
     */
    public static Mapper getInstance() {
        if (instance == null) {
            instance = new Mapper();
        }
        return instance;
    }

    /**
     * Initializes the Mapper
     *
     * Necessary for Singleton Pattern becasue Mapper cannot have parameters,
     * but we want to pass certain information into it.
     */
    public void initializeMapper(Database database, FileManager filemanager) {
        this.database = database.getInstance();
    }


    public HashMap<String, Account> getAccounts() {
        HashMap<String, Account> accounts = database.getAccounts();
        return accounts;
    }


    public HashMap<String, Transaction> getTransactions() {
        HashMap<String, Transaction> transactions = database.getTransactions();
        return transactions;
    }


    public Account findAccount(String name) {
        HashMap<String, Account> accounts = getAccounts();
        return accounts.get(name);
    }


    public Transaction findTransaction(String name) {
        HashMap<String, Transaction> transactions = getTransactions();
        return transactions.get(name);
    }


    public void setAccounts(HashMap<String, Account> accounts) {
        database.setAccounts(accounts);
    }


    public void setTransactions(HashMap<String, Transaction> transactions) {
        database.setTransactions(transactions);
    }


    public void addAccount(Account account) {
        HashMap<String, Account> accounts = getAccounts();
        accounts.put(account.getName(), account);
        setAccounts(accounts);
    }

    public void addTransaction(Transaction transaction) {
        HashMap<String, Transaction> transactions = getTransactions();
        transactions.put(transaction.getName(), transaction);
        setTransactions(transactions);
    }

    public void removeAccountByName(String name) {
        HashMap<String, Account> accounts = getAccounts();
        accounts.remove(name);
        setAccounts(accounts);

        fileManager.delAccountFile(name);
    }
    
    public void removeTransactionByName(String name) {
        HashMap<String, Transaction> transactions = getTransactions();
        transactions.remove(name);
        setTransactions(transactions);
    }


    public void removeAccount(Account account) {
        removeAccountByName(account.getName());
    }


    public void removeTransaction(Transaction transaction) {
        removeTransactionByName(transaction.getName());
    }
}
