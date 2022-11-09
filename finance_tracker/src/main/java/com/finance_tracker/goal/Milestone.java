package com.finance_tracker.dataTypes.goal;

import com.finance_tracker.dataTypes.goal.Celebration;

public class Milestone {
    private String goalName;
    private int goalPercent;
    private Account goalAccount;
    private Boolean milestoneStatus;


    void checkCelebrate () {
        if (milestoneStatus) {
            Celebration.celebrate(this);
        }
    }


    public String getGoalName() {
        return goalName;
    }


    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }


    public int getGoalPercent() {
        return goalPercent;
    }


    public void setGoalPercent(int goalPercent) {
        this.goalPercent = goalPercent;
    }


    public Account getGoalAccount() {
        return goalAccount;
    }


    public void setGoalAccount(Account goalAccount) {
        this.goalAccount = goalAccount;
    }


    public Boolean getMilestoneStatus() {
        return milestoneStatus;
    }


    public void setMilestoneStatus(Boolean milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }
}
