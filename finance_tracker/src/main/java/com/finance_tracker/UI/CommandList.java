package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.UICommands.Command;
import com.finance_tracker.UICommands.CreateAccount;
import com.finance_tracker.UICommands.EnterAccountValue;
import com.finance_tracker.UICommands.DeleteAccount;
import com.finance_tracker.UICommands.CreateTransaction;
import com.finance_tracker.UICommands.DeleteTransaction;
import com.finance_tracker.UICommands.PlotData;
import com.finance_tracker.UICommands.Exit;
import java.util.Scanner;
import java.util.ArrayList;

public class CommandList extends GeneralList{
    private Scanner UserCommand = this.FacRef.getMediator().getScanner();
    private ArrayList<Command> CommandList = new ArrayList<Command>();

    public CommandList(UIFacade FacRef){
        super(FacRef);
        CommandList.add(new CreateAccount(this.FacRef.getMediator()));
        CommandList.add(new EnterAccountValue(this.FacRef.getMediator()));
        CommandList.add(new DeleteAccount(this.FacRef.getMediator()));
        CommandList.add(new CreateTransaction(this.FacRef.getMediator()));
        CommandList.add(new DeleteTransaction(this.FacRef.getMediator()));
        CommandList.add(new PlotData(this.FacRef.getMediator()));
        CommandList.add(new Exit(this.FacRef.getMediator()));

    }

    public void RequestData(){}

    public void BuildString(){
        this.DisplayString = "";
        String Substring = String.format("|%10s: %20s|" , "Number","Option");
        this.DisplayString += Substring + '\n';
        Substring = "";

        for(Command Item: CommandList){
            Substring = String.format("|%3s: %20s|" ,String.valueOf(Item.getNumber()),Item.getName());
            this.DisplayString += Substring + '\n';
            Substring = "";
        }

    }
    public void AwaitCommand(){
        boolean validChoice = true;

        while(validChoice){
            System.out.println("Enter your chioce: ");
            Integer Choice = UserCommand.nextInt();
            
            switch(Choice){
                case 1:
                    CommandList.get(1).execute();
                    validChoice = false;
                    break;
                case 2:
                    CommandList.get(2).execute();
                    validChoice = false;
                    break;
                case 3:
                    CommandList.get(3).execute();
                    validChoice = false;
                    break;
                case 4:
                    CommandList.get(4).execute();
                    validChoice = false;
                    break;
                case 5:
                    CommandList.get(5).execute();
                    validChoice = false;
                    break;
                case 6:
                    CommandList.get(6).execute();
                    validChoice = false;
                    break;
                case 7:
                    CommandList.get(7).execute();
                    validChoice = false;
                    break;                
                default:
                    System.out.println("Try again");


        }}
    }

}

    



