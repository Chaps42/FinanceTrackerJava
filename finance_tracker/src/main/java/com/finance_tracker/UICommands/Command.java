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

    public Command(Mediator CentralRef){
        this.CentralRef = CentralRef;
        this.DataRef = this.CentralRef.getData();
        this.FileRef = this.DataRef.getFileManager();
        this.UserInput = CentralRef.getScanner();

    }

    public abstract void execute() throws IOException;

    public String getName(){
        return this.Name;
    }

    public Integer getNumber(){
        return CodeNum;
    }
}
