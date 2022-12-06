package com.finance_tracker.database;

import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.transaction.Transaction;


// The Singleton Pattern was a good choice for the Database because
// we want to ensure that there is only one object storing the data.
// This is important both for memory use, and for accuracy.
// This way we don't have to worry about pointing to separate Databases
// and getting different values.
// We used Lazy Singleton because it is thread safe, which is not as big
// of a problem at this scale, but seems like better practice and a good habit.
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
    public void initializeDatabase(HashMap<String, Account> accounts,
        HashMap<String, Transaction> transactions) {
            setAccounts(accounts);
            setTransactions(transactions);
    }


    // All Setters
    /**
     * @param accounts HashMap<String, Account>
     *
     * Sets the Accounts in the Database in a HashMap by name.
     */
    void setAccounts(HashMap<String, Account> accounts) {
        Database.accounts = accounts;
    }

    /**
     * @param transactions HashMap<String, Transaction>
     *
     * Sets the Transactions in the Database in a HashMap by name.
     */
    void setTransactions(HashMap<String, Transaction> transactions) {
        Database.transactions = transactions;
    }


    // All Getters
    /**
     * @return HashMap<String, Account>
     *
     * Returns the HashMap of Account names and Accounts.
     */
    public HashMap<String, Account> getAccounts() {
        return accounts;
    }


    /**
     * @return HashMap<String, Transaction>
     *
     * Returns the HashMap of Transaction names and Transactions.
     */
    public HashMap<String, Transaction> getTransactions() {
        return transactions;
    }
}
