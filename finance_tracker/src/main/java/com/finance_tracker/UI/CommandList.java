package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;
import java.util.Scanner;

public class CommandList extends GeneralList{
    private Scanner UserCommand = new Scanner(System.in);

    public CommandList(UIFacade FacRef){
        super(FacRef);
    }

    public void RequestData(){}

    public void BuildString(){
        this.DisplayString = "";
        String Substring = String.format("|%10s: %20s|" , "Number","Option");
        this.DisplayString += Substring + '\n';
        Substring = "";

        Substring = String.format("|%3s: %20s|" , "1","Create Account");
        this.DisplayString += Substring + '\n';
        Substring = "";

        Substring = String.format("|%3s: %20s|" , "2","Add Account Value");
        this.DisplayString += Substring + '\n';
        Substring = "";

        Substring = String.format("|%3s: %20s|" , "3","Delete Account");
        this.DisplayString += Substring + '\n';
        Substring = "";

        Substring = String.format("|%3s: %20s|" , "4","Create Transaction");
        this.DisplayString += Substring + '\n';
        Substring = "";

        Substring = String.format("|%3s: %20s|" , "5","Delete Transaction");
        this.DisplayString += Substring + '\n';
        Substring = "";

        Substring = String.format("|%3s: %20s|" , "6","Exit");
        this.DisplayString += Substring + '\n';
        Substring = "";

        Substring = String.format("|%3s: %20s|" , "7","Exit");
        this.DisplayString += Substring + '\n';
        Substring = "";

        

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

