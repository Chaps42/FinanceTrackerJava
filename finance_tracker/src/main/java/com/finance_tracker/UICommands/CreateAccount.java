package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class CreateAccount extends Command{
    
    public CreateAccount(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 1;
        this.Name = "Create Account";
    }

}
