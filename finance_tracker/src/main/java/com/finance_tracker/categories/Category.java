package com.finance_tracker.categories;

import java.util.HashMap;
import java.util.Date;

public class Category {
    private String Name;
    private CategoryEnum Type;
    private HashMap<Date,Double> Values = new HashMap<Date,Double>();

    public String getName(){
        return this.Name;
    }

    public CategoryEnum getType(){
        return this.Type;
    }

    public HashMap<Date,Double> getValues(){
        return this.Values;
    }

    public void recordValue(Date D, Double Value){
        this.Values.put(D,Value);
    }

    public void deleteValue(Date D){
        this.Values.remove(D);
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void setType(CategoryEnum E){
        this.Type = E;
    }

}
