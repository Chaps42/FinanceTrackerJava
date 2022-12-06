package com.finance_tracker.backendlogic;


public class Mediator extends Observer{
    private DataFacade DataInterface;
    private UIFacade UIInterface;
    private boolean Running = true;

    
    public Mediator(){
        this.recordMessage("Initializing Mediator");
        DataInterface = new DataFacade(this);
        UIInterface = new UIFacade(this);

        this.run();
    }

    public UIFacade getUI(){
        return UIInterface;
    }

    public DataFacade getData(){
        return DataInterface;
    }

    public void run(){
        UIInterface.showInitialization();
        while (this.Running==true){
            UIInterface.updateUI();
        }
    }

    public void endProgram(){
        this.Running = false;
    }

}
