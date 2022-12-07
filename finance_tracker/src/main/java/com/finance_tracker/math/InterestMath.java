package com.finance_tracker.math;

import java.util.Date;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.account.InterestEnum;
import com.finance_tracker.account.InterestPeriodEnum;
import com.finance_tracker.database.Mapper;


public class InterestMath {


    /**
     * Constructor for the InterstMath utility class.
     */
    public InterestMath() {}


    /**
     * @param amountPrincipal double
     * @param interestRate double
     * @param time int
     * @return double
     *
     * Method for calculating simple interest.
     * Formula:
     * I = P r t
     * Interest = Principal * interest rate * time
     */
    private double calculateSimpleInterest(double amountPrincipal,
        double interestRate,
        int time) {
            double interest = amountPrincipal * interestRate * time;
            return interest;
    }


    /**
     * @param amountPrincipal double
     * @param interestRate double
     * @param time int
     * @return double
     *
     * Method for calculating compound interest.
     * Assumes one compound per time period.
     * Formula
     * I = P(1 + r)^t - P
     * Interest = Principal * (1 + rate)^time - Principal
     */
    private double calculateCompoundInterest(double amountPrincipal,
        double interestRate,
        int time) {
            double interest = amountPrincipal
                * Math.pow((1 + interestRate), (time))
                - amountPrincipal;
            return interest;
    }


    /**
     * @param amountPrincipal double
     * @param interestRate double
     * @param time int
     * @return double
     *
     * Method for calculating continuous interst.
     * Formula:
     * I = P e^(rt) - P
     * Interest = Principal * e^(rate * time) - Principal
     */
    private double calculateContinuousInterest(double amountPrincipal,
        double interestRate,
        double time) {
            double interest = amountPrincipal
                * Math.exp(interestRate * time)
                - amountPrincipal;
            return interest;
    }


