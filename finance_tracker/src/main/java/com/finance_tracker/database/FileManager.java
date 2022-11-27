package com.finance_tracker.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountBuilder;
import com.finance_tracker.account.AccountEnum;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.account.InterestEnum;
import com.finance_tracker.account.InterestPeriodEnum;
import com.finance_tracker.transaction.TransactionBuilder;
import com.finance_tracker.transaction.TransactionEnum;
import com.finance_tracker.transaction.TransactionFrequencyEnum;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;

public class FileManager {


    // reads 1 account
    public static Account readAccount(Path accountPath) throws IOException, ParseException {
        try (BufferedReader reader = Files.newBufferedReader(accountPath);) {
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
            InterestEnum interestTypeEnum = InterestEnum.valueOf(interestType);

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
                .setInterestEnum(interestTypeEnum);

            Account account = accountBuilder.buildAccount();
            return account;
        }
    }


    // Method that looks through all account files in the path and builds each and adds to database
    public static void readAllAccounts() throws IOException, ParseException {
        File accountFolder = new File("/user_data/accounts");
        File[] accountFiles = accountFolder.listFiles();

        HashMap<String, Account> accounts = new HashMap<String, Account>();
        for (File f: accountFiles) {
            Path accountPath = f.toPath();
            Account account = readAccount(accountPath);
            accounts.put(account.getName(), account);
        }
        Mapper databaseMapper = Mapper.getInstance();
        databaseMapper.setAccounts(accounts);
    }


    public static void readTransactions() throws IOException, ParseException {
        Path transactionPath = Paths.get("/user_data/transactions.csv");
        try (BufferedReader reader = Files.newBufferedReader(transactionPath)) {
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
                Mapper databaseMapper = Mapper.getInstance();
                Account account = databaseMapper.findAccount(accountStr); // need to have read all accounts before transactions

                String categoryStr = transactionData.get(i)[4];
                CategoryEnum category = CategoryEnum.valueOf(categoryStr);

                String transactionEnumStr = transactionData.get(i)[5];
                TransactionEnum transactionEnum = TransactionEnum.valueOf(transactionEnumStr);

                String freqStr = transactionData.get(i)[6];
                TransactionFrequencyEnum frequency = TransactionFrequencyEnum.valueOf(freqStr);

                // Build each transaction
                // This makes me want to leverage Builder pattern more and move some of these attributes out, even though they are necessary
                TransactionBuilder transactionBuilder = new TransactionBuilder(name, transactionEnum, value, date, account)
                    .setFrequency(frequency)
                    .setCategory(category);

                Transaction transaction = transactionBuilder.buildTransaction();
                transactions.put(transaction.getName(), transaction);
            }
            Mapper databaseMapper = Mapper.getInstance();
            databaseMapper.setTransactions(transactions);
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


    public static void writeAccount(Account account) {
        // make it so contents of file are deleted before new save
        // this is easiest way to update any attribute or line without error
        String rootPath = "/user_data/accounts/";
        String name = account.getName();
        String filePath = rootPath + name;
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            String[] line1 = { "NAME", "TYPE"};
            writer.writeNext(line1);
            String[] line2 = {name, account.getAccountEnum().toString()};
            writer.writeNext(line2);
            String[] blankLine = {};
            writer.writeNext(blankLine);

            String[] line4 = {"Interest:"};
            writer.writeNext(line4);
            String[] line5 = {"PERCENT", "FREQ", "TYPE"};
            writer.writeNext(line5);
            String[] line6 = {String.valueOf(account.getInterestRate()), 
                account.getInterestPeriodEnum().toString(),
                account.getInterestEnum().toString()};
            writer.writeNext(line6);
            writer.writeNext(blankLine);

            String[] line8 = {"Account Records:"};
            writer.writeNext(line8);
            String[] line9 = {"DATE", "AMOUNT"};
            writer.writeNext(line9);
            ArrayList<AccountRecord> accountRecords = account.getAccountRecords();
            for (AccountRecord r: accountRecords) {
                String[] line = {r.getDate().toString(), String.valueOf(r.getAmount())};
                writer.writeNext(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeTransactions() {
        // make it so contents of file are deleted before new save
        // this is easiest way to update any attribute or line without error
        String filePath = "/user_data/transactions.csv";
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            String[] line1 = {"DATE", "NAME", "VALUE", "ACCOUNT", "CATEGORY", "ENUM (ONETIME or REPEATING)", "FREQ"};
            writer.writeNext(line1);

            Mapper databaseMapper = Mapper.getInstance();
            HashMap<String, Transaction> transactions = databaseMapper.getTransactions(); // from mapper instead, mapper only class to interact with database
            for (Transaction t: transactions.values()) {
                String[] line = {t.getDate().toString(),
                    t.getName(),
                    String.valueOf(t.getValue()),
                    t.getTransactionAccount().toString(),
                    t.getCategory().toString(),
                    t.getTransactionEnum().toString(),
                    t.getFrequency().toString()
                };
                writer.writeNext(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void delAccountFile(String name) {
        String rootPath = "/user_data/accounts/";
        String filePath = rootPath + name;
        File f = new File(filePath);  
        f.delete();
    }


    public void readAll() throws IOException, ParseException {
        // On Start up
        readAllAccounts();
        readTransactions();
    }
}
