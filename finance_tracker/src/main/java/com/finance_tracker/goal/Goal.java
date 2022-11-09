package com.finance_tracker.goal;

// This is low priority. A "nice to have"
// Will work on after everything else is in place.
// Since we don't need different types of kwargs for our goals, does Builder pattern even make sense here?

import java.util.ArrayList;
import java.util.Arrays;

import com.finance_tracker.account.Account;

public class Goal {
    private String goalName;
    private double goalValue;
    private Account goalAccount;
    private Boolean goalStatus;


    void setGoal(String goalName, double goalValue, Account goalAccount) {
        this.goalName = goalName;
        this.goalValue = goalValue;
        this.goalAccount = goalAccount;
    }


    /**
     * When goal is created, create Milestones.
     */
    void generateMilestones() {
        ArrayList<Integer> percentageList = new ArrayList<Integer>(Arrays.asList(10, 20, 25, 30, 40, 50, 60, 70, 75, 80, 90, 100));
        for (int percent: percentageList) {
            Milestone milestone = new Milestone(this.goalName, percent, this.goalAccount);
        }
    }
}
