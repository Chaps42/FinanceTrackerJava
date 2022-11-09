package com.finance_tracker.dataTypes.goal;

import java.util.ArrayList;
import java.util.Arrays;

public class Goal {
    private String goalName;
    private double goalValue;
    private Account goalAccount;
    private AbstractCategory goalCategory;
    private Boolean goalStatus;


    void setGoal(double goalValue, Account goalAccount) {

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
