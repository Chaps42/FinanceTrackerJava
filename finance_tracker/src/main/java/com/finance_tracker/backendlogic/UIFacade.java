package com.finance_tracker.backendlogic;

public class UIFacade extends Subject {
    Mediator CentralRef;


    public UIFacade(Mediator CRef){
        registerObserver(CRef);
        CentralRef = CRef;
    }
    
    public void updateUI(){
        CentralRef.getData().getAllAccounts();
        CentralRef.getData().getAllOneTimeTransactions();
        CentralRef.getData().getAllCategories();


        notifyObserver("UI Updated");
    }




}
