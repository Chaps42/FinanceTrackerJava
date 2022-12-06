package com.finance_tracker.UI;


import java.util.HashMap;
import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.transaction.Transaction;;

public class TransactionList extends GeneralList{
    private HashMap<String,Transaction> Data;


    public TransactionList(UIFacade FacRef){
        super(FacRef);
    }

    public void RequestData(){
        this.Data =  this.DataRef.getAllTransactions();
    }

    public void BuildString(){
        this.DisplayString = "";
        String Substring = String.format("|%15s|%15s|%15s|%15s|%15s|%15s|%15s|" , "Name", "Date","Value", "Type","Account","Frequency","Category" );
        this.DisplayString += Substring + '\n';
        Substring = "";
        this.RequestData();
        for (String name : this.Data.keySet()) {
            Transaction Acc = Data.get(name);
            String A = Acc.getName();
            String B = String.valueOf(Acc.getValue());
            String C = Acc.getDate().toString();
            String D = Acc.getTransactionEnum().name();
            String E = Acc.getTransactionAccount().getName();
            String F = Acc.getFrequency().name();
            String G = Acc.getCategory().name();
            Substring = String.format("|%15s|%15s|%15s|%15s|%15s|%15s|%15s|" , A,C,B, D,E,F,G );
            this.DisplayString += Substring + '\n';
            Substring = "";
        }
    }
    public void AwaitCommand(){}

}
