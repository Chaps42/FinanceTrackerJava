package com.finance_tracker.UI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.*;

public class TabPageController {

    @FXML
    private ComboBox<?> expensesDateSelector;

    @FXML
    private GridPane overviewAccountsChart;

    @FXML
    private PieChart expensesPieChart;

    @FXML
    private Button incomeDeleteCatagory;

    @FXML
    private Button incomeNewDate;

    @FXML
    private TextField overviewSavingsEntry;

    @FXML
    private Button expensesDeleteCatagory;

    @FXML
    private Button incomeNewCatagory;

    @FXML
    private Button incomeNewRecurring;

    @FXML
    private Button expensesNewRecurring;

    @FXML
    private TextField overviewSavingsTotal;

    @FXML
    private TextField overviewNetValueTotal;

    @FXML
    private Button expensesRecordValues;

    @FXML
    private Button expensesNewDate;

    @FXML
    private PieChart incomePieChart;

    @FXML
    private TableView<?> expensesCatagories;

    @FXML
    private LineChart<?, ?> incomeOvertimeChart;

    @FXML
    private Button overviewRecordAccounts;

    @FXML
    private Button expensesNewCatagory;

    @FXML
    private TableColumn<?, ?> incomeRecurringIncomes;

    @FXML
    private Button expensesEditRecurring;

    @FXML
    private Button overviewCreateAccount;

    @FXML
    private TableColumn<?, ?> expensesRecurring;

    @FXML
    private TextField overviewIncomeEntry;

    @FXML
    private ComboBox<?> overviewGraphDateSelect;

    @FXML
    private Button expensesDeleteRecurring;

    @FXML
    private TextField overviewAccountTotals;

    @FXML
    private Button overviewDeleteAccount;

    @FXML
    private Button incomeEditRecurring;

    @FXML
    private Button incomeRecordValues;

    @FXML
    private TableView<?> incomeCatagories;

    @FXML
    private LineChart<?, ?> expensesOverTime;

    @FXML
    private Button incomeDeleteRecurring;

    @FXML
    private PieChart overviewSpendingPie;

    @FXML
    private TextField overviewBudgetRemainingEntry;

    @FXML
    private Button overviewEditAccount;

    @FXML
    private ComboBox<?> incomeChartDateSelect;

    @FXML
    private TextField overviewExpensesEntry;

    @FXML
    private TextField overviewDebtsTotal;

    @FXML
    private ComboBox<?> overviewSpendingDateSelect;

    @FXML
    private PieChart overviewGraphMain;

    @FXML
    void tab_Changed(ActionEvent event) {

    }

}
