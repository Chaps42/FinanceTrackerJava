package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class CreateTransaction extends Command{
    
    CreateTransaction(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 4;
        this.Name = "Create Transaction";

    }
}
