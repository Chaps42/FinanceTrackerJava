package com.finance_tracker.plot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.finance_tracker.account.Account;
import com.finance_tracker.account.AccountRecord;
import com.finance_tracker.database.Mapper;


import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler.LegendPosition;

public class LinePlot {
    
    public XYChart plotAccountFiles() {
        // Create Chart
        XYChart accountChart = new XYChartBuilder()
            .width(800)
            .height(600)
            .title("Accounts")
            .xAxisTitle("Time")
            .yAxisTitle("Value ($)")
            .build();

        // Customize Chart
        accountChart.getStyler().setLegendPosition(LegendPosition.InsideNE);

        // get accounts data
        Mapper databaseMapper = Mapper.getInstance();
        HashMap<String, Account> accounts = databaseMapper.getAccounts();
        for (Account a: accounts.values()) {
            // for every account
            String accountName = a.getName();

            ArrayList<AccountRecord> records = a.getAccountRecords();
            ArrayList<Date> dates = new ArrayList<Date>();
            ArrayList<Double> amounts = new ArrayList<Double>();
            for (AccountRecord r: records) {
                // Split list of records into 2 lists of dates and of amounts
                dates.add(r.getDate());
                amounts.add(r.getAmount());
            }
            // Add data to chart
            accountChart.addSeries(accountName, dates, amounts);
        }
        return accountChart;
    }
}
