package com.finance_tracker.backendlogic;

import java.util.ArrayList;

public abstract class Observer{

    private ArrayList<String> MessageList = new ArrayList<String>();

    //Implements the Observer pattern to create an observer that is notified of all the funciton calls in the system
    //Useful for debugging
    public void recordMessage(String S){
        MessageList.add(S);

    }

    //Shows new messages for debugging
    public void showMessages(){
        for(String S: MessageList){
            System.out.println(S);
        }

    }

}