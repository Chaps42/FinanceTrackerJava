package com.finance_tracker.UICommands;

import java.io.IOException;

import com.finance_tracker.backendlogic.Mediator;
import com.finance_tracker.plot.LinePlotter;
import com.finance_tracker.plot.PiePlotter;

public class PlotData extends Command{
    private PiePlotter PieChart = new PiePlotter();
    private LinePlotter LineChart = new LinePlotter();


    public PlotData(Mediator CentralRef){
        super(CentralRef);
        this.CodeNum = 6;
        this.Name = "Show Plot";

    }

    //Asks the user which plot to produce to view their financial information
    public void execute() throws IOException{
        System.out.println("Enter which plot to display: ");
        System.out.println("1: Accounts");
        System.out.println("2: Transactions");
        Integer choice = this.UserInput.nextInt();
        switch(choice){
            case 1:
                this.LineChart.plotAccountFiles();
                break;
            case 2:
                this.PieChart.plotTransactions();
                break;
            default:
                this.LineChart.plotAccountFiles();
                break;
        }



    }

}
