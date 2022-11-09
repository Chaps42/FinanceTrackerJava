package com.finance_tracker.dataTypes;

import java.util.Date;

// Adding a date class is a change from the UML
// We need to decide how we want the user to be able to edit
// transaction dates, or auto-grab the current date
// and how to format the dates
// YYYY-MM-DD ?
public class Date {
    

    /**
     * @return Date
     *
     * TutorialsPoint "How to create date objects in Java?"
     * https://www.tutorialspoint.com/how-to-create-date-object-in-java
     */
    Date CreateDate() {
        Date date = new Date();
        return date;
    }
}
