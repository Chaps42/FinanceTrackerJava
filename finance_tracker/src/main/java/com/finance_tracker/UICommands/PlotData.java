package com.finance_tracker.UICommands;

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

    public void execute(){
        System.out.println("Enter which plot to display: ");
        System.out.println("1: Line Chart");
        System.out.println("2: Pie Chart");
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
