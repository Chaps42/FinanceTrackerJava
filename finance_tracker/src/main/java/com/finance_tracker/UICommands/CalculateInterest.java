package com.finance_tracker.UICommands;

import java.io.IOException;

import com.finance_tracker.backendlogic.Mediator;
import com.finance_tracker.math.InterestMath;


public class CalculateInterest extends Command {
    private InterestMath interestMath = new InterestMath();
    
    public CalculateInterest(Mediator CentralRef) {
        super(CentralRef);
        this.CodeNum = 7;
        this.Name = "Recalculate Interest";
    }


    @Override
    public void execute() throws IOException {
        interestMath.applyAllInterest();
        System.out.println("Interest Calculated");
    }
}
