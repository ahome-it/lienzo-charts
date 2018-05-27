package com.ait.lienzo.charts.client.core.factory;

import java.util.Date;

public interface IChartDataHandler
{
    IChartDataBuilder add(String someData);
    void applyChanges();
    
    // Add will return IChartDataAggregator (?)
    // Remove and delete should return other types
    
    //void add(Object[] params);
    
   /* IAnimatedChart remove(Object someData);
    IAnimatedChart update(Object someData);  
    void applyChanges();
    void discardChanges();*/
}


