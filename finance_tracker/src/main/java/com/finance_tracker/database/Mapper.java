package com.finance_tracker.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountHelper;
import com.finance_tracker.account.AccountBuilder;
import com.finance_tracker.account.AccountRecord;

import com.finance_tracker.transaction.TransactionHelper;
import com.finance_tracker.transaction.TransactionBuilder;
import com.finance_tracker.transaction.Transaction;

public class Mapper {
    //get() {}
    //search() {}
    //add() {}
    //remove() {}

    // reads 1 account
    public static Account readAccount(Path accountPath) throws IOException {
        try (Reader reader = Files.newBufferedReader(accountPath);) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> accountData = csvReader.readAll();
            csvReader.close();

            // Read account name and type
            String name = accountData.get(1)[0];

            String accountType = accountData.get(1)[0];
            AccountEnum accountEnum = AccountEnum.valueOf(accountType); // not sure why this enum can't be found

            // Read account interest info
            String interestRateStr = accountData.get(5)[0];
            double interestRate = Double.parseDouble(interestRateStr);

            String interestPeriod = accountData.get(5)[1];
            InterestPeriodEnum interestPeriodEnum = InterestPeriodEnum.valueOf(interestPeriod);

            String interestType = accountData.get(5)[2];
            InterestTypeEnum interestTypeEnum = InterestTypeEnum.valueOf(interestType);

            // Read account reccords
            int endIndex = accountData.size();
            ArrayList<AccountRecord> accountRecords = new ArrayList<AccountRecord>();
            for(int i = 9; i < endIndex; i++) {
                String dateStr = accountData.get(i)[0];
                SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
                Date date = format.parse(dateStr);

                String valueStr = accountData.get(i)[1];
                double value = Double.parseDouble(valueStr);

                AccountRecord accountRecord = new AccountRecord(date, value);
                accountRecords.add(accountRecord);
            }

            // Build an account
            AccountBuilder accountBuilder = new AccountBuilder(name, accountRecords)
                .setAccountEnum(accountEnum)
                .setInterestRate(interestRate)
                .setInterestPeriodEnum(interestPeriodEnum)
                .setInterstTypeEnum(interestTypeEnum);

            Account account = accountBuilder.buildAccount();
            return account;
        }
    }

    // Method that looks through all account files in the path and builds each and adds to database
    public static Account readAllAccounts(String accountFolder) throws IOException {
        // Set path to "/user_data/accounts" in call to Mapper
        Path[] accountPaths = accountFolder.list(); // this won't work 
        //- need to do something different to get a list of files in directory. This is dummmy placeholder code.
        HashMap<String, Account> accounts = new HashMap<String, Account>();
        for (Path accountPath: accountPaths) {
            Account account = readAccount(accountPath);
            accounts.put(account.getName(), account);
        }
        Database.setAccounts(accounts);
    }


    public static void readTransaction(Path transactionPath) throws IOException {
        try (Reader reader = Files.newBufferedReader(transactionPath)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> transactionData = csvReader.readAll();
            csvReader.close();

            HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();
            int endIndex = transactionData.size();
            for (int i = 1; i < endIndex; i++) {
                String dateStr = transactionData.get(i)[0];
                SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
                Date date = format.parse(dateStr);

                String name = transactionData.get(i)[1];

                String valueStr = transactionData.get(i)[2];
                double value = Double.parseDouble(valueStr);

                String accountStr = transactionData.get(i)[3];
                Account account = Database.findAccount(accountStr);

                String categoryStr = transactionData.get(i)[4];
                CategoryEnum category = CategoryEnum.valueOf(categoryStr);

                String transactionEnumStr = transactionData.get(i)[5];
                TransactionEnum transactionEnum = TransactionEnum.valueOf(transactionEnumStr);

                String freqStr = transactionData.get(i)[6];
                TransactionFrequencyEnum frequency = TransactionFrequencyEnum.valueOf(freqStr);

                // Build each transaction
                // This makes me want to leverage Builder pattern more and move some of these attributes out, even though they are necessary
                TransactionBuilder transactionBuilder = new TransactionBuilder(name, transactionEnum, value, date, account)
                    .setFrequencyEnum(frequency)
                    .setCategory(category);

                Transaction transaction = transactionBuilder.buildTransaction();
                transactions.put(transaction.getName(), transaction);
            }
            Database.setTransactions(transactions);
        }
    }

    // Sample method for writing to a CSV file
    // Using this resource: https://www.baeldung.com/opencsv
    public static void writeDataLineByLine(String filePath) {
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
    
            String[] data1 = { "a", "b", "c" };
            writer.writeNext(data1);
    
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAccount() {}
    public static void writeTransaction() {}
}
