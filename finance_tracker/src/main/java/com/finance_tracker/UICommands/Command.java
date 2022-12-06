package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public abstract class Command {
    
    protected Mediator CentralRef;

    public Command(Mediator CentralRef){
        this.CentralRef = CentralRef;
    }

    public abstract void execute();
}
