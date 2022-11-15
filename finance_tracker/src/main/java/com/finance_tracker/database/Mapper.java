package com.finance_tracker.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class Mapper {
    //get() {}
    //search() {}
    //add() {}
    //remove() {}


    // Sample method for writing to a CSV file
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

    public static void readAccount() {}
    public static void readTransaction() {}
}
