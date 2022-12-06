package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;
import com.finance_tracker.backendlogic.DataFacade;


public abstract class GeneralList {
    protected String DisplayString = new String();
    protected UIFacade FacRef;
    protected DataFacade DataRef;

    public GeneralList(UIFacade FacRef){
        this.FacRef = FacRef;
        this.DataRef = this.FacRef.getMediator().getData();
    }

    abstract protected void BuildString();

    abstract protected void AwaitCommand();

    abstract protected void RequestData();

    public void DisplayString(){
        this.BuildString();
        System.out.println(this.DisplayString);
        System.out.println();
        this.AwaitCommand();
    }



}
