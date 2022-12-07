package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.UICommands.CalculateInterest;
import com.finance_tracker.UICommands.Command;
import com.finance_tracker.UICommands.CreateAccount;
import com.finance_tracker.UICommands.EnterAccountValue;
import com.finance_tracker.UICommands.DeleteAccount;
import com.finance_tracker.UICommands.CreateTransaction;
import com.finance_tracker.UICommands.DeleteTransaction;
import com.finance_tracker.UICommands.PlotData;
import com.finance_tracker.UICommands.RecurTransactions;
import com.finance_tracker.UICommands.Exit;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class CommandList extends GeneralList{
    private Scanner UserCommand = this.FacRef.getMediator().getScanner();
    private ArrayList<Command> CommandList = new ArrayList<Command>();

    public CommandList(UIFacade FacRef){
        super(FacRef);
        CommandList.add(new CreateAccount(this.CentralRef));
        CommandList.add(new EnterAccountValue(this.CentralRef));
        CommandList.add(new DeleteAccount(this.CentralRef));
        CommandList.add(new CreateTransaction(this.CentralRef));
        CommandList.add(new DeleteTransaction(this.CentralRef));
        CommandList.add(new RecurTransactions(this.CentralRef));
        CommandList.add(new CalculateInterest(this.CentralRef));
        CommandList.add(new PlotData(this.CentralRef));
        CommandList.add(new Exit(this.CentralRef));

    }

    //Empty request data because commands are manually assigned. Could use additional objects to
    //Dynamically assign commands, like an invoker
    public void RequestData(){}

    //Builds String UI for Command List UI Element
    public void BuildString(){
        this.DisplayString = "";
        String Substring = "Controls: ";
        this.DisplayString += Substring + '\n';
        Substring = String.format("|%-10s: %-13s|" , "Number","Option");
        this.DisplayString += Substring + '\n';
        Substring = "";

        for(Command Item: CommandList){
            Substring = String.format("|%3s: %-20s|" ,String.valueOf(Item.getNumber()),Item.getName());
            this.DisplayString += Substring + '\n';
            Substring = "";
        }

    }

    //Await command to take in the command of the user. Launches commands in the Command Pattern
    public void AwaitCommand() throws IOException{
        boolean validChoice = true;

        while(validChoice){
            System.out.println("Enter your choice: ");
            Integer Choice = UserCommand.nextInt();
            UserCommand.nextLine();
            
            switch(Choice){
                case 1:
                    CommandList.get(0).execute();
                    validChoice = false;
                    break;
                case 2:
                    CommandList.get(1).execute();
                    validChoice = false;
                    break;
                case 3:
                    CommandList.get(2).execute();
                    validChoice = false;
                    break;
                case 4:
                    CommandList.get(3).execute();
                    validChoice = false;
                    break;
                case 5:
                    CommandList.get(4).execute();
                    validChoice = false;
                    break;
                case 6:
                    CommandList.get(5).execute();
                    validChoice = false;
                    break;
                case 7:
                    CommandList.get(6).execute();
                    validChoice = false;
                    break;     
                case 8:
                    CommandList.get(7).execute();
                    validChoice = false;
                    break;
                case 9:
                    CommandList.get(8).execute();
                    validChoice = false;
                    break;          
                default:
                    System.out.println("Try again");
        }}
    }
}
