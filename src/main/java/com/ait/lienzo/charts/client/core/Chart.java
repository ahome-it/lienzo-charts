package com.ait.lienzo.charts.client.core;

import com.ait.lienzo.charts.client.core.factory.IBuildedChart;
import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.ait.lienzo.charts.client.core.factory.IChartFactory;

public abstract class Chart //implements IChart, IDefinedChart
{   
    private Chart() {}
    
    public static IChartFactory create() 
    {
        return new ChartFactory();
    }
}

class ChartFactory implements IChartFactory
{
    @Override
    public IBuildedChart pieChart()
    {
        return new PieChart();
    }

    @Override
    public IBuildedChart barChart()
    {
        // TODO Auto-generated method stub
        return null;
    }    
}