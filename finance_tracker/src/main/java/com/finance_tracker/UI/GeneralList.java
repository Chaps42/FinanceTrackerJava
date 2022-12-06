package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.backendlogic.DataFacade;
import com.finance_tracker.backendlogic.Mediator;


public abstract class GeneralList {
    protected String DisplayString = new String();
    protected UIFacade FacRef;
    protected DataFacade DataRef;
    protected Mediator CentralRef;

    public GeneralList(UIFacade FacRef){
        this.FacRef = FacRef;
        this.DataRef = this.FacRef.getMediator().getData();
        this.CentralRef = this.FacRef.getMediator();
    }

    abstract protected void BuildString();

    abstract protected void AwaitCommand();

    abstract protected void RequestData();

    public void DisplayString(){
        this.BuildString();
        System.out.println(this.DisplayString);
        this.AwaitCommand();
    }



}
