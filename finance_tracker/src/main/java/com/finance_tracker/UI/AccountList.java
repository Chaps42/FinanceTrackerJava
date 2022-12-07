package com.finance_tracker.UI;

import java.util.HashMap;
import com.finance_tracker.account.*;
import com.finance_tracker.backendlogic.UIFacade;


//Class that only displays the accounts saved in the data file
public class AccountList extends GeneralList{
    private HashMap<String,Account> Data;
    

    public AccountList(UIFacade FacRef){
        super(FacRef);
    }

    //Function to get the required data for the Accounts UI
    public void RequestData(){
        this.Data =  this.DataRef.getAllAccounts();
    }

    //Funciton to build the display string piece by piece
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
            String F = Acc.getInterestEnum().name();
            String G = Acc.getInterestPeriodEnum().name();
            Substring = String.format("|%20s|%30s|%15s|%15s|%15s|%15s|%15s|" , A,C,B, D,E,F,G );
            this.DisplayString += Substring + '\n';
            Substring = "";
        }
    }

    //Empty Await command in case additional functionality is needed in the future
    public void AwaitCommand(){}

}

