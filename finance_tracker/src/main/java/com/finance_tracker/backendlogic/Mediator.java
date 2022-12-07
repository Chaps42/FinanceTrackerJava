package com.finance_tracker.backendlogic;

import java.io.IOException;
import java.util.Scanner;

public class Mediator extends Observer{
    protected Scanner UserInput = new Scanner(System.in);
    private DataFacade DataInterface;
    private UIFacade UIInterface;
    private boolean Running = true;


    //Central link between the UI and data handler. Implements mediator pattern s it provides a reference to other 
    //Parts of the code. IT allows objects to get the UI or Data Facade easily. IT also runs the program in therun Method. 
    
    //Initializes the rest of the program. Creates the UI and Data facade so they may be constructed. 
    public Mediator(){
        this.recordMessage("Initializing Mediator");
        DataInterface = new DataFacade(this);
        UIInterface = new UIFacade(this);
    }

    //Method to provide link to the UI Facade
    public UIFacade getUI(){
        return UIInterface;
    }

    //Method to provide link to the Data Facade
    public DataFacade getData(){
        return DataInterface;
    }


    //Main function which runs the code. This method only runs the UI, which constantly prompts
    //The user on what to do next. All the commands and originate from this method, and thus
    //the program cannot run without it.
    public void run() throws IOException{
        UIInterface.showInitialization();
        while (this.Running==true){
            //https://stackoverflow.com/questions/2979383/how-to-clear-the-console
            //System.out.print("\033[H\033[2J");  
            //System.out.flush();  
            UIInterface.updateUI();
        }
    }

    public Scanner getScanner(){
        return this.UserInput;
    }

    //Method to end program
    public void endProgram(){
        this.Running = false;
    }

}
