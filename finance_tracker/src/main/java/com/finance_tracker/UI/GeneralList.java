package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;

import java.io.IOException;

import com.finance_tracker.backendlogic.DataFacade;
import com.finance_tracker.backendlogic.Mediator;

//Abstract class to specifiy common elements of the UI. 
//Implements strategy pattern, as the elements have similar attributes
//but different behavior. Strategy pattern allows access to all objects 
//polymorphically.  USe of polymorphism is seen in the UIFacade
public abstract class GeneralList {
    protected String DisplayString = new String();
    protected UIFacade FacRef;
    protected DataFacade DataRef;
    protected Mediator CentralRef;

    //General constructor to get necassary references
    public GeneralList(UIFacade FacRef){
        this.FacRef = FacRef;
        this.DataRef = this.FacRef.getMediator().getData();
        this.CentralRef = this.FacRef.getMediator();
    }

    //Abstract method to specify the creation of the UI using strings
    abstract protected void BuildString();

    //Abstract method to allow all UI elements to ask for commands. Not used in all methods
    abstract protected void AwaitCommand() throws IOException;

    //Abstract method to acquire the necassary data, changes depending on inheriting class
    abstract protected void RequestData();

    //Common method to easily call the Build and await command from the outside
    public void DisplayString() throws IOException{
        this.BuildString();
        System.out.println(this.DisplayString);
        this.AwaitCommand();
    }



}