    /**
     * @param account Account
     * @return double
     *
     * Calculates the interst earned on an Account since interst was last
     * calculated by calculating total interst to date and previous interest,
     * and returning the difference.
     * This method calculates Daily, Monthly, and Annual 
     * Simple, Compound, and Continuous Interest.
     */
    private double calculateNewInterest(Account account) {
        double interestRate = account.getInterestRate(); // percent
        InterestPeriodEnum interestPeriodEnum =
            account.getInterestPeriodEnum();
        InterestEnum interestEnum = account.getInterestEnum();

        DateMath datemath = new DateMath();
        Date today = datemath.getCurrentDate();
        Date lastInterestDate = account.getLastInterestDate();
        Date principalInterestDate =
            account.getPrincipleDate();
        double amountPrincipal =
            account.getAccountRecords().get(0).getAmount();

        double newInterest = 0; // need to initialize variable
        if (interestPeriodEnum.equals(InterestPeriodEnum.DAILY)) {
            // Daily Interest
            int totalDaysDiff =
                datemath.getDayDiff(principalInterestDate, today);
            int lastDaysDiff = datemath.getDayDiff(principalInterestDate,lastInterestDate);
            if (interestEnum.equals(InterestEnum.SIMPLE)) {
                // Daily Simple
                double totalInterest = calculateSimpleInterest(amountPrincipal,
                    interestRate, totalDaysDiff);
                double lastInterest = calculateSimpleInterest(amountPrincipal,
                    interestRate, lastDaysDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.COMPOUND)) {
                // Daily Compound
                double totalInterest = calculateCompoundInterest(amountPrincipal,
                    interestRate, totalDaysDiff);
                double lastInterest = calculateCompoundInterest(amountPrincipal,
                    interestRate, lastDaysDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.CONTINUOUS)) {
                // Daily Compound
                double totalInterest = calculateContinuousInterest(amountPrincipal,
                    interestRate, totalDaysDiff);
                double lastInterest = calculateContinuousInterest(amountPrincipal,
                    interestRate, lastDaysDiff);
                newInterest = totalInterest - lastInterest;
            }
        } else if (interestPeriodEnum.equals(InterestPeriodEnum.MONTHLY)) {
            // Monthly Interest
            // Approximating months to be 30 days long
            int totalMonthsDiff =
                datemath.getDayDiff(principalInterestDate, today) / 30;
            int lastMonthsDiff =
                datemath.getDayDiff(principalInterestDate,lastInterestDate) / 30;
            if (interestEnum.equals(InterestEnum.SIMPLE)) {
                // Monthly Simple
                double totalInterest = calculateSimpleInterest(amountPrincipal,
                    interestRate, totalMonthsDiff);
                double lastInterest = calculateSimpleInterest(amountPrincipal,
                    interestRate, lastMonthsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.COMPOUND)) {
                // Monthly Compound
                double totalInterest = calculateCompoundInterest(amountPrincipal,
                    interestRate, totalMonthsDiff);
                double lastInterest = calculateCompoundInterest(amountPrincipal,
                    interestRate, lastMonthsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.CONTINUOUS)) {
                // Monthly Continuous
                double totalInterest = calculateContinuousInterest(amountPrincipal,
                    interestRate, totalMonthsDiff);
                double lastInterest = calculateContinuousInterest(amountPrincipal,
                    interestRate, lastMonthsDiff);
                newInterest = totalInterest - lastInterest;
            }
        } else if (interestPeriodEnum.equals(InterestPeriodEnum.ANNUAL)) {
            // Annual Interest
            // Assumes years are 365 days long
            int totalYearsDiff =
                datemath.getDayDiff(principalInterestDate, today) / 365;
            int lastYearsDiff =
                datemath.getDayDiff(principalInterestDate,lastInterestDate) / 365;
            if (interestEnum.equals(InterestEnum.SIMPLE)) {
                // Annual Simple
                double totalInterest = calculateSimpleInterest(amountPrincipal,
                    interestRate, totalYearsDiff);
                double lastInterest = calculateSimpleInterest(amountPrincipal,
                    interestRate, lastYearsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.COMPOUND)) {
                // Annual Compound
                double totalInterest = calculateCompoundInterest(amountPrincipal,
                    interestRate, totalYearsDiff);
                double lastInterest = calculateCompoundInterest(amountPrincipal,
                    interestRate, lastYearsDiff);
                newInterest = totalInterest - lastInterest;
            } else if (interestEnum.equals(InterestEnum.CONTINUOUS)) {
                // Annual Continuous
                double totalInterest = calculateContinuousInterest(amountPrincipal,
                    interestRate, totalYearsDiff);
                double lastInterest = calculateContinuousInterest(amountPrincipal,
                    interestRate, lastYearsDiff);
                newInterest = totalInterest - lastInterest;
            }
        }
        return newInterest;
    }


    /**
     * @param account Account
     * @return double
     *
     * This method calculates the new Account value after interest.
     */
    private double calculateAmountFinal(Account account) {
        double newInterest = calculateNewInterest(account);
        double amountFinal = account.getValue() + newInterest;
        return amountFinal;
    }


    /**
     * @param account Account
     *
     * This method adds a new AccountRecord to the Account with its interst
     * amount, and updates the attribute for lastInterestDate.
     */
    private void applyInterest(Account account) {
        double amountFinal = calculateAmountFinal(account);
        DateMath dateMath = new DateMath();
        Date currentDate = dateMath.getCurrentDate();

        AccountRecord newRecord = new AccountRecord(currentDate, amountFinal);
        account.addRecord(newRecord);
        account.setLastInterestDate(currentDate);
    }


    /**
     * This method applies interest to all accounts.
     * It checks to see if each Account has interest values first.
     */
    public void applyAllInterest() {
        Mapper databaseMapper = Mapper.getInstance();
        HashMap<String, Account> accounts = databaseMapper.getAccounts();
        for (Account a: accounts.values()) {
            if (a.getInterestEnum() != InterestEnum.NONE) {
                applyInterest(a);
            }
        }
    } 
}
