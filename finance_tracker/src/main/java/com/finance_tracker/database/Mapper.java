package com.finance_tracker.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountEnum;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;
import com.finance_tracker.transaction.TransactionEnum;


// The Singleton Pattern is used for the Mapper because only one of this
// utility is needed.

// The Mapper is an example of the Data Mapper Pattern. Typical Data Mappers
// have methods get(), add(), search(), and remove() and are the only class
// to interact with the Database.
// Any object that wants information about the Accounts and Transactions has
// to go through this Mapper.
// This Mapper has get(), add(), and remove() methods for Transactions and
// Accounts, and allows the users to search Accounts by name, type, and .
// Transactions can be searched by one-time vs recurring, category, and .
public class Mapper {
    Database database = Database.getInstance();
    FileManager fileManager = FileManager.getInstance();

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
    public void initializeMapper() {}


    // Methods for get()
    /**
     * @return HashMap<String, Account>
     *
     * This method returns the HashMap of Account names and Accounts.
     */
    public HashMap<String, Account> getAccounts() {
        HashMap<String, Account> accounts = database.getAccounts();
        return accounts;
    }


    /**
     * @return HashMap<String, Transaction>
     *
     * This method returns the HashMap of Transaction names and Transactions.
     */
    public HashMap<String, Transaction> getTransactions() {
        HashMap<String, Transaction> transactions = database.getTransactions();
        return transactions;
    }


    // Methods for add()
    /**
     * @param accounts HashMap<String, Account>
     *
     * This method sets the Database's HashMap of Accounts.
     */
    public void setAccounts(HashMap<String, Account> accounts) {
        database.setAccounts(accounts);
    }


    /**
     * @param transactions HashMap<String, Transaction>
     *
     * This method sets the Database's HashMap of Transactions.
     */
    public void setTransactions(HashMap<String, Transaction> transactions) {
        database.setTransactions(transactions);
    }


    /**
     * @param account Account
     *
     * This method adds a single Account to the Database.
     */
    public void addAccount(Account account) {
        HashMap<String, Account> accounts = getAccounts();
        accounts.put(account.getName(), account);
        setAccounts(accounts);
    }


    /**
     * @param transaction Transaction
     *
     * This method adds a single Transaction to the Database.
     */
    public void addTransaction(Transaction transaction) {
        HashMap<String, Transaction> transactions = getTransactions();
        transactions.put(transaction.getName(), transaction);
        setTransactions(transactions);
    }


    // Methods for search()
    /**
     * @param name String
     * @return Account
     *
     * This method searches for an Account with a given name.
     */
    public Account findAccount(String name) {
        HashMap<String, Account> accounts = getAccounts();
        return accounts.get(name);
    }


    /**
     * @param accountEnum AccountEnum
     * @return ArrayList<Account>
     *
     * Finds all Accounts that share a given AccountEnum Type (i.e. CHECKING).
     * See class: AccountEnum.
     */
    public ArrayList<Account> getAllAccountsOfType(AccountEnum accountEnum){
        ArrayList<Account> filteredAccounts = new ArrayList<Account>();
        for (Account a: getAccounts().values()) {
            if (a.getAccountEnum() == accountEnum){
                filteredAccounts.add(a);
            }
        }
        return filteredAccounts;
    }


    /**
     * @param name String
     * @return Transaction
     *
     * This method searches for a Transaction with a given name.
     */
    public Transaction findTransaction(String name) {
        HashMap<String, Transaction> transactions = getTransactions();
        return transactions.get(name);
    }


    /**
     * @param categoryEnum CategoryEnum
     * @return ArrayList<Transaction>
     *
     * This method finds all Transactions that share a category.
     * See class: CategoryEnum.
     */
    public ArrayList<Transaction> getAllTransactionsOfCategory(
        CategoryEnum categoryEnum) {
            ArrayList<Transaction> filteredTransactions =
                new ArrayList<Transaction>();
            for (Transaction t: getTransactions().values()) {
                if (t.getCategory() == categoryEnum){
                    filteredTransactions.add(t);
                }
            }
            return filteredTransactions;
    }


    /**
     * @return ArrayList<Transaction>
     *
     * This method finds an ArrayList of all Transactions that recur.
     */
    public ArrayList<Transaction> getRecurringTransactions() {
        HashMap<String, Transaction>  transactions = getTransactions();
        Collection<String> transactionKeys = transactions.keySet();

        ArrayList<Transaction> recurringTransactions =
            new ArrayList<Transaction>();
        for (String s: transactionKeys) {
            Transaction transaction = transactions.get(s);
            TransactionEnum transactionEnum = transaction.getTransactionEnum();

            if (transactionEnum.equals(TransactionEnum.RECURRING)) {
                recurringTransactions.add(transaction);
            }
        }
        return recurringTransactions;
    }


    /**
     * @return ArrayList<Transaction>
     *
     * This methods finds an ArrayList of all Transactions that do not recur.
     */
    public ArrayList<Transaction> getOneTimeTransactions() {
        HashMap<String, Transaction>  transactions = getTransactions();
        Collection<String> transactionKeys = transactions.keySet();

        ArrayList<Transaction> oneTimeTransactions =
            new ArrayList<Transaction>();
        for (String s: transactionKeys) {
            Transaction transaction = transactions.get(s);
            TransactionEnum transactionEnum = transaction.getTransactionEnum();

            if (transactionEnum.equals(TransactionEnum.ONE_TIME)) {
                oneTimeTransactions.add(transaction);
            }
        }
        return oneTimeTransactions;
    }


    // Methdos for remove()
    /**
     * @param name String
     *
     * This method removes an Account by its name.
     */
    public void removeAccountByName(String name) {
        HashMap<String, Account> accounts = getAccounts();
        accounts.remove(name);
        setAccounts(accounts);

        fileManager.delAccountFile(name);
    }


    /**
     * @param account Account
     *
     * This method removes an Account.
     */
    public void removeAccount(Account account) {
        removeAccountByName(account.getName());
    }


    public FileManager getFileManager(){
        return this.fileManager;
    }

    // Add a get all transactions from last month only? so we can compare montlhy spending

    /**
     * @param name String
     *
     * This method removes a Transaction by its name.
     */
    public void removeTransactionByName(String name) {
        HashMap<String, Transaction> transactions = getTransactions();
        transactions.remove(name);
        setTransactions(transactions);
    }


    /**
     * @param transaction Transaction
     *
     * This method removes a Transaction
     */
    public void removeTransaction(Transaction transaction) {
        removeTransactionByName(transaction.getName());
    }

}
