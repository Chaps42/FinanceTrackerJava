package com.finance_tracker.goal;

import com.finance_tracker.account.Account;

public class Milestone {
    private String goalName;
    private int goalPercent;
    private Account goalAccount;
    private Boolean milestoneStatus;


    public Milestone(String goalName, int goalPercent, Account goalAccount) {
        this.goalName = goalName;
        this.goalPercent = goalPercent;
        this.goalAccount = goalAccount;
    }


    // We need a way to check if a goal was already celebrated, so we don't celebrate 10% AND 20% or the same thing every time the App is open
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
