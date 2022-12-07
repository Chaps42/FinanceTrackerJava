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

     
    //UI Facade for ease of access to UI Elements. Responsible for running the UI
    //and providing a link to the central mediator for message passing. Implements 
    //Facade Pattern


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
    
    //Update all values in the UI. 
    public void updateUI() throws IOException{
        for(GeneralList Item:this.UIElements ){
            Item.DisplayString();
        }
        this.CentralRef.getData().getFileManager().writeAll();
        
        notifyObserver("UI Updated");
    }

    //Shows all the initialization elements
    public void showInitialization() throws IOException{
        for(GeneralList Item:this.InitializationElements ){
            Item.DisplayString();
        }
        notifyObserver("Initializer Initialized");
    }



    public Mediator getMediator(){
        return this.CentralRef;
    }
}
