package com.finance_tracker.backendlogic;

import java.util.ArrayList;

public abstract class Observer{

    private ArrayList<String> MessageList = new ArrayList<String>();

    public void recordMessage(String S){
        MessageList.add(S);

    }

    public void showMessages(){
        for(String S: MessageList){
            System.out.println(S);
        }

    }

}