package com.finance_tracker.backendlogic;


public class Mediator extends Observer{
    private DataFacade DataInterface;
    private UIFacade UIInterface;

    
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






}
