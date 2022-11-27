package com.finance_tracker;

import java.lang.Math;

import java.util.Date;
import com.finance_tracker.account.Account;

import com.finance_tracker.account.InterestEnum;
import com.finance_tracker.account.InterestPeriodEnum;

public class MathUtil {
    
    // Not sure how we want to handle time yet
    double calculateInterest(Account account, Date date) {
        double ammountPrincipal = account.getValue();
        double interestRate = account.getInterestRate();

        InterestPeriodEnum interestPeriodEnum = account.getInterestPeriodEnum();
        if (interestPeriodEnum == DAILY) {
            // handle interest rate and calculation differently for different periods
            // break time past into increments of days
        } else if (interestPeriodEnum == MONTHLY) {

        } else if (interestPeriodEnum == ANNUAL) {

        }


        InterestEnum interestEnum = account.getInterestEnum();
        double interest;
        if (interestEnum == SIMPLE) {
            interest = calculateSimpleInterest(amountPrincipal, interestRate, time);
        } else if (interestEnum == COMPOUND) {
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
        double amountFinal = amountPrincipal * Math.exp(interestRate * time);
        double interest = amountFinal - amountPrincipal;
        return interest;
    }

    double calculateAmountFinal(double amountPrincipal, double interest) {
        double amountFinal = amountPrincipal + interest;
        return amountFinal;
    }
}
