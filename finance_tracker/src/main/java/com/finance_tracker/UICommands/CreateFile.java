package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class CreateFile extends Command{
    
    public CreateFile(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 1;
        this.Name = "Create File";

    }

    //Allows user to start writing new files for their finance tracker
    public void execute(){
        System.out.print("Enter Name: ");
        this.CentralRef.getData().getFileManager().writeAll();



    }
}
