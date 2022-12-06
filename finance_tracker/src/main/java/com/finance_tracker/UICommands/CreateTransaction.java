package com.finance_tracker.UICommands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountBuilder;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.backendlogic.Mediator;
import com.finance_tracker.transaction.TransactionBuilder;
import com.finance_tracker.transaction.Transaction;
import com.finance_tracker.transaction.TransactionEnum;
import com.finance_tracker.transaction.TransactionFrequencyEnum;

public class CreateTransaction extends Command{
    
    public CreateTransaction(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 4;
        this.Name = "Create Transaction";

    }

    public void execute(){
        AccountBuilder defaultBuilder = new AccountBuilder("Default", new ArrayList<AccountRecord>());

        boolean AccountFound = false;
        System.out.println("Enter Name: ");
        Name = this.UserInput.nextLine();
        TransactionEnum Type;

        System.out.println("Enter Transaction Value: ");
        double Value = this.UserInput.nextDouble();
        
        System.out.println("Enter Transaction Type (int): ");
        int i =1;
        for(TransactionEnum E: TransactionEnum.values()){
            System.out.print(String.valueOf(i)+": ");
            System.out.println(E);
            i++;
        }
        int EnumType = this.UserInput.nextInt();
        switch(EnumType){
            case 1:
                Type = ( TransactionEnum.ONE_TIME);
                break;
            case 2:
                Type = ( TransactionEnum.RECURRING);
                break;
            default:
                Type = ( TransactionEnum.ONE_TIME);
                break;
        }

        System.out.println("Enter Transaction Frequency (int): ");
        int j =1;
        for(TransactionFrequencyEnum E: TransactionFrequencyEnum.values()){
            System.out.print(String.valueOf(j)+": ");
            System.out.println(E);
            j++;
        }
        TransactionFrequencyEnum FreqEnum;
        EnumType = this.UserInput.nextInt();
        switch(EnumType){
            case 1:
                FreqEnum = ( TransactionFrequencyEnum.WEEKLY);
                break;
            case 2:
                FreqEnum = ( TransactionFrequencyEnum.MONTHLY);
                break;
            case 3:
                FreqEnum = ( TransactionFrequencyEnum.ANNUALLY);
                break;
            default:
                FreqEnum = ( TransactionFrequencyEnum.ANNUALLY);
                break;
        }


        System.out.println("Enter Transaction Account Name: ");
        String AccName = this.UserInput.nextLine();

        HashMap<String,Account> Data = this.CentralRef.getData().getAllAccounts();
        Account Account;
        Account depositedAccount = defaultBuilder.buildAccount();
        Account defaultAccount = defaultBuilder.buildAccount();
        for (String name : Data.keySet()) {
            if(AccName.equals(name)){
                depositedAccount = Data.get(name);
                AccountFound = true;
                break;
            }
            defaultAccount = Data.get(name);
        }   
        if(AccountFound){
            System.out.println("Account Found");
            Account = depositedAccount;
        }
        else{
            System.out.println("No Account Found");
            Account = defaultAccount;

        }


        Date Now = new Date();
        TransactionBuilder Builder = new TransactionBuilder(Name,Type,Value,Now,Account);
        Builder.setFrequency(FreqEnum);

        Transaction NewT= Builder.buildTransaction();
        CentralRef.getData().addTransaction(NewT);




    }

}
