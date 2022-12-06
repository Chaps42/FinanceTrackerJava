package com.finance_tracker.math;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.transaction.Transaction;
import com.finance_tracker.transaction.TransactionBuilder;
import com.finance_tracker.transaction.TransactionFrequencyEnum;


public class RecurringTransactionMath {


    /**
     * Constructor for the RecurringTransactionMath utility class.
     */
    public RecurringTransactionMath() {}


    /**
     * @param transaction Transaction
     * @return Date
     *
     * For a Transaction, calculates the next Date of its recurrance.
     */
    private Date calculateNextTransactionDate(Transaction transaction) {
        TransactionFrequencyEnum frequency = transaction.getFrequency();

        Date lastTransactionDate = transaction.getDate();
        LocalDate lastTransactionLocalDate =
            Instant.ofEpochMilli(lastTransactionDate.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        LocalDate nextTransactionLocalDate = null;
        if (frequency.equals(TransactionFrequencyEnum.WEEKLY)) {
            nextTransactionLocalDate = lastTransactionLocalDate.plusWeeks(1);
        } else if (frequency.equals(TransactionFrequencyEnum.MONTHLY)) {
            nextTransactionLocalDate = lastTransactionLocalDate.plusMonths(1);
        } else if (frequency.equals(TransactionFrequencyEnum.ANNUALLY)) {
            nextTransactionLocalDate = lastTransactionLocalDate.plusYears(1);
        }
        Date nextTransactionDate = Date.from(nextTransactionLocalDate.atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant());
        return nextTransactionDate;
    }


    /**
     * @param transaction Transaction
     *
     * This method creates a new Transaction for each Transaction recurrance.
     * It first checks if the Transaction has already been recurred or if it
     * needs to be recurred multiple times since last log-in.
     */
    private void applyRecurrance(Transaction transaction) {
        DateMath dateMath = new DateMath();
        Date currentDate = dateMath.getCurrentDate();
        Date lastUpdateDate = dateMath.getLastUpdateDate();

        Date nextTransactionDate = calculateNextTransactionDate(transaction);
        while (nextTransactionDate.after(lastUpdateDate)
            & !nextTransactionDate.after(currentDate)) {
                // Use a while loop since it might have to recur multiple times
                // recurrs monthly and it has been 3 months since log-in, e.g.
                //
                // Check to see if recurrance date is after last update
                // (don't repeat recurrance of a transaction)
                // and before the current date.
                TransactionBuilder transactionBuilder = new TransactionBuilder(
                        transaction.getName(),
                        transaction.getTransactionEnum(),
                        transaction.getValue(),
                        nextTransactionDate,
                        transaction.getTransactionAccount())
                    .setFrequency(transaction.getFrequency())
                    .setCategory(transaction.getCategory());
                
                Transaction newTransaction =
                    transactionBuilder.buildTransaction();

                Mapper databaseMapper = Mapper.getInstance();
                databaseMapper.addTransaction(newTransaction);

                // check if we have to create multiple new transactions
                // due to long time between log in
                nextTransactionDate =
                    calculateNextTransactionDate(newTransaction);
        }
    }


    /**
     * This method looks through a list of all recurring Transactions, and
     * recurrs them if necessary.
     */
    public void recurTransactions() {
        Mapper databaseMapper = Mapper.getInstance();
        ArrayList<Transaction> recurringTransactions =
            databaseMapper.getRecurringTransactions();
        for (Transaction t: recurringTransactions) {
            applyRecurrance(t);
        }
    }
}
