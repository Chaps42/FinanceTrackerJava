package com.finance_tracker.backendlogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.finance_tracker.account.*;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;
import com.finance_tracker.transaction.TransactionBuilder;


import com.finance_tracker.transaction.*;;


class DataFacade extends Subject{
    private HashMap<String,Account> AccountMap = new HashMap<String,Account>();
    private HashMap<String,Transaction> TransactionMap = new HashMap<String,Transaction>();
    private HashMap<String,CategoryEnum> CatagoryMap = new HashMap<String,CategoryEnum>();
    private Mediator CentralRef;

    public DataFacade(Mediator CRef){
        this.registerObserver(CRef);
        CentralRef = CRef;
    }

    public void createAccount(String Name, AccountEnum Enum){
        ArrayList<AccountRecord> emptyList = new ArrayList<AccountRecord>();
        AccountBuilder builder = new AccountBuilder(Name, emptyList)
            .setAccountEnum(Enum)
            .setInterestEnum(null)
            .setInterestRate(0)
            .setInterestPeriodEnum(null)
            .setLastInterestDate(null);
        AccountMap.put(Name,builder.buildAccount());
        notifyObserver("Account Created: "+Name);
    }

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
        TransactionMap.put(name, transaction); // This seems potentially redundant with the Mapper class!
        notifyObserver("Transaction Created: " + name);
    }


    public void addValue(String Name,DataTypeEnum Type,Date D, Double Value){
        switch(Type){
        case ACCOUNT:
            AccountRecord record = new AccountRecord(D, Value);
            AccountMap.get(Name).addRecord(record);
        case CATEGORY: // is this still relevant with the simplified version of categories?
            CatagoryMap.get(Name).recordValue(D, Value);}
    
    }

    public void deleteValue(String Name,DataTypeEnum Type,Date D){
        switch(Type){
            case ACCOUNT:
                AccountRecord record = AccountMap.get(Name).getRecord(D);
                AccountMap.get(Name).removeRecord(record);
            case CATEGORY:
                CatagoryMap.get(Name).deleteValue(D);
        
    }



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
