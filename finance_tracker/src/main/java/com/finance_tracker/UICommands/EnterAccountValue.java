package com.finance_tracker.UICommands;

import java.util.Date;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.backendlogic.Mediator;

public class EnterAccountValue extends Command{
    
    public EnterAccountValue(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 2;
        this.Name = "Add Account Value";

    }

    @Override
    public void execute() {
        // 
        boolean AccountFound = false;
        System.out.println("Enter Account name: ");
        String AccName = this.UserInput.nextLine();
        System.out.println("Enter Amount: ");
        double Accvalue = this.UserInput.nextDouble();

        
        HashMap<String,Account> Data = this.CentralRef.getData().getAllAccounts();

        for (String name : Data.keySet()) {
            if(AccName.equals(name)){
                AccountRecord R = new AccountRecord(new Date(), Accvalue);
                Data.get(name).addRecord(R);
                AccountFound = true;
                break;
            }
        }
        if(AccountFound){
            System.out.println("Account Found");
        }
        else{
            System.out.println("No Account Found");
        }
    }
}
