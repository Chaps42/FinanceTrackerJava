package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class PlotData extends Command{
    
    PlotData(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 6;
        this.Name = "Show Plot";

    }
}
