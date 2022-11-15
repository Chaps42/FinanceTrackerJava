package com.finance_tracker.backendlogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.finance_tracker.account.*;
import com.finance_tracker.transaction.Transaction;
import com.finance_tracker.transaction.TransactionBuilder;


import com.finance_tracker.transaction.*;;


class DataFacade extends Subject{
    private HashMap<String,Account> AccountMap = new HashMap<String,Account>();
    private HashMap<String,Transaction> TransactionMap = new HashMap<String,Transaction>();
    private Mediator CentralRef;

    public DataFacade(Mediator CRef){
        this.registerObserver(CRef);
        CentralRef = CRef;
    }

    public void createAccount(String Name, AccountEnum Enum){
        ArrayList<AccountRecord> emptyList = new ArrayList<AccountRecord>();
        AccountBuilder builder = new AccountBuilder(Name,emptyList);
        builder.setAccountEnum(Enum);
        builder.setInterestEnum(null);
        builder.setInterestRate(0);
        builder.setInterestPeriodEnum(null);
        AccountMap.put(Name,builder.buildAccount());
        notifyObserver("Account Created: "+Name);
    }

    public void createTransaction(String Name, TransactionEnum Enum,Double value,Date date,Account wAccount){
        TransactionBuilder builder = new TransactionBuilder(Name,Enum,value,date,wAccount);
        builder.setTransactionDates(null);
        builder.setTransactionFrequency(0);
        TransactionMap.put(Name,builder.buildTransaction());
        notifyObserver("Transaction Created: "+Name);
    }

    public HashMap<String,Account> getAllAccounts(){
        notifyObserver("All Accounts Returned");
        return AccountMap;
    }

    public ArrayList<Account> getAllAccountType(AccountEnum Enum){
        ArrayList<Account> List = new ArrayList<Account>();
        for(Map.Entry<String, Account> set :AccountMap.entrySet()){
            if(set.getValue().getAccountEnum() == Enum){
                List.add(set.getValue());
            }
        }
        notifyObserver("All Accounts Type: "+Enum +" Returned");
        return List;
    }

    public ArrayList<Transaction> getAllRecurringTransactions(){
        ArrayList<Transaction> List = new ArrayList<Transaction>();
        for(Map.Entry<String, Transaction> set :TransactionMap.entrySet()){
            if(set.getValue().getTransactionEnum() == TransactionEnum.RECURRING){
                List.add(set.getValue());
            }
        }
        notifyObserver("All Reccuring Transactions Returned");
        return List;


    }

    public ArrayList<Transaction> getAllOneTimeTransactions(){
        ArrayList<Transaction> List = new ArrayList<Transaction>();
        for(Map.Entry<String, Transaction> set :TransactionMap.entrySet()){
            if(set.getValue().getTransactionEnum() == TransactionEnum.ONE_TIME){
                List.add(set.getValue());
            }
        }
        notifyObserver("All One Time Transactions Returned");
        return List;

    }





}