package com.finance_tracker.goal;

public class Celebration {


    /**
     * Some GUI pop up celebration.
     */
    static void celebrate(Milestone milestone) {
        String celebrationstring = "Congratulations! You have saved " + milestone.getPercent() + " to your goal " + milestone.getGoalName() + "!";
    }
}
