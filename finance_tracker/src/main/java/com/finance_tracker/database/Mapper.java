package com.finance_tracker.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountEnum;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;
import com.finance_tracker.transaction.TransactionEnum;

// Singleton Mapper
public class Mapper {
    Database database;
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


    public ArrayList<Transaction> getRecurringTransactions() {
        HashMap<String, Transaction>  transactions = getTransactions();
        Collection<String> transactionKeys = transactions.keySet();

        ArrayList<Transaction> recurringTransactions = new ArrayList<Transaction>();
        for (String s: transactionKeys) {
            Transaction transaction = transactions.get(s);
            TransactionEnum transactionEnum = transaction.getTransactionEnum();

            if (transactionEnum.equals(TransactionEnum.RECURRING)) {
                recurringTransactions.add(transaction);
            }
        }
        return recurringTransactions;
    }


    public ArrayList<Transaction> getOneTimeTransactions() {
        HashMap<String, Transaction>  transactions = getTransactions();
        Collection<String> transactionKeys = transactions.keySet();

        ArrayList<Transaction> oneTimeTransactions = new ArrayList<Transaction>();
        for (String s: transactionKeys) {
            Transaction transaction = transactions.get(s);
            TransactionEnum transactionEnum = transaction.getTransactionEnum();

            if (transactionEnum.equals(TransactionEnum.ONE_TIME)) {
                oneTimeTransactions.add(transaction);
            }
        }
        return oneTimeTransactions;
    }

    public ArrayList<Account> getAllAccountsOfType(AccountEnum accountEnum){
        ArrayList<Account> filteredAccounts = new ArrayList<Account>();
        for (Account a: getAccounts().values()) {
            if (a.getAccountEnum() == accountEnum){
                filteredAccounts.add(a);
            }
        }
        return filteredAccounts;
    }

    public ArrayList<Transaction> getAllTransactionsOfCategory(CategoryEnum categoryEnum){
        ArrayList<Transaction> filteredTransactions = new ArrayList<Transaction>();
        for (Transaction t: getTransactions().values()) {
            if (t.getCategory() == categoryEnum){
                filteredTransactions.add(t);
            }
        }
        return filteredTransactions;
    }

    public FileManager getFileManager(){
        return this.fileManager;
    }

    // Add a get all transactions from last month only? so we can compare montlhy spending
}
