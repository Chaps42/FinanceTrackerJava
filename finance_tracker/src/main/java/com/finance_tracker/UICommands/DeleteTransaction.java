package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class DeleteTransaction extends Command{
    
    DeleteTransaction(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 5;
        this.Name = "Delete Transaction";

    }
}
