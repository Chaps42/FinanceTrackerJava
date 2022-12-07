package com.finance_tracker.UICommands;

import java.io.IOException;

import com.finance_tracker.backendlogic.Mediator;
import com.finance_tracker.math.RecurringTransactionMath;


public class RecurTransactions extends Command {
    RecurringTransactionMath recurringTransactionMath = new RecurringTransactionMath();
    

    public RecurTransactions(Mediator CentralRef) {
        super(CentralRef);
        this.CodeNum = 6;
        this.Name = "Check Recurring Transactions";
    }


    @Override
    public void execute() throws IOException {
        recurringTransactionMath.recurTransactions();
        System.out.println("Checked for and Applied Recurring Transactions");
    }
}
