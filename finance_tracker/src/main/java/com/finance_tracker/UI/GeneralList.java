package com.finance_tracker.UI;

import com.finance_tracker.backendlogic.UIFacade;


public abstract class GeneralList {
    private String DisplayString = new String();
    private UIFacade FacRef;

    public GeneralList(UIFacade FacRef){
        this.FacRef = FacRef;
    }

    abstract public void BuildString();

    abstract public void AwaitCommand();

    abstract public void RequestData();

    public void DisplayString(){
        System.out.println(this.DisplayString);
    }


}
