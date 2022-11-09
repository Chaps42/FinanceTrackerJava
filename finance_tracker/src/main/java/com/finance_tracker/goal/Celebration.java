package com.finance_tracker.dataTypes.goal;

public class Celebration {


    /**
     * Some GUI pop up celebration.
     */
    void celebrate(Milestone milestone) {
        String celebrationstring = "Congratulations! You have saved " + milestone.getPercent() + " to your goal " + milestone.getGoalName() + "!";
    }
}
