package com.finance_tracker.account;

// Thinking of adding an interest class because if your account has interest
// You need to have all 3 of these attributes: (none can be null or all will be)
// Current builder pattern allows any combination of these to be null,
// Somehow having a separate class could make this more robust.
public class Interest {
    private double interestRate;
    private InterestEnum interestEnum;
    private InterestPeriodEnum interestPeriodEnum;
}
