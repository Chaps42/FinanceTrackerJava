package com.finance_tracker.backendlogic;

import java.io.IOException;
import java.util.Scanner;

public class Mediator extends Observer{
    protected Scanner UserInput = new Scanner(System.in);
    private DataFacade DataInterface;
    private UIFacade UIInterface;
    private boolean Running = true;

    
    public Mediator(){
        this.recordMessage("Initializing Mediator");
        DataInterface = new DataFacade(this);
        UIInterface = new UIFacade(this);
    }

    public UIFacade getUI(){
        return UIInterface;
    }

    public DataFacade getData(){
        return DataInterface;
    }

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

    public void endProgram(){
        this.Running = false;
    }

}
