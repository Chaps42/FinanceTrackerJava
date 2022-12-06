package com.finance_tracker.backendlogic;

import com.finance_tracker.UI.CommandList;
import com.finance_tracker.UI.TransactionList;
import com.finance_tracker.UI.PlotData;
import com.finance_tracker.UI.AccountList;
import com.finance_tracker.UI.GeneralList;

public class UIFacade extends Subject {
    Mediator CentralRef;
     
    GeneralList AccountList = new AccountList(this);
    GeneralList TransactionList = new TransactionList(this);
    GeneralList CommandList = new CommandList(this);


    //Constructor
    public UIFacade(Mediator CRef){
        registerObserver(CRef);
        CentralRef = CRef;
    }
    
    //Update all values in the UI
    public void updateUI(){
        CentralRef.getData().getAllAccounts();
        CentralRef.getData().getAllOneTimeTransactions();
        CentralRef.getData().getAllCategories();
        notifyObserver("UI Updated");
    }


    private void showInitialization(){}
    private void displayAccounts(){}
    private void displayTransactions(){}
    private void displayCommands(){}
    private Boolean AwaitCommand(){


        return 
    }



}
