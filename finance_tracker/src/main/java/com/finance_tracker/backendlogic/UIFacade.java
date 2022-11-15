package com.finance_tracker.backendlogic;

public class UIFacade extends Subject {
    Mediator CentralRef;


    public UIFacade(Mediator CRef){
        registerObserver(CRef);
        CentralRef = CRef;


    }
    




}
