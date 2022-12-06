package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;

public class InitializationList extends GeneralList {
    
    public InitializationList(UIFacade FacRef){
        super(FacRef);
    }
    protected void BuildString(){
        String subString = "Welcome! ";
        this.DisplayString += subString+"\n";

        subString = String.format("Enter your choice: ");
        this.DisplayString += subString + '\n';
        subString = "";

        subString = String.format("|%1s: %20s|" , "1","Create File");
        this.DisplayString += subString + '\n';
        subString = "";
        
        subString = String.format("|%1s: %20s|" , "2","New File");
        this.DisplayString += subString + '\n';
        subString = "";}

    protected void AwaitCommand(){
        System.out.println();
    }b

    protected void RequestData(){};

    
}