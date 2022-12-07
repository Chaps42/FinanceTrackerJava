package com.finance_tracker.UICommands;

import java.io.IOException;
import java.util.Scanner;

import com.finance_tracker.database.FileManager;
import com.finance_tracker.backendlogic.DataFacade;
import com.finance_tracker.backendlogic.Mediator;

public abstract class Command {
    protected Mediator CentralRef;
    protected Scanner UserInput;
    protected FileManager FileRef;
    protected DataFacade DataRef;
    protected String Name;
    protected Integer CodeNum;

    //Command constructor to give it all the necassary object references. 
    //Implements command pattern so that all commands can have the same basic structure and references. 
    //Client (User interface objects) invoke a command that acts on the mediator to send a message to 
    //the rest of the system, wherever the destination may be
    public Command(Mediator CentralRef){
        this.CentralRef = CentralRef;
        this.DataRef = this.CentralRef.getData();
        this.FileRef = this.DataRef.getFileManager();
        this.UserInput = CentralRef.getScanner();

    }

    //Abstract execute to command can be acted on like a remote
    public abstract void execute() throws IOException;

    //Common Getter functions
    public String getName(){
        return this.Name;
    }

    public Integer getNumber(){
        return CodeNum;
    }
}
