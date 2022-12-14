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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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


// Singleton Pattern was used for the File Manager because only one of this
// utility object is needed.
public class FileManager {
    static SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
    // Create a single object for lazy Singleton pattern
    private static FileManager instance;


    /**
     * Constructor for the FileManager.
     *
     * Making constructor private so that this class cannot be
     * instantiated.
     */
    private FileManager() {}


    /**
     * @return FileManager
     *
     * Gets the only FileManager.
     * Lazy Singleton.
     */
    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }


    /**
     * Initializes the FileManager
     *
     * Necessary for Singleton Pattern becasue FileManager cannot have parameters,
     * but we want to pass certain information into it.
     */
    public void initializeFileManager() {}


    /**
     * @param accountPath Path
     * @return Account
     * @throws IOException
     * @throws ParseException
     *
     * This method reads an Account file, parses it, and builds the Account.
     */
    private static Account readAccount(Path accountPath)
        throws IOException, ParseException {
            try (BufferedReader reader =
                Files.newBufferedReader(accountPath)) {
                    CSVReader csvReader = new CSVReader(reader);
                    List<String[]> accountData = csvReader.readAll();
                    csvReader.close();

                    // Read account name and type
                    String name = accountData.get(1)[0];

                    String accountType = accountData.get(1)[1];
                    AccountEnum accountEnum = AccountEnum.valueOf(accountType);

                    // Read account interest info
                    String interestRateStr = accountData.get(5)[0];
                    String interestPeriod = accountData.get(5)[1];

                    //DEBUG
                    System.out.println(interestPeriod);
                    String interestType = accountData.get(5)[2];
                    String lastInterestDateStr = accountData.get(5)[3];

                    double interestRate = Double.parseDouble(interestRateStr);
                    InterestPeriodEnum interestPeriodEnum =
                        InterestPeriodEnum.valueOf(interestPeriod);
                    InterestEnum interestTypeEnum = InterestEnum.valueOf(interestType);
                    Date lastInterestDate = format.parse(lastInterestDateStr);

                    // Read account reccords
                    int endIndex = accountData.size();
                    ArrayList<AccountRecord> accountRecords =
                        new ArrayList<AccountRecord>();
                    for (int i = 9; i < endIndex; i++) {
                        String dateStr = accountData.get(i)[0];
                        Date date = format.parse(dateStr);

                        String valueStr = accountData.get(i)[1];
                        double value = Double.parseDouble(valueStr);

                        AccountRecord accountRecord = new AccountRecord(date, value);
                        accountRecords.add(accountRecord);
                    }

                    // Build an account
                    AccountBuilder accountBuilder =
                        new AccountBuilder(name, accountRecords)
                            .setAccountEnum(accountEnum)
                            .setInterestRate(interestRate)
                            .setInterestPeriodEnum(interestPeriodEnum)
                            .setInterestEnum(interestTypeEnum)
                            .setLastInterestDate(lastInterestDate);

                    Account account = accountBuilder.buildAccount();
                    return account;
            }
    }


    /**
     * @throws IOException
     * @throws ParseException
     *
     * This method looks through all account files in the accounts path and
     * builds and adds each Account to the Database.
     */
    private static void readAllAccounts() throws IOException, ParseException {
        //https://mkyong.com/java/how-to-get-the-current-working-directory-in-java/#:~:text=In%20Java%2C%20we%20can%20use,where%20your%20program%20was%20launched.
        String CurrentDirectory = System.getProperty("user.dir");
        File accountFolder = new File(CurrentDirectory + "/user_data/accounts");
        File[] accountFiles = accountFolder.listFiles();

        HashMap<String, Account> accounts = new HashMap<String, Account>();
        for (File f: accountFiles) {
            Path accountPath = f.toPath();
            Account account = readAccount(accountPath);
            accounts.put(account.getName(), account);
        }
        // Add Accounts to Database
        Mapper databaseMapper = Mapper.getInstance();
        databaseMapper.setAccounts(accounts);
    }


    /**
     * @throws IOException
     * @throws ParseException
     *
     * This method reads all transactions in the transactions file.
     *
     * Because transactions have Accounts attributes,
     * Accounts must be read and built first.
     */
    private static void readTransactions() throws IOException, ParseException {
        //https://mkyong.com/java/how-to-get-the-current-working-directory-in-java/#:~:text=In%20Java%2C%20we%20can%20use,where%20your%20program%20was%20launched.
        String currentDirectory = System.getProperty("user.dir");
        Path transactionPath = Paths.get(currentDirectory
            + "/user_data/transactions.csv");
        try (BufferedReader reader = Files.newBufferedReader(transactionPath)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> transactionData = csvReader.readAll();
            csvReader.close();

            HashMap<String, Transaction> transactions = new HashMap<String, Transaction>();
            int endIndex = transactionData.size();
            for (int i = 1; i < endIndex; i++) {
                String dateStr = transactionData.get(i)[0];
                Date date = format.parse(dateStr);

                String name = transactionData.get(i)[1];

                String valueStr = transactionData.get(i)[2];
                double value = Double.parseDouble(valueStr);

                String accountStr = transactionData.get(i)[3];
                Mapper databaseMapper = Mapper.getInstance();
                Account account = databaseMapper.findAccount(accountStr);

                String categoryStr = transactionData.get(i)[4];
                CategoryEnum category = CategoryEnum.valueOf(categoryStr);

                String transactionEnumStr = transactionData.get(i)[5];
                TransactionEnum transactionEnum =
                    TransactionEnum.valueOf(transactionEnumStr);

                String freqStr = transactionData.get(i)[6];
                TransactionFrequencyEnum frequency =
                    TransactionFrequencyEnum.valueOf(freqStr);

                // Build each transaction
                TransactionBuilder transactionBuilder =
                    new TransactionBuilder(name,
                        transactionEnum,
                        value,
                        date,
                        account)
                        .setFrequency(frequency)
                        .setCategory(category);

                Transaction transaction =
                    transactionBuilder.buildTransaction();
                transactions.put(transaction.getName(), transaction);
            }
            // Add transactions to Database.
            Mapper databaseMapper = Mapper.getInstance();
            databaseMapper.setTransactions(transactions);
        }
    }


    /**
     * @throws IOException
     * @throws ParseException
     *
     * Reads all Accounts and then Transactions.
     *
     * This method should be called at application start up.
     */
    public void readAll() throws IOException, ParseException {
        // Call on Start up
        readAllAccounts();
        readTransactions();
    }


    /**
     * @param account Account
     *
     * This method saves an Account by writing it to a CSV file.
     *
     * Got help writing to CSVs in Java from this resource:
     * https://www.baeldung.com/opencsv
     *
     * Got help sorting AccountRecords before save from:
     * https://www.geeksforgeeks.org/how-to-iterate-over-a-treemap-in-java/
     */
    private static void writeAccount(Account account) {
        // make it so contents of file are deleted before new save
        // this is easiest way to update any attribute or line without error;
        //https://mkyong.com/java/how-to-get-the-current-working-directory-in-java/#:~:text=In%20Java%2C%20we%20can%20use,where%20your%20program%20was%20launched.
        String currentDirectory = System.getProperty("user.dir");
        String name = account.getName();
        String filePath = currentDirectory
            + "/user_data/accounts/"
            + account.getName()
            + ".csv";
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

            // Write metadata
            String[] line1 = { "NAME", "TYPE"};
            writer.writeNext(line1);
            String[] line2 = {name, account.getAccountEnum().toString()};
            writer.writeNext(line2);
            String[] blankLine = {};
            writer.writeNext(blankLine);

            // Write interest info
            String[] line4 = {"Interest:"};
            writer.writeNext(line4);
            String[] line5 = {"PERCENT", "FREQ", "TYPE", "LAST_INTEREST_DATE"};
            writer.writeNext(line5);
            try {
                String[] line6 = {String.valueOf(account.getInterestRate()),
                    account.getInterestPeriodEnum().toString(),
                    account.getInterestEnum().toString(),
                    format.format(account.getLastInterestDate())};
                writer.writeNext(line6);
            } catch (Exception e) {
                // Can't call format.format(null)
                // If no interst data from one of these, there is no interest
                String[] line6 = {"0.0", null, null, null};
                    writer.writeNext(line6);
            }
            writer.writeNext(blankLine);

            // Write account Records
            String[] line8 = {"Account Records:"};
            writer.writeNext(line8);
            String[] line9 = {"DATE", "AMOUNT"};
            writer.writeNext(line9);
            // Sort accounts by Date
            Map<Date, Double> sortedMap = account.sortAccountRecords();
            
            for (Map.Entry<Date, Double> entry : sortedMap.entrySet()) {
                String[] line = {format.format(entry.getKey()),
                    String.valueOf(entry.getValue())};
                writer.writeNext(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method writes all Accounts to CSV files.
     */
    private void writeAllAccounts() {
        Mapper databaseMapper = Mapper.getInstance();
        Collection<Account> accounts = databaseMapper.getAccounts().values();
        for (Account a: accounts) {
            writeAccount(a);
        }
    }


    /**
     * This method writs all Transactions to a CSV file.
     */
    public static void writeTransactions() {
        //https://mkyong.com/java/how-to-get-the-current-working-directory-in-java/#:~:text=In%20Java%2C%20we%20can%20use,where%20your%20program%20was%20launched.
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + "/user_data/transactions.csv";
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

            String[] line1 = {"DATE", "NAME", "VALUE", "ACCOUNT", "CATEGORY",
                "ENUM", "FREQ"};
            writer.writeNext(line1);

            Mapper databaseMapper = Mapper.getInstance();
            HashMap<String, Transaction> transactions =
                databaseMapper.getTransactions();
            // Sort Transactions by Date
            Map<Date, Transaction> sortedMap = new TreeMap<Date, Transaction>();
            for (Transaction t: transactions.values()) {
                Date date = t.getDate();
                // elements automatically sorted when added to TreeMap
                sortedMap.put(date, t);
            }
            for (Map.Entry<Date, Transaction> entry : sortedMap.entrySet()) {
                Transaction t = entry.getValue();
                // Dealing with error when Account data is missing
                Account account = t.getTransactionAccount();
                String accountStr = new String();
                if (account != null) {
                    accountStr = account.getName().toString();
                } else {
                    accountStr = "";
                }
                // Create line String
                String[] line = {format.format(t.getDate()),
                    t.getName(),
                    String.valueOf(t.getValue()),
                    accountStr,
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


    /**
     * This method saves all Accounts and Transactions to CSV files.
     *
     * This method should be called before exiting the application.
     */
    public void writeAll() {
        writeAllAccounts();
        writeTransactions();
    }


    /**
     * @param name String
     *
     * This method is used to delete an Account file by its name.
     */
    public void delAccountFile(String name) {
        //https://mkyong.com/java/how-to-get-the-current-working-directory-in-java/#:~:text=In%20Java%2C%20we%20can%20use,where%20your%20program%20was%20launched.
        String currentDirectory = System.getProperty("user.dir");
        String rootPath = currentDirectory+ "/user_data/accounts/";
        String filePath = rootPath + name;
        File f = new File(filePath);  
        f.delete();
    }
}
