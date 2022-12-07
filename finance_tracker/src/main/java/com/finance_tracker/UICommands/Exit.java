package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class Exit extends Command{
    
    public Exit(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 9;
        this.Name = "Exit";
    }

    //Exit command to terminate program. 
    //Autosaves after exiting
    @Override
    public void execute() {
        this.CentralRef.endProgram();
        System.out.println("\n Goodbye!");
        this.CentralRef.getData().getFileManager().writeAll();
    }
}
