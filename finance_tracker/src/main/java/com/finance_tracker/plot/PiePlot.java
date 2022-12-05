package com.finance_tracker.plot;

import java.util.EnumMap;
import java.util.HashMap;

import com.finance_tracker.database.Mapper;
import com.finance_tracker.math.CategoryMath;
import com.finance_tracker.transaction.CategoryEnum;
import com.finance_tracker.transaction.Transaction;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

public class PiePlot {


        // https://docs.oracle.com/javafx/2/charts/pie-chart.htm
    public void plotTransactions(Stage stage) {

        Mapper databaseMapper = Mapper.getInstance();
        HashMap<String, Transaction> transactions = databaseMapper.getTransactions();
        EnumMap<CategoryEnum, Double> categoryMap = CategoryMath.getCategoryAmounts(transactions.values());
        //EnumMap<CategoryEnum, Double> categoryPercentMap = CategoryMath.getCategoryPercents(transactions.values());

        
        // investigating javaFX because that is what the GUI is using?
        //OR should I focus on a terminal only method?
        Scene scene = new Scene(new Group());
        stage.setTitle("Transaction Categories");
        stage.setWidth(500);
        stage.setHeight(500);
         
        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList();
        
        // Add data from category HashMap
        PieChart.Data data[] = new PieChart.Data[categoryMap.size()];
        int n = 0;
        for (CategoryEnum c: categoryMap.keySet()) {
            String name = String.valueOf(c);
            Double amount = categoryMap.get(c);

            data[n] = new PieChart.Data(name, amount);
            n += 1;
        }

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Transaction Categories");
        
        stage.setScene(scene);
        stage.show();
    }
}
