package com.finance_tracker.UICommands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        //Code found here https://www.baeldung.com/java-string-to-date
        boolean ValidDate = true;
        Date Now = new Date();
        while(ValidDate){
            System.out.println("Enter the date (dd-MMM-yyyy):");
            String EnteredDate = this.UserInput.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            try{Now = formatter.parse(EnteredDate);
                ValidDate = false;}
            catch(ParseException A){
                Now = new Date();}}

        
        HashMap<String,Account> Data = this.CentralRef.getData().getAllAccounts();

        for (String name : Data.keySet()) {
            if(AccName.equals(name)){
                AccountRecord R = new AccountRecord(Now, Accvalue);
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
