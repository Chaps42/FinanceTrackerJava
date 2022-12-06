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
    private Scanner UserCommand = new Scanner(System.in);
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

        Substring = String.format("Enter your chioce: ");
        this.DisplayString += Substring + '\n';
        Substring = "";

    }
    public void AwaitCommand(){
        Integer I = UserCommand.nextInt();
        switch(I){
            case 1:
                System.out.println("Option 1");
            case 2:
                System.out.println("Option 2");
            case 3:
                System.out.println("Option 3");
            case 4:
                System.out.println("Option 4");
            case 5:
                System.out.println("Option 5");
            case 6:
                System.out.println("Option 6");
            case 7: 
                System.out.println("Option 7");
        }

    }

    

}

