package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class DeleteAccount extends Command{
    
    DeleteAccount(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 3;
        this.Name = "Delete Account";

    }
}
