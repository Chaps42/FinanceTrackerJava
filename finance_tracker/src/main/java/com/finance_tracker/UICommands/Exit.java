package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class Exit extends Command{
    
    public Exit(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 7;
        this.Name = "Exit";
    }

    @Override
    public void execute() {
        this.CentralRef.endProgram();
    }
}
