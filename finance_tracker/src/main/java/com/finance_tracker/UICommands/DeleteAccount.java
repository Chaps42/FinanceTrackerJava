package com.finance_tracker.UICommands;

import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.backendlogic.Mediator;

public class DeleteAccount extends Command{
    
    public DeleteAccount(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 3;
        this.Name = "Delete Account";

    }

    //Allows the user to delete an unwanted account by name
    public void execute(){
        boolean AccountFound = false;
        System.out.println("Enter Account name: ");
        String AccName = this.UserInput.nextLine();
  
        HashMap<String,Account> Data = this.CentralRef.getData().getAllAccounts();

        for (String name : Data.keySet()) {
            if(AccName.equals(name)){
                this.DataRef.deleteAccount(Data.get(name));
                AccountFound = true;
                break;
            }
        }
        if(AccountFound){
            System.out.println("Account Deleted");
        }
        else{
            System.out.println("No Account Found");
        }
    }


    

}


