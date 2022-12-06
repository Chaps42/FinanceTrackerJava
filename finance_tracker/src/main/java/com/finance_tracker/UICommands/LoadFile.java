package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class LoadFile extends Command{
    
    LoadFile(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 1;
        this.Name = "Load File";

    }
}
