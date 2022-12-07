package com.finance_tracker.UICommands;

import java.util.Date;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.backendlogic.Mediator;
import com.finance_tracker.transaction.Transaction;

public class DeleteTransaction extends Command{
    
    public DeleteTransaction(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 5;
        this.Name = "Delete Transaction";

    }

    //Allows the user to delete an unwanted transaction by name
    public void execute(){
        boolean TransactionFound = false;
        System.out.println("Enter Transaction name: ");
        String AccName = this.UserInput.nextLine();
  
        HashMap<String,Transaction> Data = this.CentralRef.getData().getAllTransactions();

        for (String name : Data.keySet()) {
            if(AccName.equals(name)){
                Date Now = Data.get(name).getDate();
                Account Account = Data.get(name).getTransactionAccount();
                double Value = Data.get(name).getValue();
                AccountRecord R = new AccountRecord(Now, Account.getRecord(Now).getAmount()-Value);
                
                Data.get(name).getTransactionAccount().addRecord(R);
                this.DataRef.deleteTransaction(Data.get(name));
                TransactionFound = true;
                break;
            }
        }
        if(TransactionFound){
            System.out.println("Transaction Deleted");
        }
        else{
            System.out.println("No Transaction Found");
        }
    }

}
