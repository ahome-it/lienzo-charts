
package com.ait.lienzo.charts.client.core.factory;

import java.util.Date;

public interface IChartDataBuilder
{
    IChartDataBuilderAggregator with(Double value, String header);

    IChartDataBuilderAggregator with(String value, String header);

    IChartDataBuilderAggregator with(Date value, String header); 
}