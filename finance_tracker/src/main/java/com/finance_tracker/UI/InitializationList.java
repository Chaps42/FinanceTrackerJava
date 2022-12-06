package com.finance_tracker.UI;

import java.util.ArrayList;
import java.util.Scanner;

import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.UICommands.LoadFile;
import com.finance_tracker.UICommands.Command;
import com.finance_tracker.UICommands.CreateFile;

public class InitializationList extends GeneralList {
    private Scanner UserCommand = this.FacRef.getMediator().getScanner();
    private ArrayList<Command> CommandList = new ArrayList<Command>();
    
    public InitializationList(UIFacade FacRef){
        super(FacRef);

        CommandList.add(new CreateFile(this.FacRef.getMediator()));
        CommandList.add(new LoadFile(this.FacRef.getMediator()));
    }
    protected void BuildString(){
        String Substring = "Welcome! \n Enter a number to begin ";
        this.DisplayString += Substring+"\n";

        for(Command Item: CommandList){
            Substring = String.format("|%3s: %20s|" ,String.valueOf(Item.getNumber()),Item.getName());
            this.DisplayString += Substring + '\n';
            Substring = "";}

    }


    protected void AwaitCommand(){
        boolean validChoice = true;

        while(validChoice){
            System.out.println("Enter your choice: ");
            Integer Choice = UserCommand.nextInt();
            
            switch(Choice){
                case 1:
                    CommandList.get(0).execute();
                    validChoice = false;
                    break;
                case 2:
                    CommandList.get(1).execute();
                    validChoice = false;
                    break;
                default:
                    System.out.println("Try again");


        }}
    }

    protected void RequestData(){};

    
}