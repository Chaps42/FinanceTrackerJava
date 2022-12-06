package com.finance_tracker.UI;

import java.util.ArrayList;
import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.UICommands.LoadFile;
import com.finance_tracker.UICommands.Command;
import com.finance_tracker.UICommands.CreateFile;

public class InitializationList extends GeneralList {

    private ArrayList<Command> CommandList = new ArrayList<Command>();
    
    public InitializationList(UIFacade FacRef){
        super(FacRef);
        CommandList.add(new LoadFile(this.FacRef.getMediator()));
        CommandList.add(new CreateFile(this.FacRef.getMediator()));
    }
    protected void BuildString(){
        String Substring = "Welcome! ";
        this.DisplayString += Substring+"\n";

        for(Command Item: CommandList){
            Substring = String.format("|%3s: %20s|" ,String.valueOf(Item.getNumber()),Item.getName());
            this.DisplayString += Substring + '\n';
            Substring = "";}
        
        Substring = String.format("|%1s: %20s|" , "2","New File");
        this.DisplayString += Substring + '\n';
        Substring = "";}

    protected void AwaitCommand(){
        System.out.println();
    }b

    protected void RequestData(){};

    
}