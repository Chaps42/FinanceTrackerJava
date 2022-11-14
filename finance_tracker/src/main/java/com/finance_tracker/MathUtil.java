package com.finance_tracker;

import java.lang.Math;

import com.finance_tracker.account.Account;

public class MathUtil {
    
    // Not sure how we want to handle time yet
    double calculateInterest(Account account, Time time) {
        double ammountPrincipal = account.getValue();
        double interestRate = account.getInterestRate();

        InerestPeriodEnum interestPeriodEnum = account.getInterestPeriodEnum();
        if (interestPeriodEnum == DAILY) {
            // handle interest rate and calculation differently for different periods
            // break time past into increments of days
        } else if (interstPeriodEnum == MONTHLY) {

        } else if (interestPeriodEnum == ANNUAL) {

        }


        InerestEnum interestEnum = account.getInterestEnum();
        double interest;
        if (interestEnum == SIMPLE) {
            interest = calculateSimpleInterest(amountPrincipal, interestRate, time);
        } else if (interstEnum == COMPOUND) {
            interest = calculateCompoundInterest();
        } else if (interestEnum == CONTINUOUS) {
            interest = calculateContinuousInterest();
        }
    }

    double calculateSimpleInterest(double amountPrincipal, double interestRate, double time) {
        double interest = amountPrincipal * interestRate * time; // adjusted for daily, monthly, annually
        return interest;
    }

    double calculateCompoundInterest(double amountPrincipal, double interestRate, double time, int n) {
        double amountFinal = amountPrincipal * Math.pow((1 + interestRate / n), (n * time));
        double interest = amountFinal - amountPrincipal;
        return interest;
    }

    double calculateContinuousInterest(double amountPrincipal, double interestRate, double time) {
        double amountFinal = amountPrincipal * Math.exp(interestRate * time)
    }

    double calculateAmountFinal(double amountPrincipal, double interest) {
        double amountFinal = amountPrincipal + interest;
        return amountFinal;
    }
}
