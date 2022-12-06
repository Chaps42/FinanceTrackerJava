package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class EnterAccountValue extends Command{
    
    public EnterAccountValue(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 2;
        this.Name = "Add Account Value";

    }
}
