package com.finance_tracker.UICommands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountBuilder;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.backendlogic.Mediator;
import com.finance_tracker.transaction.TransactionBuilder;
import com.finance_tracker.transaction.CategoryEnum;
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
        String newTransaction = this.UserInput.nextLine();
        TransactionEnum Type;

        System.out.println("Enter Transaction Value: ");
        double Value = this.UserInput.nextDouble();
        this.UserInput.nextLine();
        
        System.out.println("Enter Transaction Type (int): ");
        int i =1;
        for(TransactionEnum E: TransactionEnum.values()){
            System.out.print(String.valueOf(i)+": ");
            System.out.println(E);
            i++;
        }
        int EnumType = this.UserInput.nextInt();
        this.UserInput.nextLine();
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
        this.UserInput.nextLine();
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



        System.out.println("Enter Transaction Category (int): ");
        j =1;
        for(CategoryEnum E: CategoryEnum.values()){
            System.out.print(String.valueOf(j)+": ");
            System.out.println(E);
            j++;
        }
        CategoryEnum CatEnum;
        EnumType = this.UserInput.nextInt();
        switch(EnumType){
            case 1:
                CatEnum = ( CategoryEnum.VACATION);
                break;
            case 2:
                CatEnum = ( CategoryEnum.GIFT);
                break;
            case 3:
                CatEnum = ( CategoryEnum.BILLS);
                break;
            case 4:
                CatEnum = ( CategoryEnum.FOOD);
                break;
            case 5:
                CatEnum = ( CategoryEnum.ENTERTAINMENT);
                break;
            case 6:
                CatEnum = ( CategoryEnum.CLOTHES);
                break;
            case 7:
                CatEnum = ( CategoryEnum.MISC_SPENDING);
                break;
            case 8:
                CatEnum = ( CategoryEnum.FITNESS);
                break;
            default:
                CatEnum = ( CategoryEnum.MISC_SPENDING);
                break;
        }


        TransactionBuilder Builder = new TransactionBuilder(newTransaction,Type,Value,Now,Account);
        Builder.setFrequency(FreqEnum);
        Builder.setCategory(CatEnum);

        Transaction NewT= Builder.buildTransaction();
        CentralRef.getData().addTransaction(NewT);




    }

}
