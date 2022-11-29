package com.finance_tracker.database;

import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.transaction.Transaction;

// We still need an object that holds all accounts so that we can find them by name.
// Singleton Database
public class Database {
    static HashMap<String, Account> accounts;
    static HashMap<String, Transaction> transactions;

    // Create a single object for lazy Singleton pattern
    private static Database instance;

    /**
     * Constructor for the Database.
     *
     * Making constructor private so that this class cannot be
     * instantiated.
     */
    private Database() {}


    /**
     * @return Database
     *
     * Gets the only Database.
     * Lazy Singleton.
     */
    public Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }


    /**
     * Initializes the Database
     *
     * Necessary for Singleton Pattern becasue Database cannot have parameters,
     * but we want to pass certain information into it.
     */
    public void initializeDatabase(HashMap<String, Account> accounts, HashMap<String, Transaction> transactions) {
        setAccounts(accounts);
        setTransactions(transactions);
    }


    void setAccounts(HashMap<String, Account> accounts) {
        Database.accounts = accounts;
    }

    void setTransactions(HashMap<String, Transaction> transactions) {
        Database.transactions = transactions;
    }


    public HashMap<String, Account> getAccounts() {
        return accounts;
    }


    public HashMap<String, Transaction> getTransactions() {
        return transactions;
    }
}
