package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class LoadFile extends Command{
    
    public  LoadFile(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 2;
        this.Name = "Load File";

    }
    public void execute(){
        System.out.println("File Loaded\n");

    }
}
