package com.finance_tracker.math;

import java.lang.Math;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.finance_tracker.account.Account;

import com.finance_tracker.account.InterestEnum;
import com.finance_tracker.account.InterestPeriodEnum;

public class Interest {


    // https://www.javatpoint.com/java-get-current-date
    private Date getCurrentDate() {
        Date date = new Date();
        return date;
    }


    // https://stackabuse.com/how-to-get-the-number-of-days-between-dates-in-java/
    private int getDayDiff(Date dateBefore, Date dateAfter) {
        long dateBeforeInMs = dateBefore.getTime();
        long dateAfterInMs = dateAfter.getTime();

        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
        long daysDiffLong = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        // Doesn't have a TimeUnit for months or years?
        // Four our purposes we can just divide days by 30 or ?
        int daysDiff = (int) daysDiffLong;
        return daysDiff;
    }


    double calculateSimpleInterest(double amountPrincipal, double interestRate, int time) {
        double interest = amountPrincipal * interestRate * time;
        return interest;
    }

    double calculateCompoundInterest(double amountPrincipal, double interestRate, int time) {
        // Simplifying by asserting one compound per time period.
        // A = P(1 + r/n)^nt becomes A = P(1 + r)^t
        double interest = amountPrincipal * Math.pow((1 + interestRate), (time)) - amountPrincipal;
        return interest;
    }
    double calculateContinuousInterest(double amountPrincipal, double interestRate, double time) {
        double interest = amountPrincipal * Math.exp(interestRate * time) - amountPrincipal;
        return interest;
    }

    // A lot of nested if statements for all combinations of interest frequencies and types
    // Should this be a decorator pattern instead?
    double calculateNewInterest(Account account) {
        double interestRate = account.getInterestRate(); // percent
        InterestPeriodEnum interestPeriodEnum = account.getInterestPeriodEnum(); // DAILY, MONTHLY, ANNUAL
        InterestEnum interestEnum = account.getInterestEnum(); // SIMPLE, COMPOUND, CONTINUOUS

        Date today = getCurrentDate();
        Date lastInterestDate = account.getLastInterestDate();
        Date principalInterestDate = account.getAccountRecords().get(0).getDate();
        double amountPrincipal = account.getAccountRecords().get(0).getAmount();

        double newInterest = 0; // need to initialize variable
        if (interestPeriodEnum.equals(InterestPeriodEnum.DAILY)) {
            // Daily Interest
            int totalDaysDiff = getDayDiff(principalInterestDate, today);
            int lastDaysDiff = getDayDiff(lastInterestDate, today);
            if (interestEnum.equals(InterestEnum.SIMPLE)) {
                // Daily Simple
                double totalInterest = calculateSimpleInterest(amountPrincipal, interestRate, totalDaysDiff);
                double lastInterest = calculateSimpleInterest(amountPrincipal, interestRate, lastDaysDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.COMPOUND)) {
                // Daily Compound
                double totalInterest = calculateCompoundInterest(amountPrincipal, interestRate, totalDaysDiff);
                double lastInterest = calculateCompoundInterest(amountPrincipal, interestRate, lastDaysDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.CONTINUOUS)) {
                // Daily Compound
                double totalInterest = calculateContinuousInterest(amountPrincipal, interestRate, totalDaysDiff);
                double lastInterest = calculateContinuousInterest(amountPrincipal, interestRate, lastDaysDiff);
                newInterest = totalInterest - lastInterest;
            }
        } else if (interestPeriodEnum.equals(InterestPeriodEnum.MONTHLY)) {
            // Monthly Interest
            int totalMonthsDiff = getDayDiff(principalInterestDate, today) / 30; // Approximate
            int lastMonthsDiff = getDayDiff(lastInterestDate, today) / 30; // Approximate
            if (interestEnum.equals(InterestEnum.SIMPLE)) {
                // Monthly Simple
                double totalInterest = calculateSimpleInterest(amountPrincipal, interestRate, totalMonthsDiff);
                double lastInterest = calculateSimpleInterest(amountPrincipal, interestRate, lastMonthsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.COMPOUND)) {
                // Monthly Compound
                double totalInterest = calculateCompoundInterest(amountPrincipal, interestRate, totalMonthsDiff);
                double lastInterest = calculateCompoundInterest(amountPrincipal, interestRate, lastMonthsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.CONTINUOUS)) {
                // Daily Compound
                double totalInterest = calculateContinuousInterest(amountPrincipal, interestRate, totalMonthsDiff);
                double lastInterest = calculateContinuousInterest(amountPrincipal, interestRate, lastMonthsDiff);
                newInterest = totalInterest - lastInterest;
            }
        } else if (interestPeriodEnum.equals(InterestPeriodEnum.ANNUAL)) {
            // Annual Interest
            int totalYearsDiff = getDayDiff(principalInterestDate, today) / 365; // Approximate
            int lastYearsDiff = getDayDiff(lastInterestDate, today) / 365; // Approximate
            if (interestEnum.equals(InterestEnum.SIMPLE)) {
                // Annual Simple
                double totalInterest = calculateSimpleInterest(amountPrincipal, interestRate, totalYearsDiff);
                double lastInterest = calculateSimpleInterest(amountPrincipal, interestRate, lastYearsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.COMPOUND)) {
                // Annual Compound
                double totalInterest = calculateCompoundInterest(amountPrincipal, interestRate, totalYearsDiff);
                double lastInterest = calculateCompoundInterest(amountPrincipal, interestRate, lastYearsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.CONTINUOUS)) {
                // Annual Compound
                double totalInterest = calculateContinuousInterest(amountPrincipal, interestRate, totalYearsDiff);
                double lastInterest = calculateContinuousInterest(amountPrincipal, interestRate, lastYearsDiff);
                newInterest = totalInterest - lastInterest;
            }
        }
        return newInterest;
    }

    double calculateAmountFinal(Account account) {
        double newInterest = calculateNewInterest(account);
        double amountFinal = account.getValue() + newInterest;
        return amountFinal;
    }
}
