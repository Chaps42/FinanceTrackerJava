package com.finance_tracker.backendlogic;

import java.util.ArrayList;

public abstract class Subject {

    protected ArrayList<Observer> ObserverList = new ArrayList<Observer>();

    //Implements observer pattern by creating the subject.
    //Allows the subject to tell the observer what is going on inside this object

    //Register observer
    protected void registerObserver(Observer obs){
        ObserverList.add(obs);
    }

    protected void removeObserver(Observer obs){
        ObserverList.remove(obs);
    }

    protected void notifyObserver(String Message){
        for(Observer O: ObserverList){
            O.recordMessage(Message);
        }

    }


}
