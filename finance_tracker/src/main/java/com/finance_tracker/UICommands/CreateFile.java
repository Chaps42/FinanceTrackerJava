package com.finance_tracker.UICommands;

import com.finance_tracker.backendlogic.Mediator;

public class CreateFile extends Command{
    
    public CreateFile(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 1;
        this.Name = "Create File";

    }

    public void execute(){
        System.out.print("Enter Name: ");
        String Name = this.UserInput.nextLine();
        System.out.println("Enter File Destination: ");
        String Location = this.UserInput.nextLine();



    }
}
