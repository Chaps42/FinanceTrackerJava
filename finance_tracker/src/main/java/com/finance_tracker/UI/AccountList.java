package com.finance_tracker.UI;

import java.util.HashMap;
import com.finance_tracker.account.*;
import com.finance_tracker.backendlogic.UIFacade;

public class AccountList extends GeneralList{
    private HashMap<String,Account> Data;
    

    public AccountList(UIFacade FacRef){
        super(FacRef);
    }

    public void RequestData(){
        this.Data =  this.DataRef.getAllAccounts();
    }

    public void BuildString(){
        this.DisplayString = "";
        String Substring = "Accounts: ";
        this.DisplayString += Substring + '\n';
        Substring = String.format("|%20s|%30s|%15s|%15s|%15s|%15s|%15s|" , "Name", "Date","Current Value", "Type","Interest Rate","Frequency","Interest Period" );
        this.DisplayString += Substring + '\n';
        Substring = "";
        this.RequestData();
        for (String name : this.Data.keySet()) {
            Account Acc = Data.get(name);
            String A = Acc.getName();
            String B = String.valueOf(Acc.getValue());
            String C = Acc.getDate().toString();
            String D = Acc.getAccountEnum().name();
            String E = String.valueOf(Acc.getInterestRate());
            String F;
            String G;
            if (Acc.getInterestEnum() != null) {
                // If Interest Values
                F = Acc.getInterestEnum().name();
                G = Acc.getInterestPeriodEnum().name();
            } else{
                F = new String();
                G = new String();
            }
            Substring = String.format("|%20s|%30s|%15s|%15s|%15s|%15s|%15s|" , A,C,B, D,E,F,G );
            this.DisplayString += Substring + '\n';
            Substring = "";
        }
    }

    public void AwaitCommand(){}

}

