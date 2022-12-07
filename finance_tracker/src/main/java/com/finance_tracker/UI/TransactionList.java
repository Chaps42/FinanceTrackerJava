package com.finance_tracker.UI;


import java.util.HashMap;
import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.transaction.Transaction;;

public class TransactionList extends GeneralList{
    private HashMap<String,Transaction> Data;


    public TransactionList(UIFacade FacRef){
        super(FacRef);
    }

    //Function to get the required data for the transactions UI
    public void RequestData(){
        this.Data =  this.DataRef.getAllTransactions();
    }

    //Funciton to build the display string piece by piece
    public void BuildString(){
        this.DisplayString = "";
        String Substring = "Transactions: ";
        this.DisplayString += Substring + '\n';
        Substring = String.format("|%20s|%30s|%15s|%15s|%15s|%15s|%15s|" , "Name", "Date","Value", "Type","Account","Frequency","Category" );
        this.DisplayString += Substring + '\n';
        Substring = "";
        this.RequestData();
        for (String name : this.Data.keySet()) {
            Transaction Acc = Data.get(name);
            String A = Acc.getName();
            String B = String.valueOf(Acc.getValue());
            String C = Acc.getDate().toString();
            String D = Acc.getTransactionEnum().name();
            String E = new String();
            if (Acc.getTransactionAccount() != null) {
                E = Acc.getTransactionAccount().getName();
            }
            String F = Acc.getFrequency().name();
            String G = Acc.getCategory().name();
            Substring = String.format("|%20s|%30s|%15s|%15s|%15s|%15s|%15s|" , A,C,B, D,E,F,G );
            this.DisplayString += Substring + '\n';
            Substring = "";
        }
    }

    //Empty Await command in case additional functionality is needed in the future
    public void AwaitCommand(){}

}
