package com.finance_tracker.math;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.Transaction;


public class DateMath {


    /**
     * Constructor for the DateMath utility class.
     */
    public DateMath() {}



    /**
     * @return Date
     *
     * Gets the current date.
     *
     * Learned how from:
     * https://www.javatpoint.com/java-get-current-date
     */
    protected Date getCurrentDate() {
        Date date = new Date();
        return date;
    }


    /**
     * @param dateBefore Date
     * @param dateAfter Date
     * @return int
     *
     * Returns the number of days between two dates.
     *
     * Learned how from:
     * https://stackabuse.com/how-to-get-the-number-of-days-between-dates-in-java/
     */
    protected int getDayDiff(Date dateBefore, Date dateAfter) {
        long dateBeforeInMs = dateBefore.getTime();
        long dateAfterInMs = dateAfter.getTime();
    
        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
        long daysDiffLong =
            TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        // Was hoping this method had a TimeUnit.MONTHS or .YEARS, but alas
        // Four our purposes we can will divide days by 30 or 365 for month/year
        // Proof of concept, rather than the nitty gritty of date math.
        int daysDiff = (int) daysDiffLong;
        return daysDiff;
    }


    /**
     * @return Date
     *
     * Returns the date of the last Transaction.
     */
    protected Date getLastRecurringTransactionDate() {
        Mapper databaseMapper = Mapper.getInstance();
        ArrayList<Transaction>  transactions = databaseMapper.getRecurringTransactions();

        // Use TreeMap to sort Transactions by Date
        // Otherwise creating a blank Date to compare values to
        // Was always today instead of the last Date.
        TreeMap<Date, Transaction> sortedMap = new TreeMap<Date, Transaction>();
        for (Transaction t: transactions) {
            Date date = t.getDate();
            sortedMap.put(date, t);
        }
        return sortedMap.lastKey();
    }
}
