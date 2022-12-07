package com.finance_tracker.backendlogic;

import java.io.IOException;
import java.util.ArrayList;
import com.finance_tracker.UI.CommandList;
import com.finance_tracker.UI.TransactionList;
import com.finance_tracker.UI.AccountList;
import com.finance_tracker.UI.GeneralList;
import com.finance_tracker.UI.InitializationList;

public class UIFacade extends Subject {
    private Mediator CentralRef;
    private ArrayList<GeneralList> UIElements= new ArrayList<GeneralList>();
    private ArrayList<GeneralList> InitializationElements= new ArrayList<GeneralList>();

     
    //GeneralList AccountList = new AccountList(this);
    //GeneralList TransactionList = new TransactionList(this);
    //GeneralList CommandList = new CommandList(this);
    //GeneralList InitializationList = new InitializationList(this);


    //Constructor
    public UIFacade(Mediator CRef){
        registerObserver(CRef);
        CentralRef = CRef;
        this.UIElements.add(new AccountList(this));
        this.UIElements.add(new TransactionList(this));
        this.UIElements.add(new CommandList(this));
        this.InitializationElements.add(new InitializationList(this));
        notifyObserver("UIFacade Created");
    }
    
    //Update all values in the UI
    public void updateUI() throws IOException{
        for(GeneralList Item:this.UIElements ){
            Item.DisplayString();
        }
        this.CentralRef.getData().getFileManager().writeAll();
        
        notifyObserver("UI Updated");
    }


    public void showInitialization() throws IOException{
        //this.InitializationList.
        for(GeneralList Item:this.InitializationElements ){
            Item.DisplayString();
        }
        notifyObserver("Initializer Initialized");
    }



    public Mediator getMediator(){
        return this.CentralRef;
    }
}
